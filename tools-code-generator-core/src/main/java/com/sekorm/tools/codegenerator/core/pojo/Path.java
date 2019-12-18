package com.sekorm.tools.codegenerator.core.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author duke
 */
@Data
@Accessors(chain = true)
public class Path {

    private String name;

    private String method;

    private List<Tag> tags;

    private String summary;

    private List<Parameter> parameters;


}
