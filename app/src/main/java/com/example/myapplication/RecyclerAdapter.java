package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private static final String TAG = "RecyclerAdapter";
    List<String> notesList;

    public RecyclerAdapter(List<String> notesList) {
        this.notesList = notesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //holder.rowCountTextView.setText(String.valueOf(position));
        holder.textView.setText(notesList.get(position));
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageView;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
            // rowCountTextView = itemView.findViewById(R.id.rowCountTextView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), notesList.get(getAdapterPosition()), Toast.LENGTH_SHORT).show();
        }
    }
}
