package com.example.cardviewapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

//리사이클러뷰 어댑터로 상속받는데 들어가는 데이터는 펄슨어댑터의 뷰홀더 데이터
public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {
    ArrayList<Person> items = new ArrayList<Person>();
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.pseron_item,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Person item = items.get(position);
        holder.setItem(item); //item 값을 바인딩
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public  void addItem(Person item){
        items.add(item);
    }
    public void setItems(ArrayList<Person> items){
        this.items = items;
    }
    public Person getItem(int position){
        return items.get(position);
    }
    public void setItem(){

    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvPhone;

        public ViewHolder(View itemView){
            super(itemView);
            tvName=itemView.findViewById(R.id.tvName);
            tvPhone=itemView.findViewById(R.id.tvPhone);
        }
        public void setItem(Person item){
            tvName.setText(item.getName());
            tvPhone.setText(item.getMobile());
        }

    }
}