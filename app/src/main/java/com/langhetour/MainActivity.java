package com.langhetour;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.langhetour.models.TourItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.app_name));

        GridBuilder gridBuilder = new GridBuilder((ConstraintLayout) findViewById(R.id.main_constraint_layout), this);
        for (TourItem item : TourItem.ITEMS) {
            item.addTo(gridBuilder, this);
        }
    }
}
