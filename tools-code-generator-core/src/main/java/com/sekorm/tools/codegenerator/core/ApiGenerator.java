package com.sekorm.tools.codegenerator.core;

import com.sekorm.tools.codegenerator.core.config.ApiGeneratorConfig;
import com.sekorm.tools.codegenerator.core.constant.TemplateConstants;
import com.sekorm.tools.codegenerator.core.engine.BeetlEngine;
import com.sekorm.tools.codegenerator.core.engine.FreemarkerEngine;
import com.sekorm.tools.codegenerator.core.engine.TemplateEngine;
import com.sekorm.tools.codegenerator.core.exception.NoSuchRenderEngineException;
import com.sekorm.tools.codegenerator.core.pojo.SimpleSwagger;
import com.sekorm.tools.codegenerator.core.pojo.SimpleTag;
import com.sekorm.tools.codegenerator.core.template.Template;
import com.sekorm.tools.codegenerator.core.util.BeanUtils;
import io.swagger.models.Model;
import io.swagger.models.Swagger;
import io.swagger.models.Tag;
import io.swagger.parser.SwaggerParser;
import lombok.Data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * API 生成器
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
        Swagger swagger = new SwaggerParser().read(config.getInputSpec());
        TemplateEngine engine = chooseEngine(config.getEngine());
        Template template = engine.init().readTemplate(TemplateConstants.FREEMARKER_API_TEMPLATE);

        // 设置参数
        HashMap<String, Object> params = new HashMap<>(16);
        params.put("config", config);
        params.put("paths", swagger.getPaths());

        // 路径处理
        Path apiDir = Paths.get(config.getOutput(), config.getApiPackage().replace(".", "/")).normalize();
        if (!Files.exists(apiDir)) {
            try {
                Files.createDirectories(apiDir);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        SimpleSwagger simpleSwagger = new SimpleSwagger();

//        List<SimpleTag> tags = getSimpleTags(swagger);
        BeanUtils.copyPropertiesBatch(swagger.getTags(), simpleSwagger.getTags(), Tag.class, SimpleTag.class, null);



        System.err.println(Arrays.toString(swagger.getTags().toArray()));
        System.err.println(Arrays.toString(simpleSwagger.getTags().toArray()));


        // 渲染
//        try {
//            for (Tag tag : swagger.getTags()) {
//                params.put("tagName", tag.getName());
//                params.put("tagDescription", tag.getDescription());
//
//                List<Map.Entry<String, io.swagger.models.Path>> gets = swagger.getPaths().entrySet().stream()
//                        .filter(p -> p.getValue().getGet() != null)
//                        .filter(p -> p.getValue().getGet().getTags().contains(tag))
//                        .collect(Collectors.toList());
//
//
//                Path apiFile = apiDir.resolve(tag.getName() + GeneratorConstants.JAVA_FILE_SUFFIX);
//                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(apiFile.toFile())));
//                template.render(params, out);
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
    }

    private List<SimpleTag> getSimpleTags(Swagger swagger) {
        return swagger.getTags().stream().map(tag -> {
                SimpleTag simpleTag = new SimpleTag();
                BeanUtils.copyProperties(tag, simpleTag, null);
                return simpleTag;
            }).collect(Collectors.toList());
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

    public static void show(ApiGeneratorConfig config) {
        Swagger swagger = new SwaggerParser().read(config.getInputSpec());
        for (Map.Entry<String, Model> stringModelEntry : swagger.getDefinitions().entrySet()) {

        }
        System.err.println(swagger.getDefinitions());
    }
}
