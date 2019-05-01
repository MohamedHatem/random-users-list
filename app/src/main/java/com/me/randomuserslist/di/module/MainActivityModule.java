package com.me.randomuserslist.di.module;

import com.me.randomuserslist.MainActivity;
import com.me.randomuserslist.adapter.RandomUserAdapter;
import com.me.randomuserslist.di.interfaces.MainActivityScope;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Hari on 20/12/17.
 */
@Module
public class MainActivityModule {

    private final MainActivity mainActivity;

    public MainActivityModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Provides
    @MainActivityScope
    public RandomUserAdapter randomUserAdapter(Picasso picasso) {
        return new RandomUserAdapter(picasso);
    }
}
