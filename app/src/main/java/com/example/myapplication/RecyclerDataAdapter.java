package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerDataAdapter extends RecyclerView.Adapter<RecyclerDataAdapter.ViewHolder> {
    private ArrayList<String> data;
    private IRVOnItemClick onItemClickCallback;

    public RecyclerDataAdapter(ArrayList<String> data, IRVOnItemClick onItemClickCallback) {
        this.data = data;
        this.onItemClickCallback = onItemClickCallback;
    }


    void add(String newElement) {
        data.add(newElement);
        notifyItemInserted(data.size() - 1);
    }

    void remove() {
        if(data.size() > 0) {
            data.remove(0);
            notifyItemRemoved(0);
        }
    }

    void move() {
        if(data.size() > 2) {
            String item = data.get(2);
            data.remove(2);
            data.add(0, item);
            notifyItemMoved(2, 0);
        }
    }

    void clearList() {
        data = new ArrayList<>();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_layout, parent,
                false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setItemText(holder, data.get(position));
        setOnClickForItem(holder, data.get(position));
    }

    private void setItemText(@NonNull ViewHolder holder, String text) {
        holder.textView.setText(text);
    }

    private void setOnClickForItem(@NonNull ViewHolder holder, final String text) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickCallback != null) {
                    onItemClickCallback.onItemClicked(text);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.itemTextView);
        }
    }
}
