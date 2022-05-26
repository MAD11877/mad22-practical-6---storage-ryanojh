package sg.edu.np.mad.practical2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button loginBtn;
    String realUsername, realPassword;
    boolean valid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.usernameInput);
        password = findViewById(R.id.passwordInput);
        loginBtn = findViewById(R.id.loginBtn);

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://practical6-17068-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference myRef = database.getReference("Users");


        myRef.child("mad").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                realUsername = dataSnapshot.child("username").getValue(String.class);
                realPassword = dataSnapshot.child("password").getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputUsername = username.getText().toString();
                String inputPassword = password.getText().toString();

                if (inputUsername.isEmpty() || inputPassword.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Incomplete details", Toast.LENGTH_LONG).show();
                }

                else{
                    valid = validate(inputUsername, inputPassword);
                    if (!valid){
                        Toast.makeText(LoginActivity.this, "Incorrect details", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Intent i = new Intent(LoginActivity.this, ListActivity.class);
                        startActivity(i);
                    }
                }
            }
        });
    }

    // ensure username and password is correct
    public boolean validate(String username, String password){
        if (username.equals(realUsername) && password.equals(realPassword)){
            return true;
        }
        return false;
    }
}