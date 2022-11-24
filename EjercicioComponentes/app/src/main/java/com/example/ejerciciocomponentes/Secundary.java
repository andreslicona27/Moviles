package com.example.ejerciciocomponentes;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class Secundary extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secundary);

        Intent intent;
        intent = getIntent();

        final TextView nombre = findViewById(R.id.textView);
        final RatingBar rating = findViewById(R.id.ratingBar2);
        nombre.setText("Hola " + intent.getStringExtra("NOMBRE"));
        rating.setRating(intent.getFloatExtra("RATING", 0f));

        Button boton = findViewById(R.id.button3);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Secundary.this, MainActivity.class);
                intent.putExtra("RATING", rating.getRating());
                setResult(RESULT_OK, intent);
                finish();
            }

        });

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);



    }

    @Override
    public void onBackPressed(){
        final RatingBar rating = findViewById(R.id.ratingBar2);

        Intent intent = new Intent(Secundary.this, MainActivity.class);
        intent.putExtra("RATING", rating.getRating());
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}