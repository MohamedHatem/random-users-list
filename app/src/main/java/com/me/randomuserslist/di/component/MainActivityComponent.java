package com.me.randomuserslist.di.component;


import com.me.randomuserslist.adapter.RandomUserAdapter;
import com.me.randomuserslist.api.RandomUsersApi;
import com.me.randomuserslist.di.interfaces.MainActivityScope;
import com.me.randomuserslist.di.module.MainActivityModule;

import dagger.Component;

/**
 * Created by Hari on 20/12/17.
 */
@Component(modules = MainActivityModule.class, dependencies = RandomUserComponent.class)
@MainActivityScope
public interface MainActivityComponent {

    RandomUserAdapter getRandomUserAdapter();

    RandomUsersApi getRandomUserService();

}
