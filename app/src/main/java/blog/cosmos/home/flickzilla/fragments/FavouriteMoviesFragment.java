package blog.cosmos.home.flickzilla.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import blog.cosmos.home.flickzilla.R;
import blog.cosmos.home.flickzilla.adapters.FavMoviesAdapter;
import blog.cosmos.home.flickzilla.database.movies.FavMovie;
import blog.cosmos.home.flickzilla.database.movies.MovieDatabase;

public class FavouriteMoviesFragment extends Fragment {

    private RecyclerView mFavMoviesRecyclerView;

    private LinearLayout mEmptyLayout;

    public FavouriteMoviesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourite_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mFavMoviesRecyclerView = view.findViewById(R.id.fav_movies_recycler_view);
        mEmptyLayout = view.findViewById(R.id.fav_movies_linear_layout);

        final LiveData<List<FavMovie>> mFavMovies = MovieDatabase.getInstance(getContext())
                .movieDao()
                .getAllFavMovies();
        mFavMovies.observe(requireActivity(), new Observer<List<FavMovie>>() {
            @Override
            public void onChanged(List<FavMovie> favMovies) {
                FavMoviesAdapter mFavMoviesAdapter = new FavMoviesAdapter(favMovies, getContext());
                mFavMoviesRecyclerView.setAdapter(mFavMoviesAdapter);
                mFavMoviesRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));

                if(favMovies.isEmpty()){
                    mEmptyLayout.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}