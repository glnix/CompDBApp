package ru.goryachev.testjobs.compdb.logic.services.APIService;


import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
import ru.goryachev.testjobs.compdb.BuildConfig;
import ru.goryachev.testjobs.compdb.models.Comp;

public class ApiService {
    private Retrofit retrofit;
    private static final ApiService instance = new ApiService();

    public static ApiService getInstance() {
        return instance;
    }

    public Observable<List<Comp>> getCompList() {
        return retrofit.create(CompDBService.class).compList();
    }

    private ApiService() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.API_BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
