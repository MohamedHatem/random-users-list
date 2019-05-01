package com.me.randomuserslist.di.component;


import com.me.randomuserslist.di.interfaces.RandomUserApplicationScope;
import com.me.randomuserslist.di.module.PicassoModule;
import com.me.randomuserslist.di.module.RandomUsersModule;
import com.me.randomuserslist.api.RandomUsersApi;
import com.squareup.picasso.Picasso;

import dagger.Component;

@RandomUserApplicationScope
@Component(modules = {RandomUsersModule.class, PicassoModule.class})
public interface RandomUserComponent {

    RandomUsersApi getRandomUserService();

    Picasso getPicasso();
}
