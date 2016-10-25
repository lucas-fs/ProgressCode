package com.teste.progresscode.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.teste.progresscode.R;
import com.teste.progresscode.model.Encontro;

import java.util.List;

/**
 * Created by Lucas Ferreira da Silva.
 */

public class EncontroAdapter extends RecyclerView.Adapter<EncontroAdapter.EncontroViewHolder> {
    private List<Encontro> encontros; // List com os dados que preencherao a lista
    private int rowLayout; // XML do item
    private Context context; // Contexto da aplicacao


    public static class EncontroViewHolder extends RecyclerView.ViewHolder {
        //LinearLayout inscritosLayout;
        TextView dataRealizacao;
        //TextView data_nasc;
        //TextView escola;

        public EncontroViewHolder(View v) {
            super(v);
            //inscritosLayout = (LinearLayout) v.findViewById(R.id.list_ite);
            dataRealizacao = (TextView) v.findViewById(R.id.data_realizacao);
            //data_nasc = (TextView) v.findViewById(R.id.data_nasc);
            //escola = (TextView) v.findViewById(R.id.escola);
        }
    }

    public EncontroAdapter(List<Encontro> encontros, int rowLayout, Context context) {
        this.encontros = encontros;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    public EncontroAdapter(int rowLayout, Context context) {
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public EncontroAdapter.EncontroViewHolder onCreateViewHolder(ViewGroup parent,
                                                                 int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new EncontroViewHolder(view);
    }


    @Override
    public void onBindViewHolder(EncontroViewHolder holder, final int position) {
        holder.dataRealizacao.setText(encontros.get(position).getDataRealizao());
        //holder.data_nasc.setText(inscritos.get(position).getDataNasc());
        //holder.escola.setText(inscritos.get(position).getEscola());
    }

    @Override
    public int getItemCount() {
        return encontros.size();
    }
}
