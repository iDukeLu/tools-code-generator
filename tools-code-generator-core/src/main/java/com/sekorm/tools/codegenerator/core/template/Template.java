package com.sekorm.tools.codegenerator.core.template;

import java.io.Writer;
import java.util.Map;

/**
 * 模板统一接口
 * @param <T> 模板类型
 * @author duke
 */
public interface Template<T> {
    /**
     * 渲染模版并写出到指定文件
     * @param param 渲染的参数
     * @param out 输出路径
     */
    void render(Map param, Writer out);
}
