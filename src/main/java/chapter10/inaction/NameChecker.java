package chapter10.inaction;

import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.*;
import javax.lang.model.util.ElementScanner8;
import javax.tools.Diagnostic;
import java.util.EnumSet;


public class NameChecker {
    private final Messager messager;

    NameCheckerScanner nameCheckerScanner = new NameCheckerScanner();

    NameChecker(ProcessingEnvironment environment) {
        this.messager = environment.getMessager();
    }

    /**
     *  类或接口：符合驼峰命名法，首字母大写
     *  方法： 符合驼峰命名法，首字母小写
     *
     *  类、实例变量：符合驼峰命名法，首字母小写
     *  常量 ： 全大写或下划线，不能以下划线开始
     *
     * @param element
     */
    public void checkName(Element element) {
        nameCheckerScanner.scan(element);
    }

    private class NameCheckerScanner extends ElementScanner8<Void, Void> {
        //  检查Java类
        @Override
        public Void visitType(TypeElement e, Void aVoid) {
            scan(e.getTypeParameters(), aVoid);
            checkCamelCase(e, true);
            super.visitType(e, aVoid);
            return null;
        }

        // 检查方法命名是否合法
        @Override
        public Void visitExecutable(ExecutableElement e, Void aVoid) {
            if (e.getKind() == ElementKind.METHOD) {
                Name name = e.getSimpleName();
                if (name.contentEquals(e.getEnclosingElement().getSimpleName())) {
                    messager.printMessage(Diagnostic.Kind.WARNING, "一个普通方法" + name + "不应当与类名重复，避免与构造函数产生混淆", e);
                }
                checkCamelCase(e, false);
            }
            super.visitExecutable(e, aVoid);
            return null;
        }

        // 检查变量名是否合法

        @Override
        public Void visitVariable(VariableElement e, Void aVoid) {
            // 如果是枚举或者常量，则按大写命名检查，否则按照驼峰检查
            if (e.getKind() == ElementKind.ENUM_CONSTANT || e.getConstantValue() != null || heuristicallyConstant(e)) {
                checkAllCaps(e);
            } else {
                checkCamelCase(e, false);
            }
            return null;
        }

        // 判断一个变量是否常量
        private boolean heuristicallyConstant(VariableElement e) {
            if (e.getEnclosingElement().getKind() == ElementKind.INTERFACE) {
                return true;
            }
            return e.getKind() == ElementKind.FIELD && e.getModifiers().containsAll(EnumSet.of(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL));
        }

        // 检查传入的Element是否符合驼峰命名法，如果不符合，输出警告
        // 注：只会简单检查是否是有连续的大写字母，而不会进行单词的匹配
        private void checkCamelCase(Element e, boolean initialCaps) {
            String name = e.getSimpleName().toString();
            boolean previousUpper = false;
            boolean conventional = true;
            int firstCodePoint = name.codePointAt(0);

            if (Character.isUpperCase(firstCodePoint)) {
                previousUpper = true;
                if (!initialCaps) {
                    messager.printMessage(Diagnostic.Kind.WARNING, "名称"+name+"应当以小写字母开头", e);
                    return ;
                }
            } else if (Character.isLowerCase(firstCodePoint)) {
                if (initialCaps) {
                    messager.printMessage(Diagnostic.Kind.WARNING, "名称"+name+"应当以大写字母开头", e);
                    return ;
                }
            } else conventional = false;

            if (conventional) {
                int cp = firstCodePoint;
                for (int i = Character.charCount(cp); i < name.length(); i+=Character.charCount(cp)) {
                    cp = name.codePointAt(i);
                    if (Character.isUpperCase(cp)) {
                        if (previousUpper) {
                            conventional = false;
                            break;
                        }
                        previousUpper = true;
                    } else previousUpper = false;
                }
            }
            if (!conventional) {
                messager.printMessage(Diagnostic.Kind.WARNING, "名称"+name+"应当符合驼峰命名法(Camel Case Names)", e);
            }
        }

        // 大写命名检查，要求第一个字母必需是大写的英文字母，其余部分可以是大写字母或下划线
        private void checkAllCaps(Element e) {
            String name = e.getSimpleName().toString();
            boolean conventional = true;
            int firstCodePoint = name.codePointAt(0);
            if (!Character.isUpperCase(firstCodePoint)) {
                conventional = false;
            } else {
                boolean previousUnderScore = false;
                int cp = firstCodePoint;
                for (int i = Character.charCount(cp); i < name.length(); i+= Character.charCount(cp)) {
                    cp = name.codePointAt(i);
                    if (cp == (int) '_') {
                        if (previousUnderScore) {
                            conventional = false;
                            break;
                        }
                        previousUnderScore = true;
                    } else {
                        previousUnderScore = false;
                        if (!Character.isUpperCase(cp) && !Character.isDigit(cp)) {
                            conventional = false;
                            break;
                        }
                    }
                }
            }

            if (!conventional) {
                messager.printMessage(Diagnostic.Kind.WARNING, "常量" + name + "应当全部以大写字母或下划线命名，并且以字母开头", e);
            }
        }
    }
}
