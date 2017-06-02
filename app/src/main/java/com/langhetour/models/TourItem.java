package com.langhetour.models;

import android.content.Context;

import com.langhetour.GridBuilder;
import com.langhetour.R;

public abstract class TourItem {
    public static TourItem[] ITEMS = {
            new CategoryItem("Visitare"),
            new LocationItem(
                    R.drawable.castello_prunetto,
                    "Castello di Prunetto",
                    "Strada Provinciale 212, 14\n12077 Prunetto CN"),
            new LocationItem(
                    R.drawable.castello_saliceto,
                    "Castello di Saliceto",
                    "Via Vittorio Emanuele, 2\n12079 Saliceto CN"),
            new LocationItem(
                    R.drawable.castello_monesiglio,
                    "Castello di Monesiglio",
                    "Piazza XX Settembre\n12077 Monesiglio CN"),
    };

    public abstract void addTo(GridBuilder gridBuilder, Context context);
}
