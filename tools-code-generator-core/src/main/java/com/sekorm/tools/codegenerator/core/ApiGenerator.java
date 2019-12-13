package com.sekorm.tools.codegenerator.core;

import com.sekorm.tools.codegenerator.core.config.ApiGeneratorConfig;
import com.sekorm.tools.codegenerator.core.constant.GeneratorConstants;
import com.sekorm.tools.codegenerator.core.constant.TemplateConstants;
import com.sekorm.tools.codegenerator.core.engine.BeetlEngine;
import com.sekorm.tools.codegenerator.core.engine.FreemarkerEngine;
import com.sekorm.tools.codegenerator.core.engine.TemplateEngine;
import com.sekorm.tools.codegenerator.core.exception.NoSuchRenderEngineException;
import com.sekorm.tools.codegenerator.core.template.Template;
import io.swagger.models.Swagger;
import io.swagger.models.Tag;
import io.swagger.parser.SwaggerParser;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * @author duke
 */
@Data
@Accessors(chain = true)
public class ApiGenerator implements Generator {

    private ApiGeneratorConfig config;

    public ApiGenerator() {

    }

    public ApiGenerator(ApiGeneratorConfig config) {
        this.config = config;
    }

    @Override
    public void generate() {
        generateApi();
    }

    /**
     * 渲染生成 API 文件
     */
    private void generateApi() {
        // 解析 OpenAPI 规则文件、初始化引擎、获取模板
        Swagger swagger = new SwaggerParser().read(config.getInputSpec());
        TemplateEngine engine = chooseEngine(config.getEngine());
        Template template = engine.init().readTemplate(TemplateConstants.FREEMARKER_API_TEMPLATE);

        // 设置参数
        HashMap<String, Object> params = new HashMap<>(2);
        params.put("config", config);
        params.put("swagger", swagger);

        // 路径处理
        Path apiDir = Paths.get(config.getOutput(), config.getApiPackage().replace(".", "/")).normalize();
        if (!Files.exists(apiDir)) {
            try {
                Files.createDirectories(apiDir);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 渲染
        try {
            for (Tag tag : swagger.getTags()) {
                Path apiFile = apiDir.resolve(tag.getDescription().replace(" ", "") + GeneratorConstants.JAVA_FILE_SUFFIX);
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(apiFile.toFile())));
                template.render(params, out);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 选取渲染引擎
     * @param engine 引擎类型
     * @return 渲染引擎
     */
    private TemplateEngine chooseEngine(String engine) {
        if (TemplateConstants.FREEMARKER_ENGINE.equals(engine)) {
            return FreemarkerEngine.getSingleton().init();
        } else if (TemplateConstants.BEETL_ENGINE.equals(engine)) {
            return BeetlEngine.getSingleton().init();
        } else {
            throw new NoSuchRenderEngineException("Currently only supports 'Freemarker' and 'Beetl' rendering engines");
        }
    }
}
