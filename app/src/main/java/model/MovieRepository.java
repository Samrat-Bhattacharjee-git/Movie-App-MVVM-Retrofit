package model;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.example.the_movie_app.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import serviceapi.MovieApiService;
import serviceapi.RetrofitInstance;

public class MovieRepository {
    //used to abstract data source details
    //provides a clean api for the viewModel to
    //fetch and manage data
    //repository acts as a bridge between viewModel and data source

    private static ArrayList<Movie> movies=new ArrayList<>();
    private MutableLiveData<List<Movie>> mutableLiveData=new MutableLiveData<>();

    private Application application;
    //creating this application order to access the resources folder

    public MovieRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Movie>> getMutableLiveData() {
        MovieApiService movieApiService= RetrofitInstance.getService();
        Call<Result> call=movieApiService.getpopularmovies(application.getApplicationContext().getString(R.string.api_key));

        call.enqueue(new Callback<Result>() {
            //perform network request in the background thread
            //handle the response on the main(UI) thread
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                //success
                Result result=response.body();
                if(result!=null && result.getResults()!=null){
                    movies= (ArrayList<Movie>) result.getResults();
                    mutableLiveData.setValue(movies);
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }

        });
        return mutableLiveData;
    }


}
