package com.langhetour;

import android.content.Context;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class GridBuilder {
    private ConstraintLayout layout;
    private Context context;
    private int guidelineID;

    public GridBuilder(ConstraintLayout layout, Context context) {
        this.layout = layout;
        this.context = context;
        createGuideLine();
    }

    public GridBuilder addCategory(String title) {
        TextView textView = new TextView(context);
        textView.setId(View.generateViewId());
        textView.setBackgroundColor(Color.RED);
        textView.setTextColor(Color.WHITE);
        textView.setText(title);
        ConstraintLayout.LayoutParams params =
            new ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.MATCH_CONSTRAINT,
                    ConstraintLayout.LayoutParams.MATCH_CONSTRAINT);
        params.setMargins(0,0,getMargin(),0);
        textView.setLayoutParams(params);
        layout.addView(textView);

        ConstraintSet constraints = new ConstraintSet();
        constraints.clone(layout);
        constraints.constrainWidth(textView.getId(), ConstraintSet.MATCH_CONSTRAINT);
        constraints.constrainHeight(textView.getId(), ConstraintSet.MATCH_CONSTRAINT);
        constraints.setDimensionRatio(textView.getId(), "h,1:1");
        constraints.connect(textView.getId(), ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT);
        constraints.connect(textView.getId(), ConstraintSet.RIGHT, guidelineID, ConstraintSet.LEFT);
        constraints.connect(textView.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);
        constraints.applyTo(layout);
        return this;
    }

    public GridBuilder addImage(int resourceId) {
        ImageView imageView = new ImageView(context);
        imageView.setId(View.generateViewId());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(resourceId);
        ConstraintLayout.LayoutParams params =
                new ConstraintLayout.LayoutParams(
                        ConstraintLayout.LayoutParams.MATCH_CONSTRAINT,
                        ConstraintLayout.LayoutParams.MATCH_CONSTRAINT);
        params.setMargins(getMargin(),0,0,0);
        imageView.setLayoutParams(params);

        layout.addView(imageView);

        ConstraintSet constraints = new ConstraintSet();
        constraints.clone(layout);
        constraints.constrainWidth(imageView.getId(), ConstraintSet.MATCH_CONSTRAINT);
        constraints.constrainHeight(imageView.getId(), ConstraintSet.MATCH_CONSTRAINT);
        constraints.setDimensionRatio(imageView.getId(), "h,1:1");
        constraints.connect(imageView.getId(), ConstraintSet.LEFT, guidelineID, ConstraintSet.RIGHT);
        constraints.connect(imageView.getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT);
        constraints.connect(imageView.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);
        constraints.applyTo(layout);
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

    public int getMargin() {
        return 1;
    }
}
