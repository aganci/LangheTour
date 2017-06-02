package com.langhetour.models;

import com.langhetour.GridBuilder;

public class CategoryItem extends TourItem {
    private String title;

    public CategoryItem(String title) {
        this.title = title;
    }

    @Override
    public void addTo(GridBuilder gridBuilder) {
        gridBuilder.addCategory(title);
    }
}
