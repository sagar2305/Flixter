package com.flixter.flixter.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.flixter.flixter.R;
import com.flixter.flixter.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by SagarMutha on 9/15/17.
 */

public class MovieArrayAdapter extends ArrayAdapter<Movie> {

    private static final int TYPE_1 = 0;
    private static final int TYPE_2 = 1;

    // View lookup cache
    private static class ViewHolderRegularMovie {
        ImageView image;
        TextView title;
        TextView overview;
    }

    private static class ViewHolderPopularMovie {
        ImageView image;
    }


    public MovieArrayAdapter(Context context, ArrayList<Movie> movies) {
        super(context, android.R.layout.simple_list_item_1 , movies);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // get movie
        Movie movie = getItem(position);

        int viewType = getItemViewType(position);
        switch (viewType) {
            case TYPE_1:
                // Check if an existing view is being reused, otherwise inflate the view
                ViewHolderRegularMovie viewHolder; // view lookup cache stored in tag
                if (convertView == null) {
                    // If there's no view to re-use, inflate a brand new view for row
                    viewHolder = new ViewHolderRegularMovie();
                    LayoutInflater inflater = LayoutInflater.from(getContext());
                    convertView = inflater.inflate(R.layout.item_movie, parent, false);

                    viewHolder.image = convertView.findViewById(R.id.ivMovieImage);
                    viewHolder.title = convertView.findViewById(R.id.tvTitle);
                    viewHolder.overview = convertView.findViewById(R.id.tvOverview);

                    // Cache the viewHolder object inside the fresh view
                    convertView.setTag(viewHolder);
                } else {
                    // View is being recycled, retrieve the viewHolder object from tag
                    viewHolder = (ViewHolderRegularMovie) convertView.getTag();
                }

                //clear out image from imageview
                viewHolder.image.setImageResource(0);

                //populate data
                viewHolder.title.setText(movie.getOriginalTitle());
                viewHolder.overview.setText(movie.getOverview());

                String path;
                int placeHolderId;
                int orientation = getContext().getResources().getConfiguration().orientation;
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    path = movie.getPosterPath();
                    placeHolderId = R.drawable.placeholder_portrait;
                } else {
                    path = movie.getBackdropPath();
                    placeHolderId = R.drawable.placeholder;
                }

                Picasso.with(getContext()).load(path)
                        .fit().centerCrop()
                        .placeholder(placeHolderId)
                        .into(viewHolder.image);

                //return view
                return convertView;

            case TYPE_2:

                // Check if an existing view is being reused, otherwise inflate the view
                ViewHolderPopularMovie viewHolderPopularMovie; // view lookup cache stored in tag
                if (convertView == null) {
                    // If there's no view to re-use, inflate a brand new view for row
                    viewHolderPopularMovie = new ViewHolderPopularMovie();
                    LayoutInflater inflater = LayoutInflater.from(getContext());
                    convertView = inflater.inflate(R.layout.item_movie_popular, parent, false);

                    viewHolderPopularMovie.image = convertView.findViewById(R.id.ivMovieImage);

                    // Cache the viewHolder object inside the fresh view
                    convertView.setTag(viewHolderPopularMovie);
                } else {
                    // View is being recycled, retrieve the viewHolder object from tag
                    viewHolderPopularMovie = (ViewHolderPopularMovie) convertView.getTag();
                }

                //clear out image from imageview
                viewHolderPopularMovie.image.setImageResource(0);

                String pathPopular = movie.getBackdropPath();
                int placeHolderIdPopular = R.drawable.placeholder;

                Picasso.with(getContext()).load(pathPopular)
                        .fit().centerCrop()
                        .placeholder(placeHolderIdPopular)
                        .into(viewHolderPopularMovie.image);

                //return view
                return convertView;

            default:
                throw new AssertionError("view type does not exist");
        }
    }

    @Override
    public int getItemViewType(int position) {
        // get movie
        Movie movie = getItem(position);
        if (movie.isPopular()) {
            return TYPE_2;
        } else {
            return TYPE_1;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }
}
