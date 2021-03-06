package ru.goryachev.testjobs.compdb.views;


import android.support.annotation.StringRes;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import ru.goryachev.testjobs.compdb.models.Comp;

public interface CompListView extends MvpView {
    @StateStrategyType(AddToEndSingleStrategy.class)
    void showCompList(List<Comp> compList);

    @StateStrategyType(SingleStateStrategy.class)
    void showLoadDataError(@StringRes int message);
}
