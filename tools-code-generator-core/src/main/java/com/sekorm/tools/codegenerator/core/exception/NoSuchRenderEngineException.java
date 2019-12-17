package com.sekorm.tools.codegenerator.core.exception;

/**
 * 找不到指定渲染引擎异常
 * @author duke
 */
public class NoSuchRenderEngineException extends RuntimeException {

    public NoSuchRenderEngineException() {
        super();
    }

    public NoSuchRenderEngineException(String s) {
        super(s);
    }
}
