package com.flixter.flixter.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.flixter.flixter.models.Movie;

import java.util.ArrayList;

/**
 * Created by SagarMutha on 9/15/17.
 */

public class MovieArrayAdapter extends ArrayAdapter<Movie> {

    public MovieArrayAdapter(Context context, ArrayList<Movie> movies) {
        super(context, android.R.layout.simple_list_item_1 , movies);
    }
}
