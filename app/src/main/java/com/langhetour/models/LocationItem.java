package com.langhetour.models;

import com.langhetour.GridBuilder;
import com.langhetour.R;

public class LocationItem extends TourItem {
    private int resourceId;
    private String title;

    public LocationItem(int resourceId, String title) {
        this.resourceId = resourceId;
        this.title = title;
    }

    @Override
    public void addTo(GridBuilder gridBuilder) {
        gridBuilder.addImage(resourceId, title);
    }
}
