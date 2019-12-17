package com.sekorm.tools.codegenerator.core.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author duke
 */
@Data
@Accessors(chain = true)
public class TagBO {
    private String name;

    private String Description;

    private List<PathBO> paths;
}
