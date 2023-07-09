package com.example.recyclerviewlists;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerviewlists.databinding.DataItemBinding;

import java.util.ArrayList;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.ViewHolder> {
    private static ArrayList<String> WordList;
    private static RecyclerItemClick recyclerItemClick;

    public WordAdapter(Context context, ArrayList<String> WordList, PassElementSelected listener, RecyclerItemClick recyclerItemClick) {
        this.WordList = WordList;
        this.recyclerItemClick = recyclerItemClick;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DataItemBinding binding = DataItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String word=WordList.get(position);
        holder.word_view.setText(word);
    }

    @Override
    public int getItemCount() {
        return WordList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
    public TextView word_view;

        public ViewHolder(DataItemBinding binding) {
            super(binding.getRoot());
            word_view = binding.wordView;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if (pos!= RecyclerView.NO_POSITION){
                        recyclerItemClick.OnItemClick(pos);
                        //aca se guarda la posicion
                        String element=WordList.get(pos);
                        //se pone el texto seleccionado a la palabra seleccionada
                        WordList.set(pos,"Seleccionado "+ element);
                        //esto lo escucha la interfaz
                        listener.passElement(element);
                    }
                }
            });
        }
    }

    public interface PassElementSelected{
        //esta interfaz guardara el string seleccionado
        void passElement(String element);

    }
    //el escuchador para la interfaz, estatico para que no modifique el dato
    private static PassElementSelected listener;
}
