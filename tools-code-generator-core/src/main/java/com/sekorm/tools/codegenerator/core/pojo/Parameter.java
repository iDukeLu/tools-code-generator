package com.sekorm.tools.codegenerator.core.pojo;

public interface Parameter {

    String getIn();

    void setIn(String in);

    String getName();

    void setName(String name);

    String getDescription();

    void setDescription(String description);

    boolean getRequired();

    void setRequired(boolean required);

    String getPattern();

    void setPattern(String pattern);

    Boolean isReadOnly();

    void setReadOnly(Boolean readOnly);
}
