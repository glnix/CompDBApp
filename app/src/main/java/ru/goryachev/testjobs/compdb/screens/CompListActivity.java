package ru.goryachev.testjobs.compdb.screens;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import ru.goryachev.testjobs.compdb.R;
import ru.goryachev.testjobs.compdb.logic.presenters.CompListPresenter;
import ru.goryachev.testjobs.compdb.models.Comp;
import ru.goryachev.testjobs.compdb.screens.common.BaseActivity;
import ru.goryachev.testjobs.compdb.views.CompListView;

public class CompListActivity extends BaseActivity implements CompListView {
    @InjectPresenter
    CompListPresenter compListPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comp_list);
        initBaseViews();
        setToolbarTitle(R.string.app_name);
    }


    @Override
    public void showCompList(List<Comp> compList) {

    }

    @Override
    public void showLoadDataError(@StringRes int message) {
        Snackbar
                .make(getRootView(), getString(message), Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.repeat, v -> compListPresenter.getCompList())
                .show();
    }
}
