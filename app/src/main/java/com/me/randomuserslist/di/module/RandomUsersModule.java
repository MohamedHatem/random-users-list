package com.me.randomuserslist.di.module;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.me.randomuserslist.api.RandomUsersApi;
import com.me.randomuserslist.di.interfaces.RandomUserApplicationScope;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = OkHttpClientModule.class)
public class RandomUsersModule {

    private static final String LOG_TAG = RandomUsersModule.class.getSimpleName();

    @Provides
    RandomUsersApi randomUsersApi(Retrofit retrofit) {
        return retrofit.create(RandomUsersApi.class);
    }

    @RandomUserApplicationScope
    @Provides
    Retrofit retrofit(OkHttpClient okHttpClient, GsonConverterFactory gsonConverterFactory) {

        Log.d(LOG_TAG, "retrofit: instance created");
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://randomuser.me/")
                .addConverterFactory(gsonConverterFactory)
                .build();
    }

    @Provides
    GsonConverterFactory gsonConverterFactory(Gson gson) {
        return GsonConverterFactory.create(gson);
    }


    @Provides
    Gson gson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }
}
