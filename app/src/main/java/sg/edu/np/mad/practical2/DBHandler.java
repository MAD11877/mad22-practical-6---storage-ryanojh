package sg.edu.np.mad.practical2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Random;

public class DBHandler extends SQLiteOpenHelper {

    public DBHandler(Context c){
        super(c, "MADPractical6.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Random rand = new Random();
        int max = 10000000;
        int i = 0;
        int follow;
        // CREATE TABLE User (Name TEXT, Description TEXT, Id INTEGER PRIMARY KEY AUTOINCREMENT, Followed INTEGER)
        String sql = "CREATE TABLE User (Name TEXT, Description TEXT, Id INTEGER PRIMARY KEY AUTOINCREMENT, Followed INTEGER)";
        sqLiteDatabase.execSQL(sql);
        while (i < 20){
            String name = "Name" + rand.nextInt(max);
            String description = "Description " + rand.nextInt(max);
            int id = i + 1;
            boolean followed = rand.nextBoolean();
            if (followed){
                follow = 1;
            }
            else{
                follow = 0;
            }
            sqLiteDatabase.execSQL("INSERT INTO User (NAME, DESCRIPTION, ID, FOLLOWED) VALUES(\""+ name + "\"," +
                    " \"" + description +"\", \""+ id +"\", \"" + follow + "\")");
            i++;
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }


    public ArrayList<User> getUser(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<User> userList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM User ", null);
        // SELECT * FROM User

        while(cursor.moveToNext()){
            User u = new User();
            u.name = cursor.getString(0);
            u.description = cursor.getString(1);
            u.id = cursor.getInt(2);
            int followed = cursor.getInt(3);
            if (followed == 1){
                u.followed = true;
            }
            else{
                u.followed = false;
            }
            userList.add(u);
        }
        return userList;
    }

    public void updateUser(User u){
        int follow = 0;
        if (u.followed){
            follow = 1;
        }
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE User SET Followed = \"" + follow + "\" WHERE id = \"" + u.id + "\"");
        // UPDATE User SET Followed = "follow" WHERE id = "u.id"

    }

}
