package com.bookcross;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatScreen extends AppCompatActivity {

    private String name;
    private String userName;
    private RecyclerView messagesRecyclerView;
    //private FirebaseListAdapter<Message> adapter;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://bookcross-377713-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat2);

        final CircleImageView userProfilPic = findViewById(R.id.userProfilPic);


        messagesRecyclerView = findViewById(R.id.messagesRecyclerView);
        name = getIntent().getStringExtra("name");
        userName = getIntent().getStringExtra("userName");

        messagesRecyclerView.setHasFixedSize(true);
        messagesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                final String profilPicUrl = snapshot.child("User").child(name).child("profil_pic").getValue(String.class);

                if(!profilPicUrl.isEmpty()){
                    Picasso.get().load(profilPicUrl).into(userProfilPic);
                }
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                progressDialog.dismiss();

            }
        });

       //displayAllMessage();
    }

    private void displayAllMessage() {

    }
}