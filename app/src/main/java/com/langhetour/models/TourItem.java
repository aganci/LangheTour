package com.langhetour.models;

import android.content.Context;

import com.langhetour.GridBuilder;
import com.langhetour.R;

public abstract class TourItem {
    public static TourItem[] ITEMS = {
            new CategoryItem("Visitare"),
            new LocationItem(R.drawable.castello_prunetto, "Castello di Prunetto"),
            new LocationItem(R.drawable.castello_saliceto, "Castello di Saliceto"),
            new LocationItem(R.drawable.castello_monesiglio, "Castello di Monesiglio"),
    };

    public abstract void addTo(GridBuilder gridBuilder, Context context);
}
