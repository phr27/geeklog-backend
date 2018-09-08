package com.geeklog.common.util;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author 潘浩然
 * 创建时间 2018/09/07
 * 功能：响应实体
 */
public class ResponseEntity<T> {

    /**
     * @author 潘浩然
     * 创建时间 2018/09/07
     * 功能：响应体的状态码，在 http 状态码的基础上扩展
     */
    private int code;

    /**
     * @author 潘浩然
     * 创建时间 2018/09/07
     * 功能：响应体消息
     */
    private String message;

    /**
     * @author 潘浩然
     * 创建时间 2018/09/07
     * 功能：响应体携带的数据
     */
    private T data;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    private ResponseEntity() {
    }

    private ResponseEntity(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/07
     * 功能：通过状态码，消息和数据构建一个新的响应体
     * @param code 状态码
     * @param message 消息
     * @param data 数据
     */
    public static <T> ResponseEntity<T> build(int code, String message, T data) {
        return new ResponseEntity<>(code, message, data);
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/07
     * 功能：通过状态码和消息构建一个新的响应体，没有数据
     * @param code 状态码
     * @param message 消息
     */
    public static ResponseEntity<Object> build(int code, String message) {
        return new ResponseEntity<>(code, message, null);
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/07
     * 功能：通过消息和数据构建一个状态码为200的响应体
     * @param message 消息
     * @param data 数据
     */
    public static <T> ResponseEntity<T> ok(String message, T data) {
        return new ResponseEntity<>(200, message, data);
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/07
     * 功能：通过消息构建一个状态码为200的响应体，没有数据
     * @param message 消息
     */
    public static ResponseEntity<Object> ok(String message) {
        return new ResponseEntity<>(200, message, null);
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/07
     * 功能：通过消息和数据构建一个状态码为201的响应体
     * @param message 消息
     * @param data 数据
     */
    public static <T> ResponseEntity<T> resourceCreated(String message, T data) {
        return new ResponseEntity<>(201, message, data);
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/07
     * 功能：通过消息构建一个状态码为201的响应体，没有数据
     * @param message 消息
     */
    public static ResponseEntity<Object> resourceCreated(String message) {
        return new ResponseEntity<>(201, message, null);
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/07
     * 功能：通过消息和数据构建一个状态码为400的响应体
     * @param message 消息
     * @param data 数据
     */
    public static <T> ResponseEntity<T> badRequest(String message, T data) {
        return new ResponseEntity<>(400, message, data);
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/07
     * 功能：通过消息构建一个状态码为400的响应体，没有数据
     * @param message 消息
     */
    public static ResponseEntity<Object> badRequest(String message) {
        return new ResponseEntity<>(400, message, null);
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/07
     * 功能：通过消息和数据构建一个状态码为401的响应体
     * @param message 消息
     * @param data 数据
     */
    public static <T> ResponseEntity<T> unauthorized(String message, T data) {
        return new ResponseEntity<>(401, message, data);
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/07
     * 功能：通过消息构建一个状态码为401的响应体，没有数据
     * @param message 消息
     */
    public static ResponseEntity<Object> unauthorized(String message) {
        return new ResponseEntity<>(401, message, null);
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/07
     * 功能：通过消息和数据构建一个状态码为403的响应体
     * @param message 消息
     * @param data 数据
     */
    public static <T> ResponseEntity<T> forbidden(String message, T data) {
        return new ResponseEntity<>(403, message, data);
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/07
     * 功能：通过消息构建一个状态码为403的响应体，没有数据
     * @param message 消息
     */
    public static ResponseEntity<Object> forbidden(String message) {
        return new ResponseEntity<>(403, message, null);
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/07
     * 功能：通过消息和数据构建一个状态码为404的响应体
     * @param message 消息
     * @param data 数据
     */
    public static <T> ResponseEntity<T> notFound(String message, T data) {
        return new ResponseEntity<>(404, message, data);
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/07
     * 功能：通过消息构建一个状态码为404的响应体，没有数据
     * @param message 消息
     */
    public static ResponseEntity<Object> notFound(String message) {
        return new ResponseEntity<>(404, message, null);
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/07
     * 功能：通过消息和数据构建一个状态码为500的响应体
     * @param message 消息
     * @param data 数据
     */
    public static <T> ResponseEntity<T> serverInnerError(String message, T data) {
        return new ResponseEntity<>(500, message, data);
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/07
     * 功能：通过消息构建一个状态码为500的响应体，没有数据
     * @param message 消息
     */
    public static ResponseEntity<Object> serverInnerError(String message) {
        return new ResponseEntity<>(500, message, null);
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/07
     * 功能：判断响应体的状态码是否是 2xx（是否是一次成功的请求）
     */
    @JsonIgnore
    public boolean is2xxSuccessful() {
        return code >= 200 && code < 300;
    }

}
