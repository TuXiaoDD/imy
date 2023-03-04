package com.example.common.utils;


import com.example.common.exception.BizException;
import com.example.common.response.ResultCode;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

public abstract class Assert {

    /**
     * Assert a boolean expression, throwing {@code BizException}
     */
    public static void isTrue(boolean expression, Integer errorCode, String errMessage) {
        if (!expression) {
            throw new BizException(ResultCode.BIZ_EXCEPTION, errMessage);
        }
    }


    /**
     * Assert a boolean expression, if expression is true, throwing {@code BizException}
     * <p>
     * for example
     *
     * <pre class="code">Assert.isFalse(i == 0, errorCode.B_ORDER_illegalNumber, "The order number can not be zero");</pre>
     * <p>
     * This is more intuitive than isTure.
     */
    public static void isFalse(boolean expression, String errorCode, String errMessage) {
        if (expression) {
            throw new BizException(ResultCode.BIZ_EXCEPTION, errMessage);
        }
    }

    public static void isTrue(boolean expression, String errMessage) {
        if (!expression) {
            throw new BizException(errMessage);
        }
    }

    public static void isTrue(boolean expression, Supplier<String> errMessage) {
        if (!expression) {
            throw new BizException(errMessage.get());
        }
    }

    public static void isTrue(boolean expression, RuntimeException runEx) {
        if (!expression) {
            throw runEx;
        }
    }

    public static void isFalse(boolean expression, String errMessage) {
        if (expression) {
            throw new BizException(errMessage);
        }
    }

    public static void isFalse(boolean expression, RuntimeException runEx) {
        if (expression) {
            throw runEx;
        }
    }

    public static void isTrue(boolean expression) {
        isTrue(expression, "[Assertion failed] Must be true");
    }

    public static void isFalse(boolean expression) {
        isFalse(expression, "[Assertion failed] Must be false");
    }

    public static void notEmpty(Object object, String errorCode, String errMessage) {
        if (DataUtils.isEmpty(object)) {
            throw new BizException(ResultCode.BIZ_EXCEPTION, errMessage);
        }
    }

    public static void notEmpty(Object object, String errMessage) {
        if (DataUtils.isEmpty(object)) {
            throw new BizException(errMessage);
        }
    }

    public static void notEmpty(Object object) {
        notEmpty(object, "[Assertion failed] Must not null");
    }

    public static void notEmpty(Object object, RuntimeException runEx) {
        if (DataUtils.isEmpty(object)) {
            throw runEx;
        }
    }

    public static void isAnyEmpty(String errMessage, Object... objects) {
        if (!DataUtils.isAnyEmpty(objects)) {
            throw new BizException(errMessage);
        }
    }

    public static void isAnyNotEmpty(String errMessage, Object... objects) {
        if (!DataUtils.isAnyNotEmpty(objects)) {
            throw new BizException(errMessage);
        }
    }

    public static void isEmpty(Object object, String errMessage) {
        if (DataUtils.isNotEmpty(object)) {
            throw new BizException(errMessage);
        }
    }

    public static void isEmpty(Object object) {
        isEmpty(object, "[Assertion failed] Must null");
    }

    public static void notNull(Object object) {
        if (Objects.isNull(object)) {
            throw new BizException("[Assertion failed] Must not null");
        }
    }

    public static void notNull(Object object, String errMessage) {
        if (Objects.isNull(object)) {
            throw new BizException(errMessage);
        }
    }

    public static void notNull(Object object, RuntimeException runEx) {
        if (Objects.isNull(object)) {
            throw runEx;
        }
    }

    public static void isNull(Object object) {
        if (Objects.nonNull(object)) {
            throw new BizException("[Assertion failed] Must is null");
        }
    }

    public static void isNull(Object object, String errMessage) {
        if (Objects.nonNull(object)) {
            throw new BizException(errMessage);
        }
    }

    public static void isEquals(Object a, Object b, String errMessage) {
        if (!Objects.equals(a, b)) {
            throw new BizException(errMessage);
        }
    }

    public static void isNotEquals(Object a, Object b, String errMessage) {
        if (Objects.equals(a, b)) {
            throw new BizException(errMessage);
        }
    }

    public static void justAssert(String errMessage) {
        throw new BizException(errMessage);
    }

    public static void isPresent(Optional<?> optional, String errMessage) {
        if (optional.isEmpty()) {
            throw new BizException(errMessage);
        }
    }

    public static void isEmpty(Optional<?> optional, String errMessage) {
        if (optional.isPresent()) {
            throw new BizException(errMessage);
        }
    }
}
