package com.sayaji.cogniwideassgin.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.ProgressDialog;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.sayaji.cogniwideassgin.R;
import com.sayaji.cogniwideassgin.adapters.MoviesListAdapter;
import com.sayaji.cogniwideassgin.commonutils.AppConstants;
import com.sayaji.cogniwideassgin.datamodel.MovieResponseData;
import com.sayaji.cogniwideassgin.network.CogniwideAPI;
import com.sayaji.cogniwideassgin.network.CogniwideClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView moviesList = null;
    MoviesListAdapter moviesListAdapter = null;
    ProgressDialog progress;
    CogniwideAPI service = null;
    MoviesListAdapter.ScrollAtLastPosition scrollAtLastPosition = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppConstants.movieItems = new ArrayList<>();
        scrollAtLastPosition = new MoviesListAdapter.ScrollAtLastPosition() {
            @Override
            public void scrollAtLastPosition() {
                callMovieAPI();
            }
        };
        moviesListAdapter = new MoviesListAdapter(getApplicationContext(), scrollAtLastPosition);
        setListProperties();
        service = CogniwideClient.getRetrofitInstance().create(CogniwideAPI.class);
        callMovieAPI();

    }

    private void callMovieAPI() {
        progress = new ProgressDialog(MainActivity.this);
        progress.setMessage("Loading....");
        progress.setCanceledOnTouchOutside(true);
        progress.show();
        AppConstants.pageCount++;
//        Log.d(AppConstants.TAG, "Page Count - "+AppConstants.pageCount);
        Call<MovieResponseData> call = service.getMovieData(AppConstants.pageCount);
        call.enqueue(new Callback<MovieResponseData>() {
            @Override
            public void onResponse(Call<MovieResponseData> call, Response<MovieResponseData> response) {
                Log.d(AppConstants.TAG, response.body().toString());
                if (response.isSuccessful()) {
                    AppConstants.movieItems.addAll(response.body().getResults());
                    moviesListAdapter.notifyDataSetChanged();
                    progress.dismiss();
                } else {
                    progress.dismiss();
                    AppConstants.showErrorDialog(MainActivity.this, "Server Error");
                }
            }

            @Override
            public void onFailure(Call<MovieResponseData> call, Throwable t) {
                Log.d(AppConstants.TAG, t.toString());
                progress.dismiss();
                AppConstants.showErrorDialog(MainActivity.this, "Server Error");
            }
        });
    }

    private void setListProperties() {
        moviesList = (RecyclerView)findViewById(R.id.movie_list);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        moviesList.setLayoutManager(mLayoutManager);
        moviesList.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(1), true));
        moviesList.setItemAnimator(new DefaultItemAnimator());
        moviesList.setAdapter(moviesListAdapter);
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }
}