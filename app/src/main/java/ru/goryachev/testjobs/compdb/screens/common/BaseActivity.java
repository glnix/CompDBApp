package ru.goryachev.testjobs.compdb.screens.common;


import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatActivity;

import ru.goryachev.testjobs.compdb.R;

public class BaseActivity extends MvpAppCompatActivity {
    private Toolbar toolbar;
    private ViewGroup rootView;

    protected void initBaseViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rootView = (ViewGroup) ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
    }

    protected void setToolbarTitle(@StringRes int title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(title));
        }
    }

    protected ViewGroup getRootView(){
        return rootView;
    }

}
