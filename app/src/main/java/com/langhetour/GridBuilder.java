package com.langhetour;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class GridBuilder {
    private ConstraintLayout layout;
    private Context context;
    private int guidelineID;
    private ArrayList<Integer> viewsId;

    public GridBuilder(ConstraintLayout layout, Context context) {
        this.layout = layout;
        this.context = context;
        viewsId = new ArrayList<>();
        createGuideLine();
    }

    public GridBuilder addCategory(String title) {
        TextView textView = new TextView(context);
        generateId(textView);
        textView.setBackgroundColor(Color.RED);
        textView.setTextColor(Color.WHITE);
        textView.setText(title);

        applyConstraints(textView);
        return this;
    }

    public GridBuilder addImage(int resourceId) {
        ImageView imageView = new ImageView(context);
        generateId(imageView);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(resourceId);
        applyConstraints(imageView);
        return this;
    }

    private void createGuideLine() {
        ConstraintSet constraints = new ConstraintSet();
        constraints.clone(layout);
        guidelineID = View.generateViewId();
        constraints.create(guidelineID, ConstraintSet.VERTICAL);
        constraints.setGuidelinePercent(guidelineID, 0.5f);
        constraints.applyTo(layout);
    }

    private void applyConstraints(View view) {
        Integer id = viewsId.get(viewsId.size() - 1);
        if (viewsId.size() == 1) {
            setMargins(view, 0,0,getMargin(),0);
            layout.addView(view);
            ConstraintSet constraints = createContraintSet(id);
            constraints.connect(id, ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT);
            constraints.connect(id, ConstraintSet.RIGHT, guidelineID, ConstraintSet.LEFT);
            constraints.connect(id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);
            constraints.applyTo(layout);
        } else if (viewsId.size() == 2) {
            setMargins(view, getMargin(),0,0,0);
            layout.addView(view);
            ConstraintSet constraints = createContraintSet(id);
            constraints.connect(id, ConstraintSet.LEFT, guidelineID, ConstraintSet.RIGHT);
            constraints.connect(id, ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT);
            constraints.connect(id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);
            constraints.applyTo(layout);
        } else if (viewsId.size() % 2 == 1) {
            setMargins(view, 0, getMargin() * 2,  getMargin(), 0);
            layout.addView(view);
            ConstraintSet constraints = createContraintSet(id);
            constraints.connect(id, ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT);
            constraints.connect(id, ConstraintSet.RIGHT, guidelineID, ConstraintSet.LEFT);
            constraints.connect(id, ConstraintSet.TOP, viewsId.get(viewsId.size() - 3), ConstraintSet.BOTTOM);
            constraints.applyTo(layout);
        } else if (viewsId.size() % 2 == 0) {
            setMargins(view, getMargin(), getMargin() * 2,  0, 0);
            layout.addView(view);
            ConstraintSet constraints = createContraintSet(id);
            constraints.connect(id, ConstraintSet.LEFT, guidelineID, ConstraintSet.RIGHT);
            constraints.connect(id, ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT);
            constraints.connect(id, ConstraintSet.TOP, viewsId.get(viewsId.size() - 3), ConstraintSet.BOTTOM);
            constraints.applyTo(layout);
        }
    }

    @NonNull
    private ConstraintSet createContraintSet(Integer id) {
        ConstraintSet constraints = new ConstraintSet();
        constraints.clone(layout);
        constraints.constrainWidth(id, ConstraintSet.MATCH_CONSTRAINT);
        constraints.constrainHeight(id, ConstraintSet.MATCH_CONSTRAINT);
        constraints.setDimensionRatio(id, "h,1:1");
        return constraints;
    }

    private void setMargins(View view, int left, int top, int right, int bottom) {
        ConstraintLayout.LayoutParams params =
                new ConstraintLayout.LayoutParams(
                        ConstraintLayout.LayoutParams.MATCH_CONSTRAINT,
                        ConstraintLayout.LayoutParams.MATCH_CONSTRAINT);
        params.setMargins(left,top,right,bottom);
        view.setLayoutParams(params);
    }

    private void generateId(View view) {
        int id = View.generateViewId();
        viewsId.add(id);
        view.setId(id);
    }

    public int getMargin() {
        return 1;
    }
}
