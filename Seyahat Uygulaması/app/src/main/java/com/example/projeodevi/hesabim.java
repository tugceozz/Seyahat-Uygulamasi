package com.example.projeodevi;

import static com.example.projeodevi.MainActivity.myData;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class hesabim extends AppCompatActivity {
    EditText email1, sifre1, guncel;
    Button btnkol1, gunbuton;
    String txtemail1, txtsifre1, txtGuncel;
    FirebaseAuth myAunt1;
    FirebaseUser myUser;
    DatabaseReference myref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hesabim);
        email1 = findViewById(R.id.girTextemail);
        sifre1 = findViewById(R.id.girTextTextPassword);
        guncel = findViewById(R.id.guncelle);
        gunbuton = findViewById(R.id.guncellebutton);
        btnkol1 = findViewById(R.id.girbutton);
        myAunt1 = FirebaseAuth.getInstance();
        myUser = myAunt1.getCurrentUser();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.gerimenu,menu);
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
        }
        return super.onOptionsItemSelected(item);
    }

    public void Gir(View view) {
        txtemail1 = email1.getText().toString();
        txtsifre1 = sifre1.getText().toString();
        Intent gec = new Intent(getApplicationContext(),AnaSayfa.class);
        startActivity(gec);
        overridePendingTransition(R.anim.solakay , R.anim.sagakay);
        if (!TextUtils.isEmpty(txtemail1) && !TextUtils.isEmpty(txtsifre1)) {
            myAunt1.signInWithEmailAndPassword(txtemail1, txtsifre1)
                    .addOnSuccessListener(this, new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            myUser = myAunt1.getCurrentUser();
                            if (myUser != null)
                                verileriGetir(myUser.getUid());

                        }
                    }).addOnFailureListener(this, new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(hesabim.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else
            Toast.makeText(this, "Kullanıcı email ve sifre boş olamaz", Toast.LENGTH_SHORT).show();

    }

    public void verileriGetir(String uid) {
        myref = FirebaseDatabase.getInstance().getReference("Kullanicilar").child(uid);
        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snp : snapshot.getChildren()
                ) {
                    email1.setText("");
                    sifre1.setText("");
                    System.out.println(snp.getKey() + " = " + snp.getValue());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(hesabim.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void veriGuncelle(HashMap<String, Object> obj, String uid) {
        myref = FirebaseDatabase.getInstance().getReference("Kullanicilar").child(uid);
        myref.updateChildren(obj)
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(hesabim.this, "Veri başarıyla güncellendi", Toast.LENGTH_SHORT).show();
                            System.out.println("-----Güncel veriler----");
                            verileriGetir(uid);
                        }
                    }
                });
    }

    public void isimGuncelle(View view) {
        txtGuncel = guncel.getText().toString();
        if (!TextUtils.isEmpty(txtGuncel)) {

            myData.put("Kullanici Adi", txtGuncel);
            if (myUser != null) {
                veriGuncelle(myData, myUser.getUid());
            }
        } else
            Toast.makeText(hesabim.this, "Güncellenecek değer boş olamaz", Toast.LENGTH_SHORT).show();
    }

    public void delete(View view) {
        myref = FirebaseDatabase.getInstance().getReference("Kullanicilar").child(myUser.getUid());
        myUser = FirebaseAuth.getInstance().getCurrentUser();
        myUser.delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                            Toast.makeText(hesabim.this, "Hesap başarıyla silindi", Toast.LENGTH_SHORT).show();
                            Intent gec = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(gec);
                            overridePendingTransition(R.anim.solakay , R.anim.sagakay);
                    }
                });
        myref.removeValue()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                            Toast.makeText(hesabim.this, "Veriler silindi", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(hesabim.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}


