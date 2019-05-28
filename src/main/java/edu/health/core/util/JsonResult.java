package edu.health.core.util;

import java.io.Serializable;

/**
 * @author 执笔
 * 返回JSON结果
 */
public class JsonResult<T> implements Serializable {

    private final static Integer SUCCESS = 200;
    private final static Integer FAIL = 500;


    private String message;
    private Integer code;
    private T data;

    private JsonResult(String message, Integer code, T data) {
        this.message = message;
        this.code = code;
        this.data = data;
    }

    /**
     * 请求成功
     *
     * @param message 提示信息
     * @param data    返回数据
     * @param <T>
     * @return
     */
    public static <T> JsonResult<T> success(String message, T data) {
        return build(SUCCESS, message, data);
    }

    /**
     * 请求成功
     *
     * @param message 成功提示信息
     * @param <T>
     * @return
     */
    public static <T> JsonResult<T> success(String message) {
        return build(SUCCESS, message, null);
    }

    /**
     * 请求成功
     *
     * @param data 返回的数据
     * @param <T>
     * @return
     */
    public static <T> JsonResult<T> success(T data) {
        return build(SUCCESS, "success", data);
    }

    /**
     * 请求成功
     *
     * @param <T>
     * @return
     */
    public static <T> JsonResult<T> success() {
        return build(SUCCESS, "success", null);
    }

    /**
     * 请求失败
     *
     * @param message 失败信息
     * @param <T>
     * @return
     */
    public static <T> JsonResult<T> fail(String message) {
        return build(FAIL, message, null);
    }


    public static <T> JsonResult<T> build(Integer code, String message, T data) {
        return new JsonResult<>(message, code, data);
    }

    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }

    public T getData() {
        return data;
    }
}
