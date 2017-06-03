package com.langhetour.models;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.langhetour.GridBuilder;
import com.langhetour.R;
import com.langhetour.TourActivity;

public class LocationItem extends TourItem implements View.OnClickListener {
    private int resourceId;
    private String title;
    private String fullAddress;
    private Context context;
    private final LatLng position;

    public LocationItem(int resourceId, String title, String fullAddress, double lat, double lon) {
        this.resourceId = resourceId;
        this.title = title;
        this.fullAddress = fullAddress;
        position = new LatLng(lat, lon);
    }

    @Override
    public void addTo(GridBuilder gridBuilder, Context context) {
        this.context = context;
        gridBuilder.addImage(resourceId, title, this);
    }

    @Override
    public void addMarker(GoogleMap googleMap, LatLngBounds.Builder builder) {
        googleMap.addMarker(
                new MarkerOptions()
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_pin))
                        .position(position)
                        .title(title));
        builder.include(position);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this.context, TourActivity.class);
        intent.putExtra(TourActivity.EXTRA_TITLE, this.title);
        intent.putExtra(TourActivity.EXTRA_IMAGE_RESOURCE_ID, this.resourceId);
        intent.putExtra(TourActivity.EXTRA_FULL_ADDRESS, this.fullAddress);
        context.startActivity(intent);
    }
}
