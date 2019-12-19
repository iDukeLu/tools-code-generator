package com.sekorm.tools.codegenerator.core.util;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.Data;
import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.core.Converter;

import java.time.Duration;
import java.time.Instant;
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
            .initialCapacity(16) // 初始大小
            .maximumSize(32) // 最大数量
            .build();

    private BeanUtils() {}


    /**
     *
     * @param sources
     * @param targets
     * @param source
     * @param target
     * @param converter
     * @param <S>
     * @param <T>
     */
    public  static <S, T> void copyPropertiesBatch(List<S> sources, List<T> targets, Class<S> source, Class<T> target, Converter converter) {
        targets.clear();
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
     * @param source 来源对象
     * @param target 目标对象
     * @param converter 属性转换器
     */
    public static void copyProperties(Object source, Object target, Converter converter) {
        beanUtils.copy(source, target, converter);
    }

    @Override
    public void copy(Object source, Object target, Converter converter) {
        BeanCopier copier = getBeanCopier(source, target);
        copier.copy(source, target, converter == null ? genericConverter : converter);
    }

    /**
     * 获取 BeanCopier 实例，降低 BeanCopier 实例创建的开销
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
         * @param value 源对象字段类型
         * @param target 目标对象字段对应 setter 方法
         * @param context 目标对象字段类型
         * @return 转换后的源对象字段类型
         */
        @Override
        public Object convert(Object value, Class target, Object context) {
            return value;
        }
    }
    /*********************************************************************************************/

    public static void main(String[] args) {

        SourceUser sourceUser = new SourceUser();
        sourceUser = new SourceUser();
        sourceUser.setUsername("xiaoming");
        sourceUser.setPassword("" + UUID.randomUUID());
        sourceUser.setAge(18);
        sourceUser.setEmail("" + UUID.randomUUID());
        sourceUser.setPhone(13333);
        TargeUser targeUser = new TargeUser();




        // spring BeanUtils
        Instant st1 = Instant.now();
        for (int i = 0; i < 1; i++) {
            org.springframework.beans.BeanUtils.copyProperties(sourceUser, targeUser);
        }
        Instant en1 = Instant.now();
        System.err.println("spring: " + Duration.between(st1, en1).toMillis() + "ms");

        // customer BeanCopier
        Instant st2 = Instant.now();
        for (int i = 0; i < 1; i++) {
            BeanUtils.copyProperties(sourceUser, targeUser, null);
        }
        Instant en2 = Instant.now();
        System.err.println("cglib: " + Duration.between(st2, en2).toMillis() + "ms");

    }
}

@Data
class SourceUser {
    private String username;
    private String password;
    private Integer phone;
    private int age;
    private String email;
}

@Data
class TargeUser {
    private String username;
    private String password;
    private int phone;
    private Integer age;
    private String email;
}