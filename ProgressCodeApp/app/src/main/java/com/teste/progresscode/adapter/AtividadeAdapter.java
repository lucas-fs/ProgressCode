package com.teste.progresscode.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.teste.progresscode.R;
import com.teste.progresscode.model.Atividade;

import java.util.List;

/**
 * Created by Lucas Ferreira da Silva.
 */

public class AtividadeAdapter extends RecyclerView.Adapter<AtividadeAdapter.AtividadeViewHolder>  {
    private List<Atividade> atividades; // List com os dados que preencherao a lista
    private int rowLayout; // XML do item
    private Context context; // Contexto da aplicacao


    public static class AtividadeViewHolder extends RecyclerView.ViewHolder {
        //LinearLayout inscritosLayout;
        TextView descricao;
        //TextView data_nasc;
        //TextView escola;

        public AtividadeViewHolder(View v) {
            super(v);
            //inscritosLayout = (LinearLayout) v.findViewById(R.id.list_ite);
            descricao = (TextView) v.findViewById(R.id.descricao);
            //data_nasc = (TextView) v.findViewById(R.id.data_nasc);
            //escola = (TextView) v.findViewById(R.id.escola);
        }
    }

    public AtividadeAdapter(List<Atividade> atividades, int rowLayout, Context context) {
        this.atividades = atividades;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    public AtividadeAdapter(int rowLayout, Context context) {
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public AtividadeAdapter.AtividadeViewHolder onCreateViewHolder(ViewGroup parent,
                                                                 int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new AtividadeViewHolder(view);
    }


    @Override
    public void onBindViewHolder(AtividadeViewHolder holder, final int position) {
        holder.descricao.setText(atividades.get(position).getDescricao());
        //holder.data_nasc.setText(inscritos.get(position).getDataNasc());
        //holder.escola.setText(inscritos.get(position).getEscola());
    }

    @Override
    public int getItemCount() {
        return atividades.size();
    }
}
