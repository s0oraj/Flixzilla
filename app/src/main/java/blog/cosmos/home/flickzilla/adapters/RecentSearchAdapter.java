package blog.cosmos.home.flickzilla.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import blog.cosmos.home.flickzilla.R;
import blog.cosmos.home.flickzilla.activities.SearchResultsActivity;
import blog.cosmos.home.flickzilla.database.search.RecentSearch;
import blog.cosmos.home.flickzilla.database.search.SearchDatabase;

public class RecentSearchAdapter extends RecyclerView.Adapter<RecentSearchAdapter.ViewHolder> {
    List<RecentSearch> mSearches;
    private Context mContext;

    public RecentSearchAdapter(List<RecentSearch> mSearches, Context mContext) {
        this.mSearches = mSearches;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.recent_search_single_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(mSearches.get(position).getSearch_name() != null){
            holder.recent_search_name.setText(mSearches.get(position).getSearch_name());
        }

        holder.recent_search_close.setOnClickListener(view -> {
         DeleteSearch deleteSearch = new DeleteSearch();
         deleteSearch.execute(mSearches.get(position).getSearch_name());
        });
    }

    @Override
    public int getItemCount() {
        return mSearches.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ConstraintLayout recent_search_parent;
        public TextView recent_search_name;
        public ImageButton recent_search_close;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            recent_search_parent = itemView.findViewById(R.id.recent_search_parent);
            recent_search_name = itemView.findViewById(R.id.recent_search_name);
            recent_search_close = itemView.findViewById(R.id.recent_search_close);

            recent_search_parent.setOnClickListener(view -> {
                Intent intent = new Intent(mContext, SearchResultsActivity.class);
                intent.putExtra("query", mSearches.get(getAdapterPosition()).getSearch_name());
                mContext.startActivity(intent);
            });
        }
    }

    class DeleteSearch extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... strings) {
            SearchDatabase.getInstance(mContext)
                    .searchDao()
                    .deleteSearchesByName(strings[0]);

            return null;
        }
    }
}
