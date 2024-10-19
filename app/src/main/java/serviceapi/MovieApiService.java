package serviceapi;

import model.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApiService {

    //The service interface defines the structure
    //and behavior of the api request
    //Acts as a bridge between your app and api

    @GET("movie/popular")
    Call<Result> getpopularmovies(@Query("api_key") String apikey);

}
