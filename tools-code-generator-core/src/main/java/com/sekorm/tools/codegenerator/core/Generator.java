package com.sekorm.tools.codegenerator.core;

import com.sekorm.tools.codegenerator.core.config.ApiConfig;
import com.sekorm.tools.codegenerator.core.constant.TemplateConstants;
import com.sekorm.tools.codegenerator.core.engine.BeetlEngine;
import com.sekorm.tools.codegenerator.core.engine.FreemarkerEngine;
import com.sekorm.tools.codegenerator.core.engine.TemplateEngine;
import com.sekorm.tools.codegenerator.core.exception.NoSuchRenderEngineException;
import com.sekorm.tools.codegenerator.core.template.Template;
import io.swagger.models.Path;
import io.swagger.models.Swagger;
import io.swagger.models.Tag;
import io.swagger.parser.SwaggerParser;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.*;

/**
 * @author duke
 */
public class Generator {

    private static TemplateEngine templateEngine;

    /**
     * freemarker 生成文件
     *
     * @param apiConfig api 配置
     * @throws IOException IO 异常
     */
    public static void generate(ApiConfig apiConfig) {
        initEngine(apiConfig.getEngine());
        generateApi(apiConfig);
        generateVo(apiConfig);
    }

    private static void initEngine(String engine) {
        if (templateEngine != null) {
            return;
        }
        if (TemplateConstants.FREEMARKER_ENGINE.equals(engine)) {
            templateEngine = FreemarkerEngine.getSingleton();
        } else if (TemplateConstants.BEETL_ENGINE.equals(engine)) {
            templateEngine = BeetlEngine.getSingleton();
        } else {
            throw new NoSuchRenderEngineException("Currently only supports 'Freemarker' and 'Beetl' rendering engines");
        }
    }

    private static void generateApi(ApiConfig apiConfig) {
        if (apiConfig.isGenerateApis()) {
            return;
        }
        // 检查是否需要覆盖
        doGenerateApi(apiConfig);
    }

    private static void generateVo(ApiConfig apiConfig) {
        if (apiConfig.isGenerateModels()) {

        }
    }

    private static void doGenerateApi(ApiConfig apiConfig) {
        // 获取模版
        TemplateEngine freemarkerEngine = templateEngine.init();
        Template template = freemarkerEngine.readTemplate("api.ftl");
        Swagger swagger = new SwaggerParser().read(apiConfig.getInputSpec());

        // 设置参数
        Map<String, Object> param = new HashMap<>(2);
        param.put("apiConfig", apiConfig);
        param.put("swagger", swagger);

        String output = apiConfig.getOutput();
        File file = new File(output);
        if (!file.exists() || !file.isDirectory()) {
            file.mkdir();
        }
        try {
            for (Tag tag : swagger.getTags()) {
                String controller = tag.getDescription().replace(" ", "");
                String path = apiConfig.getOutput() + controller + ".java";
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(path))));
                template.render(param, out);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void doGenerateBack(TemplateEngine templateEngine, ApiConfig apiConfig) {
        if (apiConfig.isSkipOverwrite()) {
            return;
        }
        Swagger swagger = new SwaggerParser().read(apiConfig.getInputSpec());

        // 获取模版
        TemplateEngine freemarkerEngine = templateEngine.init();
        Template template = freemarkerEngine.readTemplate("api.ftl");

        // 设置参数
        Map<String, Object> param = new HashMap<>(2);
        param.put("apiConfig", apiConfig);
        param.put("swagger", swagger);

        String output = apiConfig.getOutput();
        File file = new File(output);
        if (!file.exists() || !file.isDirectory()) {
            file.mkdir();
        }
        try {
            for (Tag tag : swagger.getTags()) {
                String controller = tag.getDescription().replace(" ", "");
                String path = apiConfig.getOutput() + controller + ".java";
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(path))));
                template.render(param, out);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void getParam(Swagger swagger) {
        // 获取模版
        TemplateEngine freemarkerEngine = templateEngine.init();
        Template template = freemarkerEngine.readTemplate("");

        // 设置参数
        Map<String, Object> param = new HashMap<>(2);


        String output = apiConfig.getOutput();
        File file = new File(output);
        if (!file.exists() || !file.isDirectory()) {
            file.mkdirs();
        }

        List<Tag> tags = swagger.getTags();
        try {
            for (Tag tag : swagger.getTags()) {
                String controller = tag.getDescription().replace(" ", "");
                String path = apiConfig.getOutput() + controller + ".java";
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(path))));
                template.render(param, out);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void gn(String templateFile, Collection<String> paths) {
        TemplateEngine freemarkerEngine = templateEngine.init();
        Template template = freemarkerEngine.readTemplate(templateFile);
        try {
            for (String path : paths) {
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(path))));
                template.render(param, out);
            }
            for () {
                String controller = tag.getDescription().replace(" ", "");
                String path = apiConfig.getOutput() + controller + ".java";

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}
