package com.sekorm.tools.tools.code.generator.maven.plugin;

import com.sekorm.tools.codegenerator.core.Generator;
import com.sekorm.tools.codegenerator.core.config.ApiConfig;
import io.swagger.models.Swagger;
import io.swagger.parser.SwaggerParser;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;

/**
 * @author duke
 */
@Mojo(name = "generate", defaultPhase = LifecyclePhase.GENERATE_SOURCES)
public class GeneratorMojo extends AbstractMojo {

    /**
     * OpenAPI 规则文件路径
     */
    @Parameter(name = "inputSpec", property = "code.api.generator.maven.plugin.inputSpec", required = true)
    private String inputSpec;

    /**
     * 生成文件的输出路径
     */
    @Parameter(name = "output", property = "code.api.generator.maven.plugin.output",
            defaultValue = "${project.basedir}/generated/")
    private String output;

    /**
     * 用于生成的 API 对象/类的包
     */
    @Parameter(name = "apiPackage", property = "code.api.generator.maven.plugin.apiPackage",
            defaultValue = "com.example.api")
    private String apiPackage;

    /**
     * 用于生成的 VO 模型对象/类的包
     */
    @Parameter(name = "modelPackage", property = "code.api.generator.maven.plugin.modelPackage",
            defaultValue = "com.example.vo")
    private String modelPackage;

    /**
     * 设置 VO 模型类和枚举的前缀
     */
    @Parameter(name = "modelNamePrefix", property = "code.api.generator.maven.plugin.modelNamePrefix")
    private String modelNamePrefix;

    /**
     * 设置 VO 模型类和枚举的后缀
     */
    @Parameter(name = "modelNameSuffix", property = "code.api.generator.maven.plugin.modelNameSuffix")
    private String modelNameSuffix;

    /**
     * 是否生成 API 接口（默认:true）
     */
    @Parameter(name = "generateApis", property = "code.api.generator.maven.plugin.generateApis")
    private Boolean generateApis = true;

    /**
     * 是否生成 VO 模型（默认:true）
     */
    @Parameter(name = "generateModels", property = "code.api.generator.maven.plugin.generateModels")
    private Boolean generateModels = true;

    /**
     * 指定在生成过程中是否跳过覆盖现有文件（默认:true）
     */
    @Parameter(name = "skipOverwrite", property = "code.api.generator.maven.plugin.skipOverwrite")
    private Boolean skipOverwrite = false;

    /**
     * 要使用的模板引擎的名称（默认:freemarker、beetl）
     */
    @Parameter(name = "engine", property = "code.api.generator.maven.plugin.engine")
    private String engine = "freemarker";

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        ApiConfig apiConfig = new ApiConfig()
                .setInputSpec(inputSpec)
                .setOutput(output)
                .setApiPackage(apiPackage)
                .setModelPackage(modelPackage)
                .setModelNamePrefix(modelNamePrefix)
                .setModelNameSuffix(modelNameSuffix)
                .setGenerateApis(generateApis)
                .setGenerateModels(generateModels)
                .setSkipOverwrite(skipOverwrite)
                .setEngine(engine);
        Generator.generator(apiConfig);
    }
}
