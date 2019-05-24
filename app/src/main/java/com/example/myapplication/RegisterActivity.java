package com.example.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    private EditText kullaniciadiEdittext;
    private EditText parolaEdittext;
    private Button uyeolButton;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        kullaniciadiEdittext=(EditText)findViewById(R.id.kullanici_adi_edittext);
        parolaEdittext=(EditText)findViewById(R.id.parola_edittext_registeractivity);
        uyeolButton=(Button)findViewById(R.id.uyeol_button);
        firebaseAuth=FirebaseAuth.getInstance();

        uyeolButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               final String kullaniciadi= kullaniciadiEdittext.getText().toString().trim();
               String parola= parolaEdittext.getText().toString().trim();
               if(kullaniciadi.isEmpty()||parola.isEmpty()){

                   Toast.makeText(RegisterActivity.this, "Kullanıcı adı veya parola boş olamaz.", Toast.LENGTH_SHORT).show();

               }else{
                   firebaseAuth.createUserWithEmailAndPassword(kullaniciadi,parola).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            String uid=firebaseAuth.getCurrentUser().getUid();
                            databaseReference=FirebaseDatabase.getInstance().getReference("users/"+uid);
                            User user=new User(kullaniciadi,uid);
                            databaseReference.setValue(user);
                            Intent i=new Intent(getApplicationContext(),UserListActivity.class);
                            startActivity(i);

                        }else{

                        }
                    }
                });
            }
            }
        });
    }
}
