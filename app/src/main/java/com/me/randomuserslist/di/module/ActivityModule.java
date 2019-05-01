package com.me.randomuserslist.di.module;

import android.app.Activity;
import android.content.Context;

import com.me.randomuserslist.di.interfaces.ActivityContext;
import com.me.randomuserslist.di.interfaces.RandomUserApplicationScope;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    Context context;


    public ActivityModule(Activity context) {
        this.context = context;
    }


    @ActivityContext
    @RandomUserApplicationScope
    @Provides
    public Context context() {
        return context.getApplicationContext();
    }
}
