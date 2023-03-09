package com.fellaverse.backend.utils;

import com.fellaverse.backend.enumerator.ProductStatus;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.HashMap;
import java.util.Map;

public class ObjectToMapUtils {
    private static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /*
     * Object ---> Map<String,String>
     * */
    public static Map<String, Object> ObjectToMap(Object object) {
        Map<String, Object> map = new HashMap<>();
        try {
            Class<?> aClass = object.getClass();
            Field[] declaredFields = aClass.getDeclaredFields();
            for (Field field : declaredFields) {
                field.setAccessible(true);
                String name = field.getName();
                Object o = field.get(object);
                if (o instanceof LocalDateTime) {
                    DateTimeFormatter df = DateTimeFormatter.ofPattern(TIME_FORMAT);
                    String format = df.format((TemporalAccessor) o);
                    map.put(name, format);
                } else {
                    map.put(name, o);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return map;
    }

    // map<String,String>  ---->  Object

    public static Object MapToObj(Map<String, Object> map, Class clazz) {
        Object obj = null;
        try {
            obj = clazz.getDeclaredConstructor().newInstance();
            System.out.println("==================================");
            System.out.println("Entry Set = " + map.entrySet());
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                System.out.println("key = " + key);
                System.out.println("value = " + value);
                System.out.println("Value type = " + value.getClass());
                Field field = clazz.getDeclaredField(key);
                Class<?> type = field.getType();
                String typeName = type.getName();
//                System.out.println("Type name = " + typeName);
                field.setAccessible(true);
                switch (typeName) {
                    case "java.time.LocalDateTime" -> {
                        DateTimeFormatter df = DateTimeFormatter.ofPattern(TIME_FORMAT);
                        LocalDateTime parse = LocalDateTime.parse((String) value, df);
                        System.out.println("parse = " + parse);
                        field.set(obj, parse);
                    }
                    case "java.lang.Float" -> field.set(obj, ((Double) value).floatValue());
                    case "java.lang.Long" -> field.set(obj, ((Integer) value).longValue());
                    case "com.fellaverse.backend.enumerator.ProductStatus" ->
                            field.set(obj, ProductStatus.valueOf((String) value));
                    default -> field.set(obj, value);
                }
                System.out.println("==================================");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}


