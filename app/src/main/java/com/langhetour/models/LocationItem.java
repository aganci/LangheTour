package com.langhetour.models;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.langhetour.GridBuilder;
import com.langhetour.TourActivity;

public class LocationItem extends TourItem implements View.OnClickListener {
    private int resourceId;
    private String title;
    private String fullAddress;
    private Context context;

    public LocationItem(int resourceId, String title, String fullAddress) {
        this.resourceId = resourceId;
        this.title = title;
        this.fullAddress = fullAddress;
    }

    @Override
    public void addTo(GridBuilder gridBuilder, Context context) {
        this.context = context;
        gridBuilder.addImage(resourceId, title, this);
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
