package com.sekorm.tools.codegenerator.core.engine;

import com.sekorm.tools.codegenerator.core.constant.TemplateConstants;
import com.sekorm.tools.codegenerator.core.template.BeetlTemplate;
import com.sekorm.tools.codegenerator.core.template.FreemarkerTemplate;
import com.sekorm.tools.codegenerator.core.template.Template;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.resource.ClasspathResourceLoader;
import org.beetl.core.resource.StringTemplateResourceLoader;

import java.io.IOException;

/**
 * Beetl 模版引擎
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
            ClasspathResourceLoader resourceLoader = new ClasspathResourceLoader(TemplateConstants.BEETL_TEMPLATES_PATH);
            Configuration configuration = Configuration.defaultConfiguration();
            beetlEngine.groupTemplate = new GroupTemplate(resourceLoader, configuration);
        } catch (IOException e) {
            e.printStackTrace(); // TODO 打印日志
        }
        return beetlEngine;
    }

    @Override
    public Template readTemplate(String fileName) {
        if (fileName == null ) {
            throw new NullPointerException("fileName not be null");
        }
        return new BeetlTemplate().setTemplate(groupTemplate.getTemplate(fileName + TemplateConstants.BEETL_TEMPLATES_SUFFIX));
    }
}
