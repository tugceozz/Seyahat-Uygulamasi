package com.example.projeodevi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class trabzon extends AppCompatActivity {
    BottomNavigationView trabzon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trabzon);
        trabzon=findViewById(R.id.trabzon2);
        trabzon.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item1) {
                switch (item1.getItemId()){
                    case R.id.muze:
                        getSupportFragmentManager().beginTransaction().replace(R.id.trabzon,new TrabzonMuze()).commit();
                        break;
                    case R.id.restorant:
                        getSupportFragmentManager().beginTransaction().replace(R.id.trabzon,new TrabzonRestoran()).commit();
                        break;
                    case R.id.doga:
                        getSupportFragmentManager().beginTransaction().replace(R.id.trabzon,new TrabzonDoga()).commit();
                        break;
                }
                return true;
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.topmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.geri:
                Intent geri = new Intent(getApplicationContext(),AnaSayfa.class);
                startActivity(geri);
                overridePendingTransition(R.anim.solakay , R.anim.sagakay);
                break;
            case R.id.hesap:
                Intent hesap = new Intent(getApplicationContext(),hesabim.class);
                startActivity(hesap);
                overridePendingTransition(R.anim.solakay , R.anim.sagakay);
                break;
            case R.id.cikis:
                Toast.makeText(this, "Oturum Kapatıldı", Toast.LENGTH_SHORT).show();
                Intent cikis = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(cikis);
                overridePendingTransition(R.anim.solakay , R.anim.sagakay);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}