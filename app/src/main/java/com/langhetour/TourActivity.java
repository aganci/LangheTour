package com.langhetour;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import static com.langhetour.StatusBarUtil.makeContentAppearBehindStatusBar;

public class TourActivity extends AppCompatActivity {
    public static final String EXTRA_IMAGE_RESOURCE_ID = "com.langhetour.imageResourceId";
    public static final String EXTRA_TITLE = "com.langhetour.title";
    public static final String EXTRA_FULL_ADDRESS = "com.langhetour.fullAddress";

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour);

        makeContentAppearBehindStatusBar(getWindow());
        setUpToolbar();
        setUpImageView();
        setUpDetails();
    }

    private void setUpDetails() {
        TextView textView = (TextView) findViewById(R.id.full_address);
        textView.setText(getIntent().getStringExtra(EXTRA_FULL_ADDRESS));
    }

    private void setUpImageView() {
        int imageResourceId = getIntent().getIntExtra(EXTRA_IMAGE_RESOURCE_ID, 0);
        ImageView imageView = (ImageView) findViewById(R.id.place_image);
        imageView.setImageResource(imageResourceId);
    }

    private void setUpToolbar() {
        String title = getIntent().getStringExtra(EXTRA_TITLE);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
    }
}
