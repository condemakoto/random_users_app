package com.kun.randomusers.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.kun.randomusers.R;
import com.kun.randomusers.domain.model.User;
import com.kun.randomusers.ui.adapter.UserDetailAdapter;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author Julio Kun
 * @version 0.1
 */

public class UserDetailActivity extends BaseActivity {

    private final static String KEY_USER = "user";
    @Bind(R.id.recyclerView)
    protected RecyclerView recyclerView;

    public static Intent newIntent(User user, Context context) {
        Intent intent = new Intent(context, UserDetailActivity.class);
        intent.putExtra(KEY_USER, user);
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container_with_collapse_toolbar);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        User user = getIntent().getParcelableExtra(KEY_USER);

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        Picasso.with(this)
                .load(user.getBigImageUrl())
                .placeholder(R.drawable.default_avatar_large)
                .into(imageView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new UserDetailAdapter(false, user));

        getSupportActionBar().setTitle(R.string.txtUserDetail);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
