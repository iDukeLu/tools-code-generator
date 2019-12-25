package com.sekorm.tools.codegenerator.core;

import com.sekorm.tools.codegenerator.core.config.ApiGeneratorConfig;
import com.sekorm.tools.codegenerator.core.constant.GeneratorConstants;
import com.sekorm.tools.codegenerator.core.constant.TemplateConstants;
import com.sekorm.tools.codegenerator.core.engine.BeetlEngine;
import com.sekorm.tools.codegenerator.core.engine.FreemarkerEngine;
import com.sekorm.tools.codegenerator.core.engine.TemplateEngine;
import com.sekorm.tools.codegenerator.core.exception.NoSuchRenderEngineException;
import com.sekorm.tools.codegenerator.core.template.Template;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.tags.Tag;
import io.swagger.v3.parser.OpenAPIV3Parser;
import lombok.Data;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 * API 生成器
 *
 * @author duke
 */
@Data
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
        OpenAPI openApi = new OpenAPIV3Parser().read(config.getInputSpec());

        TemplateEngine engine = initEngine(config.getEngine());
        Template template = engine.readTemplate(TemplateConstants.API_TEMPLATE);

        // 设置参数
        HashMap<String, Object> params = new HashMap<>(16);
        params.put("config", config);
//        params.put("openApi", openApi);

        // 路径处理
        Path apiDir = Paths.get(config.getOutput(), config.getApiPackage().replace(".", "/")).normalize();
        if (!Files.exists(apiDir)) {
            try {
                Files.createDirectories(apiDir);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            for (Tag tag : openApi.getTags()) {

//                List<Map.Entry<String, PathItem>> getOptions = openApi.getPaths().entrySet().stream()
//                        .filter(p -> p.getValue().getGet() != null)
//                        .filter(p -> p.getValue().getGet().getTags().get(0).equals(tag.getName()))
//                        .collect(Collectors.toList());
//
//                List<Map.Entry<String, PathItem>> getOptions = openApi.getPaths().entrySet().stream()
//                        .filter(p -> p.getValue().getGet() != null)
//                        .filter(p -> p.getValue().getGet().getTags().get(0).equals(tag.getName()))
//                        .collect(Collectors.toList());
//
//                openApi.getPaths().entrySet().stream().collect(Collectors.groupingBy());
//
//
//
//                params.put("tag", tag);
//                params.put("getOptions", getOptions);


                String apiFile = tag.getName() + GeneratorConstants.JAVA_FILE_SUFFIX;
                BufferedWriter out = Files.newBufferedWriter(apiDir.resolve(apiFile));
                template.render(params, out);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 选取渲染引擎
     *
     * @param engine 引擎类型
     * @return 渲染引擎
     */
    private TemplateEngine initEngine(String engine) {
        if (TemplateConstants.FREEMARKER_ENGINE.equals(engine)) {
            return FreemarkerEngine.getSingleton().init();
        } else if (TemplateConstants.BEETL_ENGINE.equals(engine)) {
            return BeetlEngine.getSingleton().init();
        } else {
            throw new NoSuchRenderEngineException("Currently only supports 'Freemarker' and 'Beetl' rendering engines");
        }
    }

}
