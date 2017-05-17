package ru.goryachev.testjobs.compdb.logic.services.APIService;


import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.goryachev.testjobs.compdb.models.Comp;

public interface CompDBService {
        @GET("computers.json")
        Observable<List<Comp>> compList();

        @GET("computers/{id}.json")
        Observable<Comp> compDetasils(@Path("id") int id);
}
