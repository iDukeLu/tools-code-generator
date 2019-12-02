package com.sekorm.tools.codegenerator.core;

import java.io.Writer;
import java.util.Map;

/**
 * 模版引擎接口
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
     * @param templateType 模版类型 Class 类
     * @param <T> 模版类型范型
     * @return 模版
     */
    <T>T readTemplate(String fileName, Class<T> templateType);

    /**
     * 渲染模版并写出文件
     * @param param 渲染的参数
     * @param out 输出流
     */
    void renderAndWriteFile(Map param, Writer out);

    /**
     * 关闭模版引擎
     */
    void close();
}
