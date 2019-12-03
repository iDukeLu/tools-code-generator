package com.sekorm.tools.codegenerator.core.engine;

import com.sekorm.tools.codegenerator.core.template.Template;

/**
 * 模版引擎统一接口
 * @author duke
 */
public interface TemplateEngine {

    /**
     * 初始化模版引擎
     * @return 模版引擎
     */
    TemplateEngine init();

    /**
     * 读取指定模版
     * @param fileName 模版文件名称
     * @param <T> 模版类型范型
     * @return 模版
     */
    <T> Template<T> readTemplate(String fileName);
}
