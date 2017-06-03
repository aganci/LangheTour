package com.langhetour.models;

import android.content.Context;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLngBounds;
import com.langhetour.GridBuilder;
import com.langhetour.R;

public abstract class TourItem {
    public static TourItem[] ITEMS = {
            new CategoryItem("Visitare"),
            new LocationItem(
                    R.drawable.castello_prunetto,
                    "Castello di Prunetto",
                    "Strada Provinciale 212, 14\n12077 Prunetto CN",
                    44.4917147, 8.1416262),
            new LocationItem(
                    R.drawable.castello_saliceto,
                    "Castello di Saliceto",
                    "Via Vittorio Emanuele, 2\n12079 Saliceto CN",
                    44.4152779,8.1660571),
            new LocationItem(
                    R.drawable.castello_monesiglio,
                    "Castello di Monesiglio",
                    "Piazza XX Settembre\n12077 Monesiglio CN",
                    44.4153377,8.1331352),
    };

    public abstract void addTo(GridBuilder gridBuilder, Context context);
    public abstract void addMarker(GoogleMap googleMap, LatLngBounds.Builder builder);
}
