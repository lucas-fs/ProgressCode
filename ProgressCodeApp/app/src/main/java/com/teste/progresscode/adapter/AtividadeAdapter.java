package com.teste.progresscode.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.teste.progresscode.R;
import com.teste.progresscode.database.SyncDatabaseApi;
import com.teste.progresscode.model.dao.FeedbackDAO;
import com.teste.progresscode.model.object.Atividade;
import com.teste.progresscode.model.object.Feedback;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Lucas Ferreira da Silva.
 */

public class AtividadeAdapter extends RecyclerView.Adapter<AtividadeAdapter.AtividadeViewHolder>  {
    private List<Atividade> atividades; // List com os dados que preencherao a lista
    private int rowLayout; // XML do item
    private Context context; // Contexto da aplicacao
    private int id_inscrito;
    private int id_tutor;
    private SyncDatabaseApi syncDatabaseApi;

    public static class AtividadeViewHolder extends RecyclerView.ViewHolder {
        //LinearLayout inscritosLayout;
        TextView descricao;
        ImageView smileTriste;
        ImageView smileFeliz;
        ImageView smileNeutro;
        //TextView data_nasc;
        //TextView escola;

        public AtividadeViewHolder(View v) {
            super(v);
            //inscritosLayout = (LinearLayout) v.findViewById(R.id.list_ite);
            descricao = (TextView) v.findViewById(R.id.descricao);
            smileTriste = (ImageView) v.findViewById(R.id.smile_triste);
            smileFeliz = (ImageView) v.findViewById(R.id.smile_feliz);
            smileNeutro = (ImageView) v.findViewById(R.id.smile_neutro);
            //data_nasc = (TextView) v.findViewById(R.id.data_nasc);
            //escola = (TextView) v.findViewById(R.id.escola);
        }
    }

    public AtividadeAdapter(List<Atividade> atividades, int id_inscrito, int id_tutor, int rowLayout, Context context) {
        this.atividades = atividades;
        this.id_inscrito = id_inscrito;
        this.id_tutor = id_tutor;
        this.rowLayout = rowLayout;
        this.context = context;
        this.syncDatabaseApi = new SyncDatabaseApi(context);
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
    public void onBindViewHolder(final AtividadeViewHolder holder, final int position) {
        holder.descricao.setText(atividades.get(position).getDescricao());
        holder.smileFeliz.setImageResource(R.drawable.ic_feliz_off);
        holder.smileNeutro.setImageResource(R.drawable.ic_neutro_off);
        holder.smileTriste.setImageResource(R.drawable.ic_triste_off);

        holder.smileFeliz.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                holder.smileFeliz.setImageResource(R.drawable.ic_feliz_on);
                holder.smileNeutro.setImageResource(R.drawable.ic_neutro_off);
                holder.smileTriste.setImageResource(R.drawable.ic_triste_off);

                FeedbackDAO feedbackDAO = new FeedbackDAO(context);
                Feedback feedback = new Feedback(id_tutor, id_inscrito, atividades.get(position).getId(), 3, getCurrentTimeStamp(), "");
                feedbackDAO.openConection();
                feedbackDAO.insertFeedback(feedback);
                feedbackDAO.closeConection();
                Toast.makeText(context, "Smile Feliz", Toast.LENGTH_SHORT).show();

                final Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        syncDatabaseApi.syncFeedback();
                    }
                });
                thread.start();

            }
        });

        holder.smileTriste.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                holder.smileTriste.setImageResource(R.drawable.ic_triste_on);
                holder.smileNeutro.setImageResource(R.drawable.ic_neutro_off);
                holder.smileFeliz.setImageResource(R.drawable.ic_feliz_off);

                FeedbackDAO feedbackDAO = new FeedbackDAO(context);
                Feedback feedback = new Feedback(id_tutor, id_inscrito, atividades.get(position).getId(), 1, getCurrentTimeStamp(), "");
                feedbackDAO.openConection();
                feedbackDAO.insertFeedback(feedback);
                feedbackDAO.closeConection();
                Toast.makeText(context, "Smile Triste", Toast.LENGTH_SHORT).show();
            }
        });

        holder.smileNeutro.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                holder.smileNeutro.setImageResource(R.drawable.ic_neutro_on);
                holder.smileFeliz.setImageResource(R.drawable.ic_feliz_off);
                holder.smileTriste.setImageResource(R.drawable.ic_triste_off);

                FeedbackDAO feedbackDAO = new FeedbackDAO(context);
                Feedback feedback = new Feedback(id_tutor, id_inscrito, atividades.get(position).getId(), 2, getCurrentTimeStamp(), "");
                feedbackDAO.openConection();
                feedbackDAO.insertFeedback(feedback);
                feedbackDAO.closeConection();
                Toast.makeText(context, "Smile Neutro", Toast.LENGTH_SHORT).show();
            }
        });
        //holder.data_nasc.setText(inscritos.get(position).getDataNasc());
        //holder.escola.setText(inscritos.get(position).getEscola());
    }

    @Override
    public int getItemCount() {
        return atividades.size();
    }

    public static String getCurrentTimeStamp(){
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentDateTime = dateFormat.format(new Date()); // Find todays date

            return currentDateTime;

        } catch (Exception e) {
            e.printStackTrace();

            return null;

        }
    }
}
