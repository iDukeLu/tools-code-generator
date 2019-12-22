package com.sekorm.tools.codegenerator.core.engine;

import com.sekorm.tools.codegenerator.core.constant.TemplateConstants;
import com.sekorm.tools.codegenerator.core.template.FreemarkerTemplate;
import com.sekorm.tools.codegenerator.core.template.Template;
import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.NullCacheStorage;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;


/**
 * Freemarker 模板引擎
 * @author duke
 */
public class FreemarkerEngine implements TemplateEngine {

    private static FreemarkerEngine freemarkerEngine = new FreemarkerEngine();

    private Configuration configuration;

    private FreemarkerEngine() {}

    public static FreemarkerEngine getSingleton() {
        return freemarkerEngine;
    }

    @Override
    public FreemarkerEngine init() {
        freemarkerEngine.configuration = new Configuration(Configuration.getVersion());
        this.configuration.setDefaultEncoding(StandardCharsets.UTF_8.name());
        this.configuration.setCacheStorage(NullCacheStorage.INSTANCE);
        this.configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        this.configuration.setTemplateLoader(new ClassTemplateLoader(FreemarkerEngine.class, TemplateConstants.FREEMARKER_TEMPLATES_PATH));
        return freemarkerEngine;
    }

    @Override
    public Template readTemplate(String fileName) {
        if (fileName == null ) {
            throw new NullPointerException("fileName not be null");
        }
        try {
            return new FreemarkerTemplate().setTemplate(configuration.getTemplate(fileName + TemplateConstants.FREEMARKER_TEMPLATES_SUFFIX));
        } catch (IOException e) {
            e.printStackTrace(); // TODO 打印日志
        }
        return null;
    }
}
