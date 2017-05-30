package com.langhetour;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.app_name));

        new GridBuilder((ConstraintLayout) findViewById(R.id.main_constraint_layout), this)
                .addCategory("Visitare")
                .addImage(R.drawable.castello_prunetto, "Castello di Prunetto")
                .addImage(R.drawable.castello_saliceto, "Castello di Saliceto")
                .addImage(R.drawable.castello_monesiglio, "Castello di Monesiglio")
                .addImage(R.drawable.castello_prunetto, "Castello di Prunetto")
                .addImage(R.drawable.castello_saliceto, "Castello di Saliceto")
                .addImage(R.drawable.castello_monesiglio, "Castello di Monesiglio");
    }
}
