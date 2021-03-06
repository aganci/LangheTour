package com.langhetour;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.constraint.solver.SolverVariable;
import android.util.TypedValue;
import android.view.Gravity;
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
        textView.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
        textView.setTextColor(Color.WHITE);
        textView.setText(title);
        textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);

        applyConstraints(textView);
        return this;
    }

    public GridBuilder addImage(int resourceId, String title, View.OnClickListener onClickListener) {
        ImageView imageView = new ImageView(context);
        generateId(imageView);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(resourceId);
        applyConstraints(imageView);

        TextView titleView = new TextView(context);
        titleView.setId(View.generateViewId());
        titleView.setBackgroundColor(Color.TRANSPARENT);
        titleView.setTextColor(Color.WHITE);
        titleView.setText(title);
        setMargins(titleView, getPixels(16), 0, 0, getPixels(16));
        layout.addView(titleView);

        ConstraintSet constraints = new ConstraintSet();
        constraints.clone(layout);
        constraints.constrainWidth(titleView.getId(), ConstraintSet.MATCH_CONSTRAINT);
        constraints.constrainHeight(titleView.getId(), ConstraintSet.WRAP_CONTENT);
        constraints.connect(titleView.getId(), ConstraintSet.LEFT, imageView.getId(), ConstraintSet.LEFT);
        constraints.connect(titleView.getId(), ConstraintSet.RIGHT, imageView.getId(), ConstraintSet.RIGHT);
        constraints.connect(titleView.getId(), ConstraintSet.BOTTOM, imageView.getId(), ConstraintSet.BOTTOM);
        constraints.applyTo(layout);

        imageView.setOnClickListener(onClickListener);

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
            setMargins(view, 0,0, getPixels(1),0);
            layout.addView(view);
            ConstraintSet constraints = createContraintSet(id);
            constraints.connect(id, ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT);
            constraints.connect(id, ConstraintSet.RIGHT, guidelineID, ConstraintSet.LEFT);
            constraints.connect(id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);
            constraints.applyTo(layout);
        } else if (viewsId.size() == 2) {
            setMargins(view, getPixels(1),0,0,0);
            layout.addView(view);
            ConstraintSet constraints = createContraintSet(id);
            constraints.connect(id, ConstraintSet.LEFT, guidelineID, ConstraintSet.RIGHT);
            constraints.connect(id, ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT);
            constraints.connect(id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);
            constraints.applyTo(layout);
        } else if (viewsId.size() % 2 == 1) {
            setMargins(view, 0, getPixels(2),  getPixels(1), 0);
            layout.addView(view);
            ConstraintSet constraints = createContraintSet(id);
            constraints.connect(id, ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT);
            constraints.connect(id, ConstraintSet.RIGHT, guidelineID, ConstraintSet.LEFT);
            constraints.connect(id, ConstraintSet.TOP, viewsId.get(viewsId.size() - 3), ConstraintSet.BOTTOM);
            constraints.applyTo(layout);
        } else if (viewsId.size() % 2 == 0) {
            setMargins(view, getPixels(1), getPixels(2),  0, 0);
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

    public int getPixels(int dpi) {
        Resources r = context.getResources();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpi, r.getDisplayMetrics());
    }
}
