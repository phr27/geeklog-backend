package com.geeklog.common.util;

import java.lang.reflect.Field;

import com.geeklog.common.exception.ValidatorException;

/**
 * @author 潘浩然
 * 创建时间 2018/09/11
 * 功能：实现 domain 类对象和 dto 对象的转换
 */
public class Converter {

    /**
     * @author 潘浩然
     * 创建时间 2018/09/11
     * 功能：domain 对象转 dto 对象
     */
    public static <T> T domainToDTO(Object domain, Class<T> dtoClass) {
        Validator.notNull(domain, ValidatorException.unexpected("Converter.domainToDTO(domain cannot be null)"));
        Validator.notNull(dtoClass, ValidatorException.unexpected("Converter.domainToDTO(dtoClass cannot be null)"));

        T dto;
        try {
            dto = dtoClass.newInstance();
        } catch (InstantiationException e) {
            throw ValidatorException.unexpected("Converter.domainToDTO(dtoClass 不可实例化)");
        } catch (IllegalAccessException e) {
            throw ValidatorException.unexpected("Converter.domainToDTO(dtoClass 没有公有无参构造器)");
        }

        Field[] fields = domain.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            try {
                Field targetField = dtoClass.getDeclaredField(fields[i].getName());
                if (!targetField.getType().isAssignableFrom(fields[i].getType())) {
                    continue;
                }
                fields[i].setAccessible(true);
                targetField.setAccessible(true);
                targetField.set(dto, fields[i].get(domain));
            } catch (NoSuchFieldException e) {
            } catch (Throwable e) {
                throw ValidatorException.unexpected(
                        "Converter.domainToDTO throw " + e.getClass().getSimpleName() + ": " + e.getMessage());
            }
        }

        return dto;
    }
}
