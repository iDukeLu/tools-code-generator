package com.sekorm.tools.codegenerator.core;

import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.StringTemplateResourceLoader;

import java.io.Writer;
import java.util.Map;

/**
 * @author duke
 */
public class BeetlEngine implements TemplateEngine {
    @Override
    public TemplateEngine init() {

//        //初始化代码
//        StringTemplateResourceLoader resourceLoader = new StringTemplateResourceLoader();
//        Configuration cfg = Configuration.defaultConfiguration();
//        GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);
//        //获取模板
//        Template t = gt.getTemplate("hello,${name}");
//        t.binding("name", "beetl");
//        //渲染结果
//        String str = t.render();
//        System.out.println(str);

        return null;
    }

    @Override
    public <T> T readTemplate(String fileName, Class<T> templateType) {
        return null;
    }

    @Override
    public void renderAndWriteFile(Map param, Writer out) {

    }

    @Override
    public void close() {

    }
}
