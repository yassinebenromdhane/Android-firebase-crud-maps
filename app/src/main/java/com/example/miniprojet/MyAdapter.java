package com.example.miniprojet;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miniprojet.Model.Event;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>  {

    Context context;

    ArrayList<Event> list;




    public MyAdapter(Context context, ArrayList<Event> list) {
        this.context = context;
        this.list = list;


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Event event = list.get(position);

        holder.name.setText(event.getName());
        holder.desc.setText(event.getDesc());
        holder.date.setText(event.getDate());
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context,EditEventActivity.class);
                i.putExtra("id",event.getId());
                context.startActivity(i);
            }
        });
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference db=FirebaseDatabase.getInstance().getReference().child("events");
                Query query=db.child(event.getId());
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        // remove the value at reference
                        dataSnapshot.getRef().removeValue();
                        Toast.makeText(context,"deleted Successfully",Toast.LENGTH_LONG).show();
                        Intent i = new Intent(context,context.getClass());
                        ((Activity)context).finish();
                        context.startActivity(i);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(context,"failed to delete",Toast.LENGTH_LONG).show();

                    }
                });

            }
        });




    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name, desc, date,id;
        ImageView image ;
        Button edit , remove;
        private  final  Context context;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            context = itemView.getContext();

            name = itemView.findViewById(R.id.name);
            desc = itemView.findViewById(R.id.desc);
            date = itemView.findViewById(R.id.date);
            image = itemView.findViewById(R.id.image);
            edit = itemView.findViewById(R.id.edit);
            remove = itemView.findViewById(R.id.delete);




        }

        }
    }