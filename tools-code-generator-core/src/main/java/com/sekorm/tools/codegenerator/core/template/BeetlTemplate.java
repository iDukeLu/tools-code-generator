package com.sekorm.tools.codegenerator.core.template;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Writer;
import java.util.Map;

/**
 * Beetl 模板类
 * @author duke
 */
@Data
@Accessors(chain = true)
public class BeetlTemplate implements Template {

    private org.beetl.core.Template template;

    @Override
    public void render(Map param, Writer out) {
        template.binding(param);
        template.renderTo(out);
    }
}
