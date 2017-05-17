package ru.goryachev.testjobs.compdb.logic.presenters;


import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.goryachev.testjobs.compdb.R;
import ru.goryachev.testjobs.compdb.logic.services.APIService.ApiService;
import ru.goryachev.testjobs.compdb.views.CompDetailsView;
import timber.log.Timber;

@InjectViewState
public class CompDetailsPresenter extends MvpPresenter<CompDetailsView> {

    public CompDetailsPresenter(int compId) {
        getDetails(compId);
    }

    public void getDetails(int compId) {
        ApiService.getInstance().getCompDetails(compId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(comp -> {
                            getViewState().showCompDetails(comp);
                            getViewState().loadCompImage(comp.getImageUrl());
                        },
                        e -> {
                            Timber.e(e);
                            getViewState().showLoadDataError(R.string.error);
                        });

    }
}
