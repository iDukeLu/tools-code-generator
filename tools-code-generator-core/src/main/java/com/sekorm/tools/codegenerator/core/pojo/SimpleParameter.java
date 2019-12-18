package com.sekorm.tools.codegenerator.core.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author duke
 */
@Data
@Accessors(chain = true)
public class SimpleParameter {

    private String in;

    private String name;

    private String type;

    private String description;

    private String required;

}
