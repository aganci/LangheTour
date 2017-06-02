package com.langhetour;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class TourActivity extends AppCompatActivity {
    public static final String EXTRA_IMAGE_RESOURCE_ID = "com.langhetour.imageResourceId";
    public static final String EXTRA_TITLE = "com.langhetour.title";

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour);

        makeContentAppearBehindStatusBar();
        setUpToolbar();
        setUpImageView();
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

    private void makeContentAppearBehindStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
    }
}
