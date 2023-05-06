package com.bookcross;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

public class InfoProfilScreen extends AppCompatActivity {//не работает

    ImageView icon_back1, icon_edit;
    TextView profileName, profileEmail, profileUsername, profilePassword;
    TextView titleName, titleUsername, book_cross, book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infoprofil);

        profileName = findViewById(R.id.profileName);
        profileEmail = findViewById(R.id.profileEmail);
        profileUsername = findViewById(R.id.profileUsername);
        profilePassword = findViewById(R.id.profilePassword);
        titleName = findViewById(R.id.titleName);
        titleUsername = findViewById(R.id.titleUsername);
        icon_back1 = findViewById(R.id.icon_back1);
        icon_edit = findViewById(R.id.icon_edit);
        book_cross = findViewById(R.id.book_Cross);
        book = findViewById(R.id.book);

        //showUserData();
        passUserData();

        icon_back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InfoProfilScreen.this, ProfilScreen.class);
                InfoProfilScreen.this.startActivity(intent);
            }
        });

        /*icon_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InfoProfilScreen.this, EditProfilScreen.class);
                InfoProfilScreen.this.startActivity(intent);
            }
        });*/

    }

    public void showUserData() {

        Intent intent = getIntent();

        String nameUser = intent.getStringExtra("name");
        String emailUser = intent.getStringExtra("email");
        String usernameUser = intent.getStringExtra("userName");
        String passwordUser = intent.getStringExtra("password");

        titleName.setText(nameUser);
        titleUsername.setText(usernameUser);
        profileName.setText(nameUser);
        profileEmail.setText(emailUser);
        profileUsername.setText(usernameUser);
        profilePassword.setText(passwordUser);

    }

    public void passUserData(){
        String userUsername = profileUsername.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUserDatabase = reference.orderByChild("username").equalTo(userUsername);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                String nameFromDB = snapshot.child("User").child(userUsername).child("name").getValue(String.class);
                String emailFromDB = snapshot.child("User").child(userUsername).child("email").getValue(String.class);
                String usernameFromDB = snapshot.child("User").child(userUsername).child("userName").getValue(String.class);
                String passwordFromDB = snapshot.child("User").child(userUsername).child("password").getValue(String.class);

                titleName.setText(nameFromDB);
                titleUsername.setText(usernameFromDB);
                profileName.setText(nameFromDB);
                profileEmail.setText(emailFromDB);
                profileUsername.setText(usernameFromDB);
                profilePassword.setText(passwordFromDB);

            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

    }

}