package com.geeklog.common.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.geeklog.common.exception.ValidatorException;
import org.apache.commons.lang3.StringUtils;

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

        Field[] domainFields = domain.getClass().getDeclaredFields();
        List<Field> dtoFields = getAllFields(dtoClass);
        int j;
        for (int i = 0; i < domainFields.length; i++) {
            try {
                Field targetField = null;
                for (j = 0; j < dtoFields.size(); j++) {
                    if (StringUtils.equals(domainFields[i].getName(), dtoFields.get(j).getName())) {
                        targetField = dtoFields.get(j);
                        break;
                    }
                }
                if (targetField == null || !targetField.getType().isAssignableFrom(domainFields[i].getType())) {
                    continue;
                }
                domainFields[i].setAccessible(true);
                targetField.setAccessible(true);
                targetField.set(dto, domainFields[i].get(domain));
            } catch (Throwable e) {
                throw ValidatorException.unexpected(
                        "Converter.domainToDTO throw " + e.getClass().getSimpleName() + ": " + e.getMessage());
            }
        }

        return dto;
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/13
     * 功能：获取该类型的所有字段，包括父类型的字段
     */
    private static List<Field> getAllFields(Class<?> clazz) {
        List<Field> fields = new ArrayList<>();
        Class<?> currentClass = clazz;
        int i;
        while (currentClass != null) {
            Field[] currentFields = currentClass.getDeclaredFields();
            for (i = 0; i < currentFields.length; i++) {
                fields.add(currentFields[i]);
            }
            currentClass = currentClass.getSuperclass();
        }

        return fields;
    }
}
