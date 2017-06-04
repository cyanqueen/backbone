package org.backbone.core.util;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author bianliang (06/04/2017)
 */
public class ClassUtils {

    private static Map<Class<?>, Map<String, Field>> FIELD_CACHE = new ConcurrentHashMap<Class<?>, Map<String, Field>>(
            new IdentityHashMap<Class<?>, Map<String, Field>>());

    /**
     * 获取某类的全部字段
     *
     * @param cls
     * @return
     */
    public static Collection<Field> getAllFields(Class<?> cls) {
        return getAllFieldsMap(cls).values();
    }

    public static Map<String, Field> getAllFieldsMap(Class<?> cls) {
        Map<String, Field> map = FIELD_CACHE.get(cls);
        if (map == null) {
            map = getAllInheritedField(cls);
            FIELD_CACHE.put(cls, map);
        }
        return map;
    }

    private static Map<String, Field> getAllInheritedField(Class<?> cls) {
        Map<String, Field> map = new HashMap<String, Field>();
        if (cls == Object.class) return map;
        Field[] fields = cls.getDeclaredFields();
        if (fields != null && fields.length > 0) {
            for (Field field : fields) {
                map.put(field.getName(), field);
            }
        }
        Map<String, Field> superMap = getAllInheritedField(cls.getSuperclass());
        if (!superMap.isEmpty()) {
            map.putAll(superMap);
        }
        return map;
    }
}
