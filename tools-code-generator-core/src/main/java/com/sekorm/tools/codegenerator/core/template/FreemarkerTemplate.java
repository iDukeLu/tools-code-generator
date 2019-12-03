package com.sekorm.tools.codegenerator.core.template;

import freemarker.template.TemplateException;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

/**
 * Freemarker 模板类
 * @param <T> 模板类型
 * @author duke
 */
@Data
@Accessors(chain = true)
public class FreemarkerTemplate<T> implements Template<T> {

    private T t;

    @Override
    public void render(Map param, Writer out) {
        freemarker.template.Template template = (freemarker.template.Template) t;
        try {
            template.process(param, out);
        } catch (TemplateException | IOException e) {
            e.printStackTrace(); // TODO 打印日志
        }
    }
}
