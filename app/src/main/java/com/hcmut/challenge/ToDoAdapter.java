package com.hcmut.challenge;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ViewHolder>
{
    private ArrayList<ToDo> toDo_List;
    Context context;
    /*
    ItemClicked activity;
    public interface ItemClicked
    {
        void onItemClicked(int index);
    }
*/
    public ToDoAdapter (Context context, ArrayList<ToDo> list)
    {
        toDo_List = list;
        this.context=context;
        //activity = (ItemClicked) context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvTitle, tvDesc, tvDate, keydoes;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDesc = itemView.findViewById(R.id.tvDesc);
            tvDate = itemView.findViewById(R.id.tvDate);
            /*     SET CLICK LISTENER
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    activity.onItemClicked(toDo_List.indexOf((ToDo) view.getTag()));
                }
            });

             */
        }
    }

    @NonNull
    @Override
    public ToDoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ToDoAdapter.ViewHolder viewHolder, int i) {

        viewHolder.itemView.setTag(toDo_List.get(i));
        viewHolder.tvTitle.setText(toDo_List.get(i).getTitledoes());
        viewHolder.tvDesc.setText(toDo_List.get(i).getDesdoes());
        viewHolder.tvDate.setText(toDo_List.get(i).getDatedoes());

        final String getTitleDoes = toDo_List.get(i).getTitledoes();
        final String getDesDoes = toDo_List.get(i).getDesdoes();
        final String getDateDoes = toDo_List.get(i).getDatedoes();
        final String getKeyDoes = toDo_List.get(i).getKeydoes();

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a= new Intent(context, com.hcmut.challenge.Edit_Activity.class);
                a.putExtra("titledoes",getTitleDoes );
                a.putExtra("desdoes", getDesDoes);
                a.putExtra("datedoes", getDateDoes);
                a.putExtra("keydoes", getKeyDoes);
                context.startActivity(a);
            }
        });
    }

    @Override
    public int getItemCount() {
        return toDo_List.size();
    }
}
