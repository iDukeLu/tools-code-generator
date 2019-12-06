package com.sekorm.tools.codegenerator.core.engine;

import com.sekorm.tools.codegenerator.core.constant.FreemarkerConstants;
import com.sekorm.tools.codegenerator.core.template.FreemarkerTemplate;
import com.sekorm.tools.codegenerator.core.template.Template;
import freemarker.template.Configuration;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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
        try {
            URI uri = this.getClass().getResource(FreemarkerConstants.FREEMARKER_TEMPLATES_PATH).toURI();
            this.configuration.setDirectoryForTemplateLoading(new File(uri));
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace(); // TODO 打印日志
        }
        return freemarkerEngine;
    }

    @Override
    public Template<freemarker.template.Template> readTemplate(String fileName) {
        if (fileName == null ) {
            throw new NullPointerException("fileName not be null");
        }
        try {
            return new FreemarkerTemplate<freemarker.template.Template>().setT(configuration.getTemplate(fileName));
        } catch (IOException e) {
            e.printStackTrace(); // TODO 打印日志
        }
        return null;
    }
}
