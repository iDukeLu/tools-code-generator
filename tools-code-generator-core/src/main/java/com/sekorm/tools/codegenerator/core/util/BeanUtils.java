package com.sekorm.tools.codegenerator.core.util;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.Data;
import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.core.Converter;
import org.checkerframework.checker.units.qual.C;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Bean 工具类
 * @author duke
 */
public class BeanUtils extends BeanCopier {

    /**
     * Bean 工具类
     */
    private static BeanUtils beanUtils = new BeanUtils();

    /**
     * 通用属性转换器
     */
    private static GenericConverter genericConverter = new GenericConverter();

    /**
     * BeanCopier 缓存
     */
    private static Cache<String, BeanCopier> cache = Caffeine.newBuilder()
            .initialCapacity(16)
            .maximumSize(32)
            .build();

    private BeanUtils() { }

    /**
     * 批量拷贝 pojo 属性
     *
     * @param sources   来源对象集合
     * @param targets   目标对象集合
     * @param target    目标对象
     * @param <S>       来源对象范型
     * @param <T>       目标对象范型
     */
    public static <S, T> void copyPropertiesBatch(Collection<S> sources, Collection<T> targets, Class<T> target) {
        if (targets.size() != 0) {
            targets.clear();
        }
        targets.addAll(sources.stream().map(s -> {
            try {
                T t = target.newInstance();
                copyProperties(s, t);
                return t;
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList()));
    }

    /**
     * 批量拷贝 pojo 属性
     *
     * @param sources   来源对象集合
     * @param targets   目标对象集合
     * @param target    目标对象
     * @param converter 属性转换器
     * @param <S>       来源对象范型
     * @param <T>       目标对象范型
     */
    public static <S, T> void copyPropertiesBatch(Collection<S> sources, Collection<T> targets, Class<T> target, Converter converter) {
        if (targets.size() != 0) {
            targets.clear();
        }
        targets.addAll(sources.stream().map(s -> {
            try {
                T t = target.newInstance();
                copyProperties(s, t, converter);
                return t;
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList()));
    }

    /**
     * 拷贝 pojo 属性
     *
     * @param source    来源对象
     * @param target    目标对象
     */
    public static void copyProperties(Object source, Object target) {
        beanUtils.copy(source, target, null);
    }

    /**
     * 拷贝 pojo 属性
     *
     * @param source    来源对象
     * @param target    目标对象
     * @param converter 属性转换器
     */
    public static void copyProperties(Object source, Object target, Converter converter) {
        beanUtils.copy(source, target, converter);
    }

    /**
     * 拷贝 pojo 属性，使用 CGLIB 操作字节码进行高效拷贝
     *
     * @param source    来源对象
     * @param target    目标对象
     * @param converter 属性转换器
     */
    @Override
    public void copy(Object source, Object target, Converter converter) {
        BeanCopier copier = getBeanCopier(source, target);
        copier.copy(source, target, converter == null ? genericConverter : converter);
    }

    /**
     * 获取 BeanCopier 实例，降低 BeanCopier 实例创建的开销
     *
     * @param source 来源对象
     * @param target 目标对象
     * @return BeanCopier 实例
     */
    private BeanCopier getBeanCopier(Object source, Object target) {
        // 获取 Class 对象
        Class<?> sourceClass = source.getClass();
        Class<?> targetClass = target.getClass();

        // 从缓存中获取
        BeanCopier copier = cache.getIfPresent(sourceClass.getName() + targetClass.getName());
        if (copier == null) {
            copier = BeanCopier.create(sourceClass, targetClass, true);
        }
        return copier;
    }

    /**
     * 通用属性转换器
     */
    static class GenericConverter implements Converter {

        /**
         * 自定义属性转换
         *
         * @param value   源对象字段类型
         * @param target  目标对象字段对应 setter 方法
         * @param context 目标对象字段类型
         * @return 转换后的源对象字段类型
         */
        @Override
        public Object convert(Object value, Class target, Object context) {
            return value;
        }
    }
}