package com.sekorm.tools.codegenerator.core;

import com.sekorm.tools.codegenerator.core.constant.FreemarkerConstants;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


/**
 * @author duke
 */
public class FreemarkerEngine implements TemplateEngine {

    private Configuration configuration;

    private FreemarkerEngine freemarkerEngine;

    private FreemarkerEngine() {}

    @Override
    public TemplateEngine init() {
        if (freemarkerEngine == null) {
            return doInit();
        }
        return freemarkerEngine;


//        // 第四步：加载一个模板，创建一个模板对象。
//
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

    private FreemarkerEngine doInit() {
        freemarkerEngine = new FreemarkerEngine();
        freemarkerEngine.configuration = new Configuration(Configuration.getVersion());
        configuration.setDefaultEncoding(StandardCharsets.UTF_8.name());
        try {
            URI uri = this.getClass().getResource(FreemarkerConstants.FREEMARKER_TEMPLATES_PATH).toURI();
            configuration.setDirectoryForTemplateLoading(new File(uri));
        } catch (URISyntaxException | IOException e) {
            // TODO 打印日志
        }
        return freemarkerEngine;
    }

    @Override
    public <T> T readTemplate(String fileName, Class<T> templateType) {
        try {
            Template template = configuration.getTemplate(fileName);
        } catch (IOException e) {
            // TODO 打印日志
        }
        return null;
    }

    @Override
    public void renderAndWriteFile(Map param, Writer out) {

    }

    @Override
    public void close() {

    }
}
