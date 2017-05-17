package ru.goryachev.testjobs.compdb.screens;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import ru.goryachev.testjobs.compdb.R;
import ru.goryachev.testjobs.compdb.logic.presenters.CompDetailsPresenter;
import ru.goryachev.testjobs.compdb.models.Comp;
import ru.goryachev.testjobs.compdb.screens.common.BaseActivity;
import ru.goryachev.testjobs.compdb.views.CompDetailsView;

public class CompDetailsActivity extends BaseActivity implements CompDetailsView {

    @InjectPresenter
    CompDetailsPresenter compDetailsPresenter;

    AppCompatTextView compName, compDescription;
    AppCompatImageView compImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comp_details);
        initBaseViews();

        findViews();
    }

    @ProvidePresenter
    CompDetailsPresenter provideCompDetailsPresenter() {
        return new CompDetailsPresenter(getIntent().getIntExtra("ID", -1));
    }

    private void findViews() {
        compName = (AppCompatTextView) findViewById(R.id.comp_name);
        compDescription = (AppCompatTextView) findViewById(R.id.comp_description);
        compImage = (AppCompatImageView) findViewById(R.id.comp_image);
    }

    @Override
    public void showCompDetails(Comp comp) {
        setToolbarTitle(comp.getCompany().getName());
        compName.setText(comp.getName());
        compDescription.setText(comp.getDescription());
    }

    @Override
    public void showLoadDataError(@StringRes int message) {
        Snackbar
                .make(getRootView(), getString(message), Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.repeat, v -> compDetailsPresenter.getDetails(getIntent().getIntExtra("ID", -1)))
                .show();
    }

    @Override
    public void loadCompImage(String url) {
        if (TextUtils.isEmpty(url)) return;
        Glide
             .with(this)
             .load(url)
             .fitCenter()
             .diskCacheStrategy(DiskCacheStrategy.ALL)
             .into(compImage);
    }
}
