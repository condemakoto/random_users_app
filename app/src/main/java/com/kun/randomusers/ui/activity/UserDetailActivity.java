package com.kun.randomusers.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.ethanhua.skeleton.Skeleton;
import com.ethanhua.skeleton.SkeletonScreen;
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
    private final static int TIME_TO_HIDE_SKELETON_SCREEN = 3000;
    private final Handler mHandler = new Handler();
    private SkeletonScreen skeletonScreen;

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
        UserDetailAdapter  adapter = new UserDetailAdapter(false, user);
        recyclerView.setAdapter(adapter);

        getSupportActionBar().setTitle(R.string.txtUserDetail);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        skeletonScreen = Skeleton.bind(recyclerView)
                .adapter(adapter)
                .show();
        mHandler.postDelayed(hideSkeletonViewRunnable, TIME_TO_HIDE_SKELETON_SCREEN);
    }

    private final Runnable hideSkeletonViewRunnable = new Runnable() {
        @Override
        public void run() {
            if (skeletonScreen != null) {
                skeletonScreen.hide();
            }
        }
    };
}
