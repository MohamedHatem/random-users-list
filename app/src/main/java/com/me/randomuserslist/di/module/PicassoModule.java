package com.me.randomuserslist.di.module;


import android.content.Context;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.me.randomuserslist.di.interfaces.ApplicationContext;
import com.me.randomuserslist.di.interfaces.RandomUserApplicationScope;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module(includes = OkHttpClientModule.class)
public class PicassoModule {

    @RandomUserApplicationScope
    @Provides
    Picasso picasso(@ApplicationContext Context context, OkHttp3Downloader okHttpDownloader) {
        return new Picasso.Builder(context).downloader(okHttpDownloader).build();
    }

    @Provides
    OkHttp3Downloader okHttpDownloader(OkHttpClient okHttpClient) {
        return new OkHttp3Downloader(okHttpClient);
    }
}
