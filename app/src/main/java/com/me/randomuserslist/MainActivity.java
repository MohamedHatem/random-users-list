package com.me.randomuserslist;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.me.randomuserslist.adapter.RandomUserAdapter;
import com.me.randomuserslist.api.RandomUsersApi;
import com.me.randomuserslist.di.component.DaggerMainActivityComponent;
import com.me.randomuserslist.di.component.DaggerRandomUserComponent;
import com.me.randomuserslist.di.component.MainActivityComponent;
import com.me.randomuserslist.di.component.RandomUserComponent;
import com.me.randomuserslist.di.module.ContextModule;
import com.me.randomuserslist.di.module.MainActivityModule;
import com.me.randomuserslist.model.RandomUsers;
import com.squareup.picasso.Picasso;


import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    @Inject
    RandomUserAdapter mAdapter;
    @Inject
    RandomUsersApi randomUsersApi;


    /*
      TODO :
         1- To understand how the singleton scope works from the generated DI files.
         2- To know Acitvity scope objects
         3- To Differentiate between Activity scope and application scope objects
         4- To know what is the meaning of Activity life scope ?

     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        MainActivityComponent mainActivityComponent = DaggerMainActivityComponent.builder()
                .mainActivityModule(new MainActivityModule(this))
                .randomUserComponent(RandomUserApplication.get(this).getRandomUserApplicationComponent())
                .build();


        mainActivityComponent.injectMainActivity(this);

        populateUsers();

    }

    private void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void populateUsers() {
        Call<RandomUsers> randomUsersCall = randomUsersApi.getRandomUsers(10);
        randomUsersCall.enqueue(new Callback<RandomUsers>() {
            @Override
            public void onResponse(Call<RandomUsers> call, @NonNull Response<RandomUsers> response) {
                if (response.isSuccessful()) {
                    mAdapter.setItems(response.body().getResults());
                    recyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<RandomUsers> call, Throwable t) {
                Timber.i(t.getMessage());
            }
        });
    }


}