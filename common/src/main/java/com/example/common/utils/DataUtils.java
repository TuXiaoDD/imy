package com.example.common.utils;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Supplier;



public final class DataUtils {

    /**
     * 判断对象是否Empty(null或元素为0)<br>
     * 实用于对如下对象做判断:String Collection及其子类 Map及其子类
     *
     * @param pObj 待检查对象
     * @return boolean 返回的布尔值
     */
    public static boolean isEmpty(Object pObj) {
        if (pObj == null) {
            return true;
        }
        if (pObj == "") {
            return true;
        }
        if (pObj instanceof String) {
            return ((String) pObj).trim().length() == 0;
        } else if (pObj instanceof Collection<?>) {
            return ((Collection<?>) pObj).size() == 0;
        } else if (pObj instanceof Map<?, ?>) {
            return ((Map<?, ?>) pObj).size() == 0;
        } else if (pObj instanceof Object[]) {
            return ((Object[]) pObj).length == 0;
        } else if (pObj instanceof boolean[]) {
            return ((boolean[]) pObj).length == 0;
        } else if (pObj instanceof byte[]) {
            return ((byte[]) pObj).length == 0;
        } else if (pObj instanceof char[]) {
            return ((char[]) pObj).length == 0;
        } else if (pObj instanceof short[]) {
            return ((short[]) pObj).length == 0;
        } else if (pObj instanceof int[]) {
            return ((int[]) pObj).length == 0;
        } else if (pObj instanceof long[]) {
            return ((long[]) pObj).length == 0;
        } else if (pObj instanceof float[]) {
            return ((float[]) pObj).length == 0;
        } else if (pObj instanceof double[]) {
            return ((double[]) pObj).length == 0;
        }
        return false;
    }



    /**
     * 判断对象是否为NotEmpty(!null或元素>0)<br>
     * 实用于对如下对象做判断:String Collection及其子类 Map及其子类
     *
     * @param pObj 待检查对象
     * @return boolean 返回的布尔值
     */
    public static boolean isNotEmpty(Object pObj) {
        return !isEmpty(pObj);
    }

    /**
     * 判断对象是否全部不为null
     */
    public static boolean isAllNotEmpty(Object... objects) {
        for (Object object : objects) {
            if (isEmpty(object)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAllEmpty(Object... objects) {
        for (Object object : objects) {
            if (isNotEmpty(object)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAnyEmpty(Object... objects) {
        for (Object object : objects) {
            if (isEmpty(object)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isAnyNotEmpty(Object... objects) {
        for (Object object : objects) {
            if (isNotEmpty(object)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 得到默认值
     *
     * @param value
     * @param defaultValue
     * @param <T>
     * @return
     */
    public static <T> T getDefaultValue(T value, T defaultValue) {
        return getDefaultValue(value, defaultValue, a -> a);
    }

    /**
     * 得到默认值, 如果原值不为空可以处理
     *
     * @param value
     * @param defaultValue
     * @param function
     * @param <T>
     * @return
     */
    public static <T, S> T getDefaultValue(S value, T defaultValue, Function<S, T> function) {
        return isNotEmpty(value)
                ? function.apply(value)
                : defaultValue;
    }

    /**
     * 得到默认值, 如果原值不为空可以处理
     *
     * @param value
     * @param defaultValue
     * @param function
     * @param <T>
     * @return
     */
    public static <T, S> T getDefaultValueSafety(S value, T defaultValue, Function<S, T> function) {
        try {
            return isNotEmpty(value)
                    ? function.apply(value)
                    : defaultValue;
        } catch (Exception e) {
            return defaultValue;
        }
    }

//
//    /**
//     * 将json转换为 map
//     *
//     * @param objStr
//     * @return
//     */
//    public static Map<String, String> json2Map(String objStr) {
//        Map<String, String> data = new HashMap<>();
//        JSONObject jsonObject = JSONObject.parseObject(objStr);
//        for (Object key : jsonObject.keySet()) {
//            String value = jsonObject.getString(key.toString());
//            if (isNotEmpty(value)) {
//                data.put(key.toString(), value);
//            }
//        }
//        return data;
//    }

    /**
     * 得到值, 如果原值不为空可以处理
     *
     * @param dataGetter   获取值
     * @param defaultValue 默认值
     * @param <T>          类型
     * @return 获取值 保证不会出现空指针
     */
    public static <T> T getValueSafety(Supplier<T> dataGetter, T defaultValue) {
        try {
            return dataGetter.get();
        } catch (Exception e) {
            return defaultValue;
        }
    }


//    public static long randomLongId() {
//        return -RandomUtils.nextLong(0, 99999999999999L);
//    }

    public static Long randomUnBoundLong() {

        return UUID.randomUUID().getMostSignificantBits() & Integer.MAX_VALUE;

    }

    public static boolean tryParseLong(String value) {
        try {
            Long.parseLong(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isAllNotNull(Object ... objects) {
        for (Object object : objects) {
            if (isEmpty(object)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 对象列表需要有一个不为空
     */
    public static boolean requireOneNotNull(Object ... objects) {
        for (Object object : objects) {
            if (Objects.nonNull(object)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检查一个对象的所有属性是不是都为空
     *
     * @param obj
     * @return
     * @throws IllegalAccessException
     */
    public static boolean checkObjFieldIsNull(Object obj) throws IllegalAccessException {
        for (Field f : obj.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            if (isNotEmpty(f.get(obj))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAnyEquals(Object obj, Object... tarObjs) {
        for (Object tarObj : tarObjs) {
            if (Objects.equals(obj, tarObj)) {
                return true;
            }
        }
        return false;
    }

}