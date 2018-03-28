package com.example.ali.androidmvp.utils;

import com.example.ali.androidmvp.data.network.model.Movie;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Ali Esa Assadi on 12/03/2018.
 */

public class ToolsUtils {

    private ToolsUtils() {
        // This tools class is not publicly instantiable
    }


    public static boolean isListValid(List<Movie> list) {
        return list != null && !list.isEmpty();
    }

}