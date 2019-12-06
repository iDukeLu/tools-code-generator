package com.sekorm.tools.codegenerator.core;


import com.sekorm.tools.codegenerator.core.engine.FreemarkerEngine;
import com.sekorm.tools.codegenerator.core.engine.TemplateEngine;

import java.io.FileNotFoundException;

/**
 * @author duke
 */
public class Application {

    public static void main(String[] args) throws FileNotFoundException {
        TemplateEngine freemarkerEngine = FreemarkerEngine.getSingleton().init();
//        Template<freemarker.template.Template> template = freemarkerEngine.readTemplate("hello.ftl");
//        Map<String, Object> map = new HashMap<>();
//        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("src")));
//        template.render(map, out);
    }
}
