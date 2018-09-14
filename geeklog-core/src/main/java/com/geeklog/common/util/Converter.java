package com.geeklog.common.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
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
     * 功能：object 对象转 T 类型，类型兼容名字相同的字段值复制
     * @param object
     * @param clazz
     */
    public static <T> T convert(Object object, Class<T> clazz) {
        Validator.notNull(object, ValidatorException.unexpected("Converter.convert(object cannot be null)"));
        Validator.notNull(clazz, ValidatorException.unexpected("Converter.convert(clazz cannot be null)"));

        T target;
        try {
            target = clazz.newInstance();
        } catch (InstantiationException e) {
            throw ValidatorException.unexpected("Converter.convert(clazz 不可实例化)");
        } catch (IllegalAccessException e) {
            throw ValidatorException.unexpected("Converter.convert(clazz 没有公有无参构造器)");
        }

        Field[] objectFields = object.getClass().getDeclaredFields();
        List<Field> clazzFields = getAllFields(clazz);
        int j;
        for (int i = 0; i < objectFields.length; i++) {
            try {
                Field targetField = null;
                for (j = 0; j < clazzFields.size(); j++) {
                    if (StringUtils.equals(objectFields[i].getName(), clazzFields.get(j).getName())) {
                        targetField = clazzFields.get(j);
                        break;
                    }
                }
                if (targetField == null || !targetField.getType().isAssignableFrom(objectFields[i].getType())) {
                    continue;
                }
                objectFields[i].setAccessible(true);
                targetField.setAccessible(true);
                targetField.set(target, objectFields[i].get(object));
            } catch (Throwable e) {
                throw ValidatorException.unexpected(
                        "Converter.convert throw " + e.getClass().getSimpleName() + ": " + e.getMessage());
            }
        }

        return target;
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
