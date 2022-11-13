package blog.cosmos.home.flickzilla.database;

import android.content.Context;

import blog.cosmos.home.flickzilla.database.movies.FavMovie;
import blog.cosmos.home.flickzilla.database.movies.MovieDatabase;
import blog.cosmos.home.flickzilla.database.search.RecentSearch;
import blog.cosmos.home.flickzilla.database.search.SearchDatabase;
import blog.cosmos.home.flickzilla.database.series.FavSeries;
import blog.cosmos.home.flickzilla.database.series.SeriesDatabase;

public class DatabaseHelper {
    public static boolean isFavMovie(Context context, Integer movieId){
        if(movieId == null) return false;
        FavMovie f = MovieDatabase.getInstance(context).movieDao().getMovieById(movieId);
        return f != null;
    }

    public static boolean isFavSeries(Context context, Integer seriesId){
        if(seriesId == null) return false;
        FavSeries f = SeriesDatabase.getInstance(context).seriesDao().getSeriesById(seriesId);
        return f != null;
    }

    public static boolean isRecentlySearched(Context context, String name){
        if(name == null) return false;
        RecentSearch recentSearch = SearchDatabase.getInstance(context).searchDao().getSearchesByName(name);
        return recentSearch != null;
    }
}
