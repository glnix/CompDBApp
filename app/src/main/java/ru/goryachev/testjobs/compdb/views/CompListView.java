package ru.goryachev.testjobs.compdb.views;


import android.support.annotation.StringRes;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import ru.goryachev.testjobs.compdb.models.Comp;

public interface CompListView extends MvpView {
    void showCompList(List<Comp> compList);
    void showLoadDataError(@StringRes int message);
}
