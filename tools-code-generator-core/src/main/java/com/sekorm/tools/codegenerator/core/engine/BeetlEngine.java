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

    private BeetlEngine beetlEngine;

    private GroupTemplate groupTemplate;

    private BeetlEngine() {}

    @Override
    public TemplateEngine init() {
        if (beetlEngine == null) {
            return doInit();
        }
        return beetlEngine;
    }

    @Override
    public Template<org.beetl.core.Template> readTemplate(String fileName) {
        return new FreemarkerTemplate<org.beetl.core.Template>().setT(groupTemplate.getTemplate(fileName));
    }

    private BeetlEngine doInit() {
        beetlEngine = new BeetlEngine();
        try {
            StringTemplateResourceLoader resourceLoader = new StringTemplateResourceLoader();
            Configuration configuration = Configuration.defaultConfiguration();
            beetlEngine.groupTemplate = new GroupTemplate(resourceLoader, configuration);
        } catch (IOException e) {
            e.printStackTrace(); // TODO 打印日志
        }
        return beetlEngine;
    }

}
