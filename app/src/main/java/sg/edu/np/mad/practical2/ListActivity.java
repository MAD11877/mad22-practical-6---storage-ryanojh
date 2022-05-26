package sg.edu.np.mad.practical2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

public class ListActivity extends AppCompatActivity {
    public static ArrayList<User> userList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        DBHandler db = new DBHandler(this);
        userList = db.getUser();


        RecyclerView rv;
        rv = findViewById(R.id.recyclerView);
        UsersAdapter adapter = new UsersAdapter(userList);
        LinearLayoutManager layout = new LinearLayoutManager(this);
        rv.setAdapter(adapter);
        rv.setLayoutManager(layout);

    }
}

        /*
        ImageView i = findViewById(R.id.imageView2);
        i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder ab = new AlertDialog.Builder(ListActivity.this);
                ab.setTitle("Profile");
                ab.setMessage("MADness");
                ab.setPositiveButton("VIEW", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Random rand = new Random();
                        int max = 100000000;
                        int num = rand.nextInt(max);
                        String title = "MAD " + num;
                        Intent i2 = new Intent(ListActivity.this, MainActivity.class);

                        i2.putExtra("Title", title);
                        startActivity(i2);
                    }
                });
                ab.setNegativeButton("CLOSE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                ab.show();
            }
        }); */