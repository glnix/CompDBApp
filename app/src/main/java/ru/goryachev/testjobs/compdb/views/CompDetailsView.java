package ru.goryachev.testjobs.compdb.views;

import android.support.annotation.StringRes;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import ru.goryachev.testjobs.compdb.models.Comp;

public interface CompDetailsView extends MvpView {
    @StateStrategyType(AddToEndSingleStrategy.class)
    void showCompDetails(Comp comp);

    @StateStrategyType(SingleStateStrategy.class)
    void showLoadDataError(@StringRes int message);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void loadCompImage(String url);
}
