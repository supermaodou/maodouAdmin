package com.maodou.domain;

public class Result<T> {
    private int code; // 状态码
    private String msg; // 消息
    private T data; // 返回数据

    // 私有构造方法
    private Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    // 静态方法用于构建成功的结果
    public static <T> Result<T> success(T data) {
        return new Result<>(200, "success", data);
    }

    // 静态方法用于构建自定义成功的结果
    public static <T> Result<T> success(String msg, T data) {
        return new Result<>(200, msg, data);
    }

    // 静态方法用于构建失败的结果
    public static <T> Result<T> failure(int code, String msg) {
        return new Result<>(code, msg, null);
    }

    // 静态方法用于构建失败的结果并带有数据
    public static <T> Result<T> failure(int code, String msg, T data) {
        return new Result<>(code, msg, data);
    }

    // getter 和 setter 方法

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
