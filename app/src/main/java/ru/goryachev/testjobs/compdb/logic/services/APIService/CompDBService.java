package ru.goryachev.testjobs.compdb.logic.services.APIService;


import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import ru.goryachev.testjobs.compdb.models.Comp;

public interface CompDBService {
        @GET("computers.json")
        Observable<List<Comp>> compList();
}
