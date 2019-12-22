package com.sekorm.tools.codegenerator.core.pojo;

import io.swagger.models.Model;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author duke
 */
@Data
public class SimpleSwagger {

    private List<SimpleTag> tags;

    private List<SimplePath> paths;

    private List<SimpleModel> definitions;
}
