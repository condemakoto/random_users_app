package com.kun.randomusers.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.kun.randomusers.R;
import com.kun.randomusers.domain.model.User;
import com.kun.randomusers.ui.fragment.UserDetailFragment;
import com.kun.randomusers.ui.fragment.UserListFragment;

/**
 * @author Julio kun
 * @version 0.1
 */

public class UserActivity extends BaseActivity implements UserListFragment.ActivityCallback {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        getSupportActionBar().setTitle(R.string.txtUserList);

        if (getSupportFragmentManager().findFragmentById(R.id.viewContainer) == null) {
            loadUserListView();
        }
    }

    private void loadUserListView() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.viewContainer, new UserListFragment());
        ft.commit();
    }

    private void loadUserDetailView(User user) {
        UserDetailFragment fr = (UserDetailFragment) getSupportFragmentManager().findFragmentById(R.id.viewDetailContainer);
        if (fr == null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.viewDetailContainer, UserDetailFragment.newInstance(user));
            ft.commit();
        } else {
            fr.loadUser(user);
        }
    }

    @Override
    public void showUserDetail(User user) {
        if (findViewById(R.id.viewDetailContainer) != null) {
            loadUserDetailView(user);
        } else {
            startActivity(UserDetailActivity.newIntent(user, this));
            overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
        }
    }
}
