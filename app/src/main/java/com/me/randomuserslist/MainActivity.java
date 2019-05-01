package com.me.randomuserslist;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.me.randomuserslist.adapter.RandomUserAdapter;
import com.me.randomuserslist.api.RandomUsersApi;
import com.me.randomuserslist.di.component.DaggerRandomUserComponent;
import com.me.randomuserslist.di.component.RandomUserComponent;
import com.me.randomuserslist.di.module.ContextModule;
import com.me.randomuserslist.model.RandomUsers;
import com.squareup.picasso.Picasso;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RandomUserAdapter mAdapter;

    RandomUsersApi randomUsersApi;
    Picasso picasso;

    /*
      TODO :
         1- To understand how the singleton scope works from the generated DI files

     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        RandomUserComponent daggerRandomUserComponent = DaggerRandomUserComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
        picasso = daggerRandomUserComponent.getPicasso();

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
                    mAdapter = new RandomUserAdapter(picasso);
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