package ru.goryachev.testjobs.compdb.logic.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.goryachev.testjobs.compdb.R;
import ru.goryachev.testjobs.compdb.logic.services.APIService.ApiService;
import ru.goryachev.testjobs.compdb.views.CompListView;
import timber.log.Timber;

@InjectViewState
public class CompListPresenter extends MvpPresenter<CompListView> {
    public CompListPresenter() {
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getCompList();
    }

    public void getCompList(){
        ApiService.getInstance().getCompList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(list -> getViewState().showCompList(list),
                        e -> {
                            Timber.e(e);
                            getViewState().showLoadDataError(R.string.error);
                        });
    }
}
