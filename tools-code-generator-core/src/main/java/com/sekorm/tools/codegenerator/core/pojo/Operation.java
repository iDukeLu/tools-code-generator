package com.sekorm.tools.codegenerator.core.pojo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author duke
 */
@Data
public class Operation {

    private List<String> tags;

    private String summary;

    private String description;

    private String operationId;

    private List<SimpleParameter> simpleParameters = new ArrayList();

    private Boolean deprecated;
}
