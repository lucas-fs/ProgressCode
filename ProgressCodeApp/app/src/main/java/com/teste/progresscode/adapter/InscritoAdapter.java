package com.teste.progresscode.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.teste.progresscode.R;
import com.teste.progresscode.model.object.Inscrito;

import java.util.List;

/**
 * Created by lucas on 11/10/16.
 */

public class InscritoAdapter extends RecyclerView.Adapter<InscritoAdapter.InscritoViewHolder>  {
    private List<Inscrito> inscritos; // List com os dados que preencherao a lista
    private int rowLayout; // XML do item
    private Context context; // Contexto da aplicacao


    public static class InscritoViewHolder extends RecyclerView.ViewHolder {
        //LinearLayout inscritosLayout;
        TextView inscritoNome;
        //TextView data_nasc;
        //TextView escola;

        public InscritoViewHolder(View v) {
            super(v);
            //inscritosLayout = (LinearLayout) v.findViewById(R.id.list_ite);
            inscritoNome = (TextView) v.findViewById(R.id.nome_incrito);
            //data_nasc = (TextView) v.findViewById(R.id.data_nasc);
            //escola = (TextView) v.findViewById(R.id.escola);
        }
    }

    public InscritoAdapter(List<Inscrito> inscritos, int rowLayout, Context context) {
        this.inscritos = inscritos;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    public InscritoAdapter(int rowLayout, Context context) {
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
        //holder.data_nasc.setText(inscritos.get(position).getDataNasc());
        //holder.escola.setText(inscritos.get(position).getEscola());
    }

    @Override
    public int getItemCount() {
        return inscritos.size();
    }
}
