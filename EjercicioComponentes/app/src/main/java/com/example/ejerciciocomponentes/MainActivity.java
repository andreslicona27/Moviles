package com.example.ejerciciocomponentes;

import static android.content.ContentValues.TAG;
import static android.net.Uri.parse;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ToggleButton tgbtn = findViewById(R.id.primerBoton);
        CheckBox chbox = findViewById(R.id.checkBox);
        CheckBox chbox2 = findViewById(R.id.checkBox2);
        CheckBox chbox3 = findViewById(R.id.checkBox3);
        SeekBar skb = findViewById(R.id.seekBar);
        TextView tv3 = findViewById(R.id.textView3);
        Switch sw = findViewById(R.id.switch1);
        TextView tv2 = findViewById(R.id.textView2);
        Button reinicio = findViewById(R.id.button);
        Button btnImagen = findViewById(R.id.button2);
        RatingBar rb = findViewById(R.id.ratingBar);
        EditText nombre = findViewById(R.id.editTextTextPersonName);
        RadioGroup rg = findViewById(R.id.radioGroup);
        RadioButton rb1 = findViewById(R.id.radioButton);
        RadioButton rb2 = findViewById(R.id.radioButton2);
        ImageButton imgbtn = findViewById(R.id.imageButton2);
        TextView tvmuestraRating = findViewById((R.id.tvmuestraRating));
        ActionBar ab = getSupportActionBar();



        Intent intent;
        intent = getIntent();
        rb.setRating(intent.getFloatExtra("RATING", 0f));


        tgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tgbtn.isChecked()){
                    chbox2.setChecked(false);
                } else {
                    chbox2.setChecked(true);
                }
            }
        });
        chbox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chbox2.isChecked()){
                    tgbtn.setChecked(false);
                } else {
                    tgbtn.setChecked(true);
                }
            }
        });
        skb.setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tv3.setText(String.valueOf(progress));
                ab.setSubtitle(String.valueOf(progress));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sw.isChecked()){
                    tv2.setText("Activo");
                } else {
                    tv2.setText("Desactivo");
                }
            }
        });

        reinicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tgbtn.setChecked(false);
                chbox.setChecked(false);
                chbox2.setChecked(false);
                chbox3.setChecked(false);
                rb1.setChecked(false);
                rb2.setChecked(false);
                sw.setChecked(false);
                skb.setProgress(0);
                rb.setRating(0);
                nombre.setText("");
            }
        });

        btnImagen.setOnClickListener(new View.OnClickListener() {
            int cont = 0;
            @Override
            public void onClick(View v) {
                if (chbox2.isChecked()) {
                    cont--;
                } else {
                    cont++;
                }
               btnImagen.setText("Button " + cont);
            }
        });

        rb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "RadioButton1 Seleccionado", Toast.LENGTH_SHORT).show();
            }
        });

        rb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "RadioButton2 Seleccionado", Toast.LENGTH_SHORT).show();
            }
        });


        ActivityResultLauncher<Intent> launcher=registerForActivityResult(new
                ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode()==RESULT_OK){
                Intent intent=result.getData();
                int numEstrellas=(int)intent.getFloatExtra("numEstrellas", 0f);
                rb.setRating(intent.getFloatExtra("RATING", 0F));
                tvmuestraRating.setText("num Estrellas: " + intent.getFloatExtra("RATING", 0F));
                switch(numEstrellas){
                    case 0: Log.i(TAG, "Por los suelos"); break;
                    case 1: Log.i(TAG, "De bajón"); break;
                    case 2: Log.i(TAG, "Triste"); break;
                    case 3: Log.i(TAG, "Normal"); break;
                    case 4: Log.i(TAG, "Contento"); break;
                    case 5: Log.i(TAG, "Feliz"); break;
                }
            }
        }
    });

        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, Secundary.class);
                intent.putExtra("NOMBRE",nombre.getText().toString());
                intent.putExtra("RATING", rb.getRating());
                launcher.launch(intent);
            }
        });

        ImageButton iconoLlamar = findViewById(R.id.iconoLlamar);
        EditText textoPhone = findViewById(R.id.textoPhone);
        iconoLlamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String numero = textoPhone.getText().toString();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(parse("tel:" + numero));
                startActivity(intent);
            }
        });


        Button actionBar = findViewById(R.id.actionBar);
        actionBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ab.isShowing()) {
                    ab.hide();
                } else {
                    ab.show();
                }
            }
        });




    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nuevo:
                CheckBox chbox = findViewById(R.id.checkBox);
                CheckBox chbox2 = findViewById(R.id.checkBox2);
                CheckBox chbox3 = findViewById(R.id.checkBox3);

                Intent intent = new Intent(MainActivity.this, Terciaria.class);
                if (chbox.isChecked()) {
                    intent.putExtra("checkbox1", chbox.getText() + "");
                }
                if (chbox2.isChecked()){
                    intent.putExtra("checkbox2", chbox2.getText() + "");
                }
                if (chbox3.isChecked()){
                    intent.putExtra("checkbox3", chbox3.getText() + "");
                }

               startActivity(intent);

                return true;
            case R.id.borrar:
                SeekBar skb = findViewById(R.id.seekBar);
                TextView tv3 = findViewById(R.id.textView3);
                TextView tvmuestraRating = findViewById((R.id.tvmuestraRating));
                TextView tv2 = findViewById(R.id.textView2);

                skb.setProgress(0);
                tv3.setText(" ");
                tv2.setText(" ");
                tvmuestraRating.setText(" ");
                return true;
            case R.id.editar:
                EditText nombre = findViewById(R.id.editTextTextPersonName);
                nombre.setText(" ");
                return true;
            case R.id.opcion1:
                Toast.makeText(this, "opcion 1", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.opcion2:
                Toast.makeText(this, "opcion 2", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}