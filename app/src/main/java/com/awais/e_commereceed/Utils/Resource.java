package com.awais.e_commereceed.Utils;

public class Resource<T> {
    public enum Status {
        SUCCESS,
        ERROR,
        LOADING,
        UNSPECIFIED
    }

    private Status status;
    private T data;
    private String message;

    public Resource(Status status, T data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> Resource<T> Success(T data) {
        return new Resource<>(Status.SUCCESS, data, null);
    }

    public static <T> Resource<T> Error(String message) {
        return new Resource<>(Status.ERROR, null, message);
    }

    public static <T> Resource<T> Loading() {
        return new Resource<>(Status.LOADING, null, null);
    }

    public static <T> Resource<T> Unspecified() {
        return new Resource<>(Status.UNSPECIFIED, null, null);
    }

    public Status getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }
}
