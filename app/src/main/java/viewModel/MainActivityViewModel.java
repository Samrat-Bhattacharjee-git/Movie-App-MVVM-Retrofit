package viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import model.Movie;

import java.util.List;

import model.MovieRepository;

public class MainActivityViewModel extends AndroidViewModel {
    //if you need to use context inside your viewModel
    //you need to use androidViewModel instead of ViewModel
    //because it content application context

    private MovieRepository repository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.repository = new MovieRepository(application);
    }

    //LiveData
    public LiveData<List<Movie>> getAllMovies(){
        return repository.getMutableLiveData();
    }
}
