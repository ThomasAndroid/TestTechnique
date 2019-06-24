package com.thomas.test.retrofit;



import com.thomas.test.object.Item;
import retrofit2.Call;
import retrofit2.http.GET;
import java.util.List;


public interface APIInterface {

    @GET("photos")
    Call<List<Item>> getItems();

}
