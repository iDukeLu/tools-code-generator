package com.sekorm.tools.codegenerator.core;


import java.io.IOException;
import java.net.URISyntaxException;

public class Application {





    public static void main(String[] args) throws IOException, URISyntaxException {


//        // 第一步：创建一个Configuration对象，直接new一个对象。构造方法的参数就是freemarker对于的版本号。
//        Configuration configuration = new Configuration(Configuration.getVersion());
//        configuration.setDirectoryForTemplateLoading("");
//        configuration.setDefaultEncoding("utf-8");
//
//        // 第四步：加载一个模板，创建一个模板对象。
//        Template template = configuration.getTemplate("hello.ftl");
//
//        // 第五步：创建一个模板使用的数据集，可以是pojo也可以是map。一般是Map。
//        Map dataModel = new HashMap();
//        // 向数据集中添加数据
//        dataModel.put("hello", "this is my first freemarker test.");
//
//        // 第六步：创建一个Writer对象，一般创建一FileWriter对象，指定生成的文件名。
//        Writer out = new FileWriter(new File("D:\\Java\\Eclipse\\workspace_Test\\FreeMarker\\out\\hello.html"));
//
//        // 第七步：调用模板对象的process方法输出文件。
//        template.process(dataModel, out);
//
//        // 第八步：关闭流。
//        out.close();
    }

    private void init(String engine) {

//        if (BEETL_ENGINE.equals(engine)) {
//            Configuration configuration = new Configuration(Configuration.getVersion());
//            configuration.setDefaultEncoding(StandardCharsets.UTF_8.name());
//            try {
//                configuration.setDirectoryForTemplateLoading(new File(Application.class.getResource("/templates/freemarker").toURI()));
//                Template template = configuration.getTemplate("hello.ftl");
//            } catch (IOException | URISyntaxException e) {
//
//            }
//
//        }
//
//        if (FREEMARKER_ENGINE.equals(engine)) {
//
//        }
    }
}
