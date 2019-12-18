package com.sekorm.tools.codegenerator.core.util;

import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.core.Converter;

import java.math.BigDecimal;

/**
 * Bean 工具类
 * @author duke
 */
public class BeanUtils extends BeanCopier {

    public static void copy(Object source, Object target) {

    }


    @Override
    public void copy(Object source, Object target, Converter converter) {
        Class<?> sourceClass = source.getClass();
        Class<?> targetClass = target.getClass();
        String key = sourceClass.getName() + targetClass.getName();

        BeanCopier copier = BeanCopier.create(sourceClass, target.getClass(), true);
        converter = converter == null ? new GenericConverter() : converter;
        copier.copy(source, target, converter);
    }

    static class GenericConverter implements Converter {

        /**
         * 自定义属性转换
         * @param value 源对象字段类型
         * @param target 目标对象字段对应 setter 方法
         * @param context 目标对象字段类型
         * @return
         */
        @Override
        public Object convert(Object value, Class target, Object context) {
            if (value instanceof Integer) {
                return (Integer) value;
            } else if (value instanceof BigDecimal) {
                BigDecimal bd = (BigDecimal) value;
                return bd.toPlainString();
            }
            return null;
        }
    }
}
