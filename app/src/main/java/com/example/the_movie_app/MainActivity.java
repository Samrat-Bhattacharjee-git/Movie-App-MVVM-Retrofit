package com.example.the_movie_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;

import com.example.the_movie_app.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import model.Movie;
import view.MovieAdapter;
import viewModel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {

    ArrayList<Movie> movies=new ArrayList<>();
    RecyclerView recyclerView;
    MovieAdapter movieAdapter;
    SwipeRefreshLayout swipeRefreshLayout;
    ActivityMainBinding binding;
    MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recycle);

        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);

        //View model
        viewModel=new ViewModelProvider(this).get(MainActivityViewModel.class);

        getPopularMovies();

        swipeRefreshLayout =binding.swipe;
        swipeRefreshLayout.setColorSchemeResources(R.color.black);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                getPopularMovies();
            }
        });
    }

    private void getPopularMovies() {
        viewModel.getAllMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> moviesFromLiveData) {

                movies = (ArrayList<Movie>) moviesFromLiveData;
                displayMoviesInRecyclerView();
            }

        });
    }


    private void displayMoviesInRecyclerView() {
        //RecyclerView
        recyclerView=binding.recycle;
        movieAdapter=new MovieAdapter(this,movies);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //linking the recyclerview with adapter
        recyclerView.setAdapter(movieAdapter);

        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        movieAdapter.notifyDataSetChanged();


    }
}