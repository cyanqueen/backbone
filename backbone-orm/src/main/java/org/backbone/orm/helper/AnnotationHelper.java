package org.backbone.orm.helper;

import org.backbone.core.annotation.Column;
import org.backbone.core.annotation.Component;
import org.backbone.core.annotation.PrimaryKey;
import org.backbone.core.util.ClassUtils;
import org.backbone.core.util.SqlUtils;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author bianliang (06/04/2017)
 */
public class AnnotationHelper {

    private static Map<Class<?>, List<AnnotationHolder>> ANNOTATIONHOLDER_LIST_CACHE;

    private static Map<Class<?>, Map<String, AnnotationHolder>> ANNOTATIONHOLDER_CACHES;

    static {
        ANNOTATIONHOLDER_LIST_CACHE = new ConcurrentHashMap<Class<?>, List<AnnotationHolder>>(
                new IdentityHashMap<Class<?>, List<AnnotationHolder>>());
        ANNOTATIONHOLDER_CACHES = new ConcurrentHashMap<Class<?>, Map<String, AnnotationHolder>>(
                new IdentityHashMap<Class<?>, Map<String, AnnotationHolder>>());
    }

    public static List<AnnotationHolder> getAnnotationHolders(Class<?> cls) {
        List<AnnotationHolder> list = ANNOTATIONHOLDER_LIST_CACHE.get(cls);
        if (list == null) {
            Map<String, AnnotationHolder> map = getAnnotationHoldersMap(cls);
            list = new ArrayList<AnnotationHolder>(map.values());
            ANNOTATIONHOLDER_LIST_CACHE.put(cls, list);
        }
        return list;
    }

    private static Map<String, AnnotationHolder> getAnnotationHoldersMap(Class<?> cls) {
        Map<String, AnnotationHolder> map = ANNOTATIONHOLDER_CACHES.get(cls);
        if (map == null) {
            map = getAllColumnAnnotationHoldersInternal(cls, null);
            ANNOTATIONHOLDER_CACHES.put(cls, map);
        }
        return map;
    }

    private static Map<String, AnnotationHolder> getAllColumnAnnotationHoldersInternal(Class<?> cls, String ognl) {
        Collection<Field> fields = ClassUtils.getAllFields(cls);
        Map<String, AnnotationHolder> map = new HashMap<String, AnnotationHolder>();
        if (fields != null && fields.size() > 0) {
            boolean duplicatedKey = false;
            for (Field field : fields) {
                Column column = field.getAnnotation(Column.class);
                PrimaryKey primaryKey = field.getAnnotation(PrimaryKey.class);
                if (column != null) {
                    AnnotationHolder holder = new AnnotationHolder(field, column, primaryKey, null);
                    map.put(field.getName(), holder);
                    if (primaryKey != null) {
                        if (duplicatedKey) {
                            throw new IllegalArgumentException("Duplicated primary key @PrimaryKey in(" + cls.getCanonicalName() + ")");
                        }
                        duplicatedKey = true;
                    }
                    String refField = column.referenceField();
                    boolean found = false;
                    if (SqlUtils.isNotBlank(refField)) {
                        Class<?> refClass = field.getDeclaringClass();
                        Collection<Field> refFields = ClassUtils.getAllFields(refClass);
                        if (refFields != null && refFields.size() > 0) {
                            for (Field f : refFields) {
                                if (refField.equals(f.getName())) {
                                    found = true;
                                    break;
                                }
                            }
                        }
                        if (!found) {
                            throw new IllegalArgumentException("Reference field specified to '" + column.referenceField() + "' but no such field found in " + refClass);
                        }
                    }
                } else {
                    Component component = field.getAnnotation(Component.class);
                    if (component != null) {
                        // todo Component
                    }
                }
            }
        }
        return map;
    }

    public static String getColumnName(AnnotationHolder holder) {
        String columnName = holder.getColumn().value();
        if (SqlUtils.isBlank(columnName) || "#".equals(columnName)) {
            columnName = holder.getField().getName();
        }
        if (holder.getOgnl() != null) {
            columnName = holder.getOgnl() + "." + columnName;
        }
        return SqlUtils.hump(columnName);
    }

    public static AnnotationHolder getAnnotationHolder(String field, Class<?> beanType) {
        return null;
    }
}
