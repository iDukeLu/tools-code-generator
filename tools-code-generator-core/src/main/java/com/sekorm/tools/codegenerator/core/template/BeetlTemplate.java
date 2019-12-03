package com.sekorm.tools.codegenerator.core.template;

import java.io.Writer;
import java.util.Map;

/**
 * Beetl 模板类
 * @param <T> 模板类型
 * @author duke
 */
public class BeetlTemplate<T> implements Template<T> {

    private T t;

    @Override
    public void render(Map param, Writer out) {
        org.beetl.core.Template template = (org.beetl.core.Template) t;
        template.binding(param);
        template.renderTo(out);
    }
}
