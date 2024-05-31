package com.example.projeodevi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AnaSayfa extends AppCompatActivity {
    Button istanbul,ankara,bolu,trabzon,erzurum,adana,mardin,izmir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ana_sayfa);
        istanbul = findViewById(R.id.istanbul);
        ankara = findViewById(R.id.ankara);
        bolu = findViewById(R.id.bolu);
        trabzon = findViewById(R.id.trabzon);
        erzurum = findViewById(R.id.erzurum);
        adana = findViewById(R.id.adana);
        mardin = findViewById(R.id.mardin);
        izmir = findViewById(R.id.izmir);

        istanbul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent istanbul = new Intent(getApplicationContext(),istanbul.class);
                startActivity(istanbul);
                overridePendingTransition(R.anim.solakay , R.anim.sagakay);
            }
        });

        ankara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ankara = new Intent(getApplicationContext(),ankara.class);
                startActivity(ankara);
                overridePendingTransition(R.anim.solakay , R.anim.sagakay);
            }
        });

        bolu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bolu = new Intent(getApplicationContext(),bolu.class);
                startActivity(bolu);
                overridePendingTransition(R.anim.solakay , R.anim.sagakay);
            }
        });

        trabzon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent trabzon = new Intent(getApplicationContext(),trabzon.class);
                startActivity(trabzon);
                overridePendingTransition(R.anim.solakay , R.anim.sagakay);
            }
        });

        erzurum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent erzurum = new Intent(getApplicationContext(),erzurum.class);
                startActivity(erzurum);
                overridePendingTransition(R.anim.solakay , R.anim.sagakay);
            }
        });

        adana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent adana = new Intent(getApplicationContext(),adana.class);
                startActivity(adana);
                overridePendingTransition(R.anim.solakay , R.anim.sagakay);
            }
        });

        mardin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mardin = new Intent(getApplicationContext(),mardin.class);
                startActivity(mardin);
                overridePendingTransition(R.anim.solakay , R.anim.sagakay);
            }
        });

        izmir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent izmir = new Intent(getApplicationContext(),izmir.class);
                startActivity(izmir);
                overridePendingTransition(R.anim.solakay , R.anim.sagakay);
            }
        });



    }
}