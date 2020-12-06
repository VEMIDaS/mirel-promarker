package jp.vemi.framework.util;

public class InstanceUtil {

    @SuppressWarnings("unchecked")
    public static <T> T forceCast(Object object) {
        return (T) object;
    }

    public static <T> T newInstance(Class<T> clazz) {
        T result = null;
        try {
            result = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return result;
    }
}
