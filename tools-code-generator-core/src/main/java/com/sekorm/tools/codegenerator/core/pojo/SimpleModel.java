package com.sekorm.tools.codegenerator.core.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author duke
 */
@Data
@Accessors(chain = true)
public class SimpleModel {

    private String name;

    private String description;
}
