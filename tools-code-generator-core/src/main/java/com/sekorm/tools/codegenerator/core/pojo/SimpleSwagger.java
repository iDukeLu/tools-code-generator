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
@Accessors(chain = true)
public class SimpleSwagger {

    private List<SimpleTag> tags = new ArrayList<>();

    private List<SimplePath> paths = new ArrayList<>();

    private List<SimpleModel> definitions = new ArrayList<>();
}
