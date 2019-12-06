package com.sekorm.tools.codegenerator.core.engine;

import com.sekorm.tools.codegenerator.core.template.FreemarkerTemplate;
import com.sekorm.tools.codegenerator.core.template.Template;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.resource.StringTemplateResourceLoader;

import java.io.IOException;

/**
 * @author duke
 */
public class BeetlEngine implements TemplateEngine {

    private static BeetlEngine beetlEngine = new BeetlEngine();

    private GroupTemplate groupTemplate;

    private BeetlEngine() {}

    public static BeetlEngine getSingleton() {
        return beetlEngine;
    }

    @Override
    public BeetlEngine init() {
        try {
            StringTemplateResourceLoader resourceLoader = new StringTemplateResourceLoader();
            Configuration configuration = Configuration.defaultConfiguration();
            beetlEngine.groupTemplate = new GroupTemplate(resourceLoader, configuration);
        } catch (IOException e) {
            e.printStackTrace(); // TODO 打印日志
        }
        return beetlEngine;
    }

    @Override
    public Template<org.beetl.core.Template> readTemplate(String fileName) {
        if (fileName == null ) {
            throw new NullPointerException("fileName not be null");
        }
        return new FreemarkerTemplate<org.beetl.core.Template>().setT(groupTemplate.getTemplate(fileName));
    }
}
