package com.sekorm.tools.codegenerator.core.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * @author duke
 */
@Data
@Accessors(chain = true)
public class Operation {

    private List<String> tags;

    private String summary;

    private String description;

    private String operationId;

    private List<Parameter> parameters = new ArrayList();

    private Boolean deprecated;
}
