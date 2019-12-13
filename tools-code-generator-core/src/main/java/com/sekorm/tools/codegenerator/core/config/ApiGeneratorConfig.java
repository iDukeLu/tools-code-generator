package com.sekorm.tools.codegenerator.core.config;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author duke
 */
@Data
@Accessors(chain = true)
public class ApiGeneratorConfig {
    /**
     * OpenAPI 规则文件路径
     */
    private String inputSpec;

    /**
     * 生成文件的输出路径
     */
    private String output;

    /**
     * 用于生成的 API 对象/类的包
     */
    private String apiPackage;

    /**
     * 用于生成的模型对象/类的包
     */
    private String modelPackage;

    /**
     * 设置模型类和枚举的前缀
     */
    private String modelNamePrefix;

    /**
     * 设置模型类和枚举的后缀
     */
    private String modelNameSuffix;

    /**
     * 是否生成 API 接口（默认:true）
     */
    private boolean generateApis;

    /**
     * 是否生成 VO 模型（默认:true）
     */
    private boolean generateModels;

    /**
     * 指定在生成过程中是否跳过覆盖现有文件（默认:true）
     */
    private boolean skipOverwrite;

    /**
     * 要使用的模板引擎的名称（默认:freemarker、beetl）
     */
    private String engine;
}
