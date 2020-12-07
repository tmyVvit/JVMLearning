package chapter10.inaction;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.Set;

@SupportedAnnotationTypes("*")
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class MyNameCheckProcessor extends AbstractProcessor {

    private NameChecker nameChecker;

    // 初始化名称检查插件
    @Override
    public void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        this.nameChecker = new NameChecker(processingEnvironment);
    }

    // 对输入的各个节点进行名称检查
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (!roundEnv.processingOver()) {
            for (Element element : roundEnv.getRootElements())
                nameChecker.checkName(element);
        }
        return false;
    }
}

// 编译运行过程：
// javac chapter10/inaction/NameChecker.java
// javac chapter10/inaction/MyNameCheckProcessor.java
// javac -processor chapter10.inaction.MyNameCheckProcessor chapter10/inaction/BAD_NAME_CODE.java

// Javac 的 -processor 命令可以用来执行编译时需要附带的注解处理器，如果有多个注解，用逗号分隔
// 也可以使用 -XprintRounds -XprintProcessorInfo 参数来查看注解处理器运作的详细信息
