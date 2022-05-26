package sg.edu.np.mad.practical2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UsersAdapter extends RecyclerView.Adapter<UserViewHolder> {

    ArrayList<User> data;
    public UsersAdapter(ArrayList<User> data){
        this.data = data;
    }

    @Override
    public int getItemViewType(int position) {
        if (data.get(position).name.endsWith("7")){
            return 1;
        }
        else {
            return 0;
        }
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item;
        if (viewType == 0){
            item = LayoutInflater.from(parent.getContext()).inflate(R.layout.msg_layout,null,false);
        }
        else{
            item = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview2,null,false);
        }
        return new UserViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User content = data.get(position);
        holder.name.setText(content.name);
        holder.description.setText(content.description);

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectedName = data.get(holder.getAdapterPosition()).name;
                String selectedDesc = data.get(holder.getAdapterPosition()).description;
                AlertDialog.Builder ab = new AlertDialog.Builder(view.getContext());
                ab.setTitle("Profile");
                ab.setMessage(selectedName);

                ab.setPositiveButton("VIEW", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent i2 = new Intent(view.getContext(), MainActivity.class);
                        i2.putExtra("Title", selectedName);
                        i2.putExtra("Description", selectedDesc);
                        i2.putExtra("Index", holder.getAdapterPosition());
                        view.getContext().startActivity(i2);
                    }
                });

                ab.setNegativeButton("CLOSE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                ab.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
