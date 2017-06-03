package com.langhetour.models;

import android.content.Context;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLngBounds;
import com.langhetour.GridBuilder;

public class CategoryItem extends TourItem {
    private String title;

    public CategoryItem(String title) {
        this.title = title;
    }

    @Override
    public void addTo(GridBuilder gridBuilder, Context context) {
        gridBuilder.addCategory(title);
    }

    @Override
    public void addMarker(GoogleMap googleMap, LatLngBounds.Builder builder) {

    }
}
