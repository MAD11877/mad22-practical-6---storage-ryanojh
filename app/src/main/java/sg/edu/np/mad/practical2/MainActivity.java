package sg.edu.np.mad.practical2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = getIntent();
        String t = i.getStringExtra("Title");
        TextView title = findViewById(R.id.textView);
        title.setText(t);

        String d = i.getStringExtra("Description");
        TextView desc = findViewById(R.id.textView2);
        desc.setText(d);

        Button followBtn = findViewById(R.id.follow);
        int index = i.getIntExtra("Index", 1);
        User selectedUser = ListActivity.userList.get(index);

        if (selectedUser.followed) {
            followBtn.setText("UNFOLLOW");
        } else {
            followBtn.setText("FOLLOW");
        }

        DBHandler db = new DBHandler(this);
        followBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedUser.followed){
                    followBtn.setText("FOLLOW");
                    Toast.makeText(MainActivity.this, "Unfollowed", Toast.LENGTH_LONG).show();
                    selectedUser.followed = false;

                }
                else {
                    followBtn.setText("UNFOLLOW");
                    Toast.makeText(MainActivity.this, "Followed", Toast.LENGTH_LONG).show();
                    selectedUser.followed = true;
                }
                db.updateUser(selectedUser);
            }
        });

        Button m = findViewById(R.id.message);
        m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(MainActivity.this, MessageGroup.class);
                startActivity(i2);
            }
        });
    }
}