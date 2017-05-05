package com.kun.randomusers.data.callback;

/**
 * @author Julio Kun
 * <p>
 *     A base callback to send back information after the web service is executed.
 * </p>
 */

public interface BaseCallback<T> {
    void onError();
    void onDataAvailable(T data);
}
