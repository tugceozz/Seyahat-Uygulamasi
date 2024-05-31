package com.example.projeodevi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

   public static EditText ad,soyad,email,telefon,sifre;
    Button kaydet,kayitolma;
    String txtemail,txtsifre,txtad,txtsoyad,txttelefon;
    FirebaseAuth myAuth;
    DatabaseReference myRef;
    FirebaseUser myUser;
    ImageView logo;
    public static HashMap<String,Object> myData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logo = findViewById(R.id.logo2);
        ad = findViewById(R.id.editTextTextPersonName);
        soyad = findViewById(R.id.editTextTextPersonName2);
        email = findViewById(R.id.editTextTextEmailAddress);
        telefon = findViewById(R.id.editTextPhone);
        sifre = findViewById(R.id.editTextTextPassword);
        kaydet = findViewById(R.id.button);
        kayitolma = findViewById(R.id.button2);
        myAuth = FirebaseAuth.getInstance();
        myRef = FirebaseDatabase.getInstance().getReference();
        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.solakay);
        logo.startAnimation(anim);
        ad.startAnimation(anim);
        soyad.startAnimation(anim);
        email.startAnimation(anim);
        telefon.startAnimation(anim);
        sifre.startAnimation(anim);
        kaydet.startAnimation(anim);
        kayitolma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gec = new Intent(getApplicationContext(),AnaSayfa.class);
                startActivity(gec);
                overridePendingTransition(R.anim.solakay , R.anim.sagakay);
            }
        });
    }

    public void kaydet(View view) {
        txtad = ad.getText().toString();
        txtsoyad = soyad.getText().toString();
        txtemail = email.getText().toString();
        txttelefon = telefon.getText().toString();
        txtsifre = sifre.getText().toString();
        if(!TextUtils.isEmpty(txtemail)&&!TextUtils.isEmpty(txtsifre)){
            myAuth.createUserWithEmailAndPassword(txtemail,txtsifre)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                myUser=myAuth.getCurrentUser();
                                myData=new HashMap<>();
                                myData.put("Kullanici Adi",txtad);
                                myData.put("Kullanici Soyadi",txtsoyad);
                                myData.put("Kullanici Email",txtemail);
                                myData.put("Kullanici Telefonu",txttelefon);
                                myData.put("Kullanici Sifre",txtsifre);
                                myData.put("Kullanici Id",myUser.getUid());
                                myRef.child("Kullanicilar").child(myUser.getUid()).setValue(myData)
                                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if(task.isSuccessful()){
                                                    email.setText("");
                                                    sifre.setText("");
                                                    ad.setText("");
                                                    soyad.setText("");
                                                    telefon.setText("");
                                                    Toast.makeText(MainActivity.this,"Kullanıcı kayıt edildi",Toast.LENGTH_SHORT).show();
                                                    Intent geri = new Intent(getApplicationContext(),AnaSayfa.class);
                                                    startActivity(geri);
                                                    overridePendingTransition(R.anim.solakay , R.anim.sagakay);

                                                }else{
                                                    Toast.makeText(MainActivity.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                            }
                            else
                                Toast.makeText(MainActivity.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }else
            Toast.makeText(this, "Kullanıcı email ve sifre boş olamaz", Toast.LENGTH_SHORT).show();
    }
}


