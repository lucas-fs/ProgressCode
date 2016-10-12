package com.teste.progresscode.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.teste.progresscode.R;
import com.teste.progresscode.model.Inscrito;

import java.util.List;

/**
 * Created by lucas on 11/10/16.
 */

public class InscritoAdapter extends RecyclerView.Adapter<InscritoAdapter.InscritoViewHolder>  {
    private List<Inscrito> inscritos;
    private int rowLayout;
    private Context context;


    public static class InscritoViewHolder extends RecyclerView.ViewHolder {
        LinearLayout inscritosLayout;
        TextView inscritoNome;
        TextView data_nasc;
        TextView escola;
        //TextView rating;


        public InscritoViewHolder(View v) {
            super(v);
            inscritosLayout = (LinearLayout) v.findViewById(R.id.inscritos_layout);
            inscritoNome = (TextView) v.findViewById(R.id.nome);
            data_nasc = (TextView) v.findViewById(R.id.data_nasc);
            escola = (TextView) v.findViewById(R.id.escola);
            //rating = (TextView) v.findViewById(R.id.rating);
        }
    }

    public InscritoAdapter(List<Inscrito> inscritos, int rowLayout, Context context) {
        this.inscritos = inscritos;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public InscritoAdapter.InscritoViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new InscritoViewHolder(view);
    }


    @Override
    public void onBindViewHolder(InscritoViewHolder holder, final int position) {
        holder.inscritoNome.setText(inscritos.get(position).getNome());
        holder.data_nasc.setText(inscritos.get(position).getDataNasc());
        holder.escola.setText(inscritos.get(position).getEscola());
        //holder.rating.setText(movies.get(position).getVoteAverage().toString());
    }

    @Override
    public int getItemCount() {
        return inscritos.size();
    }
}
