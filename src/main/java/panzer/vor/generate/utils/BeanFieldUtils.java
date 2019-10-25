package panzer.vor.generate.utils;

import java.lang.reflect.Field;

/**
 * @outhor luozc
 * @create 2018-07-20
 */
public class BeanFieldUtils {
    /**
     * 将实例内String类型的成员变量值为空字符串时替换成null
     *
     * @param object
     */
    public static void trimToNull(Object object) {
        Field fields[] = object.getClass().getDeclaredFields();

        try {
            Field.setAccessible(fields, true);

            for (Field field : fields) {
                if (field.getType() == String.class) {
                    String value = (String) field.get(object);
                    if (value != null && value.length() == 0) {
                        field.set(object, null);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
