package com.sekorm.tools.codegenerator.core;

import com.sekorm.tools.codegenerator.core.constant.TemplateConstants;
import com.sekorm.tools.codegenerator.core.engine.BeetlEngine;
import com.sekorm.tools.codegenerator.core.engine.FreemarkerEngine;
import com.sekorm.tools.codegenerator.core.engine.TemplateEngine;
import com.sekorm.tools.codegenerator.core.template.Template;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Generator {

    public void getPath(String path) throws IOException {
        System.err.println(Paths.get(Paths.get("").toRealPath().toString(), path).toRealPath());
    }

    public void getPath() throws IOException, URISyntaxException {


    }

    /**
     * freemarker 生成文件
     * @param path 路径
     * @throws IOException IO 异常
     */
    public void generator(String engine, String path) throws IOException {
        TemplateEngine templateEngine = null;
        if (TemplateConstants.FREEMARKER_ENGINE.equals(engine)) {
            templateEngine = FreemarkerEngine.getSingleton();
        } else if (TemplateConstants.BEETL_ENGINE.equals(engine)) {
            templateEngine = BeetlEngine.getSingleton();
        } else {

        }
        doGenerator(templateEngine, path);
    }

    private void doGenerator(TemplateEngine templateEngine, String path) throws IOException {
        TemplateEngine freemarkerEngine = templateEngine.init();
        Template<Template> template = freemarkerEngine.readTemplate("hello.ftl");
        Map<String, Object> map = new HashMap<>();
        map.put("hello", "hello");
        String file = Paths.get(Paths.get("").toRealPath().toString(), path).normalize().toString();
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(file))));
        template.render(map, out);
    }

//    public void generator(String path) throws IOException {
//
//    }
}
