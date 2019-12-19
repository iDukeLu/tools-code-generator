package com.sekorm.tools.codegenerator.core.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author duke
 */
@Data
public class SimplePath {

    private String value;

    private String method;

    private List<SimpleTag> tags;

    private String summary;

    private List<SimpleParameter> simpleParameters;

    private Boolean deprecated;
}
