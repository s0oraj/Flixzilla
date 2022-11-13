package blog.cosmos.home.flickzilla.adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import blog.cosmos.home.flickzilla.fragments.FavouriteMoviesFragment;
import blog.cosmos.home.flickzilla.fragments.FavouriteSeriesFragment;

public class FavouritesPagerAdapter extends FragmentPagerAdapter {
    private Context mContext;

    public FavouritesPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FavouriteMoviesFragment();
            case 1:
                return new FavouriteSeriesFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Movies";
            case 1:
                return "TV Shows";
        }
        return null;
    }
}
