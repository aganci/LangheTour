package com.langhetour;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new GridBuilder((ConstraintLayout) findViewById(R.id.main_constraint_layout), this)
                .addCategory("Visitare")
                .addImage(R.drawable.castello_prunetto)
                .addImage(R.drawable.castello_saliceto)
                .addImage(R.drawable.castello_prunetto)
                .addImage(R.drawable.castello_saliceto)
                .addImage(R.drawable.castello_prunetto)
                .addImage(R.drawable.castello_saliceto)
                .addImage(R.drawable.castello_prunetto)
                .addImage(R.drawable.castello_saliceto)
                .addImage(R.drawable.castello_prunetto)
                .addImage(R.drawable.castello_saliceto);
    }
}
