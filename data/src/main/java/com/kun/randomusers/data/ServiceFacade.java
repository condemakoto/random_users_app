package com.kun.randomusers.data;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.kun.randomusers.data.callback.BaseCallback;
import com.kun.randomusers.data.entity.UserPageEntity;
import com.squareup.okhttp.OkHttpClient;

import java.lang.reflect.Type;
import java.util.Calendar;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

/**
 * @author Julio Kun
 * @version 0.1
 *          <p>
 *          Facade for all web requests.
 *          </p>
 */

public class ServiceFacade {

    private ServiceInterface serviceInterface;
    private final String API_BASE_URL = "https://randomuser.me";
    private final int USER_AMOUNT_PER_PAGE = 50;

    public ServiceFacade() {
        com.google.gson.GsonBuilder gsonBuilder = new GsonBuilder();

        gsonBuilder.registerTypeAdapter(Calendar.class, new JsonDeserializer<Calendar>() {
            public Calendar deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(json.getAsJsonPrimitive().getAsLong() * 1000);
                return calendar;
            }
        });

        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint(API_BASE_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setClient(new OkClient(new OkHttpClient()))
                .setConverter(new GsonConverter(gsonBuilder.create()));

        RestAdapter adapter = builder.build();
        this.serviceInterface = adapter.create(ServiceInterface.class);
    }

    public void getUsers(int page, String seed, final BaseCallback<UserPageEntity> userCallback) {
        serviceInterface.getUsers(page, seed, USER_AMOUNT_PER_PAGE, new Callback<UserPageEntity>() {
            @Override
            public void success(UserPageEntity data, Response response) {
                userCallback.onDataAvailable(data);
            }

            @Override
            public void failure(RetrofitError error) {
                userCallback.onError();
            }
        });
    }

}
