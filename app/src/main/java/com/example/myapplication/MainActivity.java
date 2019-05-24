package com.example.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseUser firebaseUser;
    private FirebaseAuth firebaseAuth;
    private TextView uyeOlTextview;
    private EditText kullaniciadiEdittext;
    private EditText parolaEdittext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uyeOlTextview=(TextView)findViewById(R.id.uyelik_textview);
        kullaniciadiEdittext=(EditText)findViewById(R.id.kullanici_adi_edittetx);
        parolaEdittext=(EditText)findViewById(R.id.parola_edittext);
        firebaseAuth=FirebaseAuth.getInstance();

        firebaseUser=firebaseAuth.getCurrentUser();

        if(firebaseUser!=null){
            Intent i=new Intent(MainActivity.this,UserListActivity.class);
            startActivity(i);
        }

        uyeOlTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(i);
            }
        });

    }

    public void giris(View view) {
       String kullaniciAdi=kullaniciadiEdittext.getText().toString().trim();
       String parola=parolaEdittext.getText().toString().trim();
       if(kullaniciAdi.isEmpty()||parola.isEmpty()){

           Toast.makeText(MainActivity.this, "Kullanıcı adı veya parola boş olamaz", Toast.LENGTH_SHORT).show();

       }else{
           firebaseAuth.signInWithEmailAndPassword(kullaniciAdi,parola).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
               @Override
               public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Intent i=new Intent(MainActivity.this,UserListActivity.class);
                        startActivity(i);
                    }else if(task.getException() instanceof FirebaseAuthInvalidUserException){
                        Toast.makeText(getApplicationContext(), "Kaydınız bulunmamaktadır.", Toast.LENGTH_SHORT).show();

                    }

               }
           });

       }
    }


}
