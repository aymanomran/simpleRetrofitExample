package com.example.ayman.training1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by AYMAN on 04/09/2018.
 */

public class wordAdapter extends RecyclerView.Adapter<wordAdapter.wordViewHolder> {
 List<WordClass> word_List;
 Context  context;

    public wordAdapter(List word_List, Context context) {
        this.word_List = word_List;
        this.context = context;
    }

    @Override
    public wordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      wordViewHolder Holder=null;
        try {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item,parent,false);
            Holder = new wordViewHolder(view);
        }catch (Exception ex){
            Toast.makeText(context,"In OnCreateView Holder "+ex.getMessage().toString(),Toast.LENGTH_LONG).show();
        }
        return Holder;
    }

    @Override
    public void onBindViewHolder(wordViewHolder holder, int position) {
        try {
            WordClass wordList = word_List.get(position);
            holder.Word.setText(wordList.Word);
        }catch (Exception ex){
            Toast.makeText(context,"In OnBind "+ex.getMessage().toString(),Toast.LENGTH_LONG).show();

        }

    }

    @Override
    public int getItemCount() {
        return  word_List.size();
    }

    public class wordViewHolder extends RecyclerView.ViewHolder{

       TextView Word;

        public wordViewHolder(View itemView) {
            super(itemView);
            try {
            Word = (TextView) itemView.findViewById(R.id.wordtxtID);
            Word.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, Word.getText().toString(), Toast.LENGTH_LONG).show();
                }
            });
        }catch (Exception ex){

                Toast.makeText(context,"In ViewHolder "+ex.getMessage().toString(),Toast.LENGTH_LONG).show();
            }
        }
    }
}
