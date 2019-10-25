package panzer.vor.generate.utils;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

/**
 * Created by JiaMing on 2019/4/29.
 */
public class U {
    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }

        if (obj instanceof String) {

            String str = (String) obj;
            return str.length() == 0 || "null".equals(str);

        }

        if (obj.getClass().isArray()) {

            Object[] array = (Object[]) obj;
            return array.length == 0;

        }

        if (obj instanceof Map) {

            Map map = (Map) obj;
            return map.isEmpty();

        }

        if (obj instanceof Collection) {

            Collection c = (Collection) obj;
            return c.isEmpty();

        }

        return false;
    }

    public static boolean noneEmpty(Object... objects) {
        for (Object o : objects) {
            if (isEmpty(o)) return false;
        }
        return true;
    }

    public static boolean anyEmpty(Object... objects) {
        for (Object o : objects) {
            if (isEmpty(o)) return true;
        }
        return false;
    }

    public static boolean allEmpty(Object... objects) {
        for (Object o : objects) {
            if (!isEmpty(o)) return false;
        }
        return true;
    }

    public static String emptyDefaultString(String source, String target) {
        return isEmpty(source) ? target : source;
    }

    public static Integer emptyDefaultInteger(Integer source, Integer target) {
        return isEmpty(source) ? target : source;
    }

    public static String uuid(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

}
