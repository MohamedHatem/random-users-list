package com.me.randomuserslist.di.module;

import android.content.Context;

import com.me.randomuserslist.di.interfaces.ApplicationContext;
import com.me.randomuserslist.di.interfaces.RandomUserApplicationScope;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {

    Context context;

    public ContextModule(Context context) {
        this.context = context;
    }


    @ApplicationContext
    @RandomUserApplicationScope
    @Provides
    public Context context() {
        return context.getApplicationContext();
    }
}
