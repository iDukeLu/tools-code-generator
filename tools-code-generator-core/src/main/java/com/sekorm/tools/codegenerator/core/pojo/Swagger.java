package com.sekorm.tools.codegenerator.core.pojo;

import io.swagger.models.Model;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

/**
 * @author duke
 */
@Data
@Accessors(chain = true)
public class Swagger {

    private String swagger = "2.0";

    private List<Tag> tags;

    private List<Path> paths;

    private Map<String, Model> definitions;
}
