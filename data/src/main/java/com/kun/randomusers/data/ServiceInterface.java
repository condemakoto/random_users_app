package com.kun.randomusers.data;

import com.kun.randomusers.data.entity.UserPageEntity;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * @author Julio Kun
 * @version 0.1
 * <p>
 *    Interface that describes the web service.
 * </p>
 */

public interface ServiceInterface {

    @GET("/api/")
    //void getUsers(@Query("page") int pageNumber, @Query("seed") String seed, @Query("results") int results,  Callback<UserPageEntity> callback);
    Observable<UserPageEntity> getUsers(@Query("page") int pageNumber, @Query("seed") String seed, @Query("results") int results);

}
