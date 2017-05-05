package com.kun.randomusers.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

/**
 * @author Julio Kun
 * @version 0.1
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if(menuItem.getItemId() == android.R.id.home) {
            this.onHomeButtonPressed();
            return true;
        }

        return super.onOptionsItemSelected(menuItem);
    }

    protected void onHomeButtonPressed() {
        finish();
    }
}
