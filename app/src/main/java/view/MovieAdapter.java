package view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.the_movie_app.R;
import com.example.the_movie_app.databinding.MovieItemListBinding;

import java.util.ArrayList;

import model.Movie;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private Context context;
    private ArrayList<Movie> movieArrayList;

    public MovieAdapter(Context context, ArrayList<Movie> movieArrayList) {
        this.context = context;
        this.movieArrayList = movieArrayList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //creating new viewholder for items in recycleview
        MovieItemListBinding movieItemListBinding= DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.movie_item_list,parent,false
        );
        return new MovieViewHolder(movieItemListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        //called by recycleview when it need to display or update an item
        //at a specific position in the list or grid.

        Movie currentmovie=movieArrayList.get(position);
        holder.movieItemListBinding.setMovie(currentmovie);
    }

    @Override
    public int getItemCount() {
        //Determines the total number of items in the dataset that will be displayed in the recyclerview
        if(movieArrayList != null){
            return movieArrayList.size();
        }
        else {
            return 0;
        }
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder{

        private MovieItemListBinding movieItemListBinding;
        public MovieViewHolder(MovieItemListBinding movieItemListBinding) {
            super(movieItemListBinding.getRoot());
            this.movieItemListBinding=movieItemListBinding;

            movieItemListBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }

}
