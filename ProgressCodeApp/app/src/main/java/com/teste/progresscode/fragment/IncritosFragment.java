package com.teste.progresscode.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.teste.progresscode.R;
import com.teste.progresscode.activity.AtividadesActivity;
import com.teste.progresscode.adapter.InscritoAdapter;
import com.teste.progresscode.model.object.Inscrito;
import com.teste.progresscode.model.object.Tutor;
import com.teste.progresscode.model.dao.InscritoDAO;
import com.teste.progresscode.other.DividerItemDecoration;
import com.teste.progresscode.other.RecyclerItemClickListener;

import java.util.List;

public class IncritosFragment extends Fragment {

    private static final String TAG = IncritosFragment.class.getSimpleName();

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    protected RecyclerView recyclerView;

    private List<Inscrito> inscritos;
    private Tutor tutor;
    private int idEncontro;

    private OnFragmentInteractionListener mListener;

    public IncritosFragment() {
        // Required empty public constructor
    }

    public static IncritosFragment newInstance(String param1, String param2) {
        IncritosFragment fragment = new IncritosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            idEncontro = this.getArguments().getInt("id_encontro");
            InscritoDAO inscritoDAO = new InscritoDAO(getActivity());

            inscritoDAO.openConection();

            if (idEncontro != -1) {
                tutor = bundleToTutor();
                inscritos = inscritoDAO.getInscritosByEncontro(idEncontro);
            } else {
                inscritos = inscritoDAO.getAllInscritos();
            }

            inscritoDAO.closeConection();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_incritos, container, false);
        rootView.setTag(TAG);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.inscritos_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(new InscritoAdapter(inscritos, R.layout.row_inscrito, getActivity()));

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Toast.makeText(getActivity(), "Inscrito: " + inscritos.get(position).getNome(), Toast.LENGTH_SHORT).show();

                        Bundle bundle = new Bundle();
                        bundle.putInt("id_encontro", idEncontro);
                        bundle.putInt("id_tutor", tutor.getId());
                        bundle.putString("nome_tutor", tutor.getNome());
                        bundle.putString("email_tutor", tutor.getEmail());
                        bundle.putInt("id_inscrito", inscritos.get(position).getId());
                        bundle.putString("nome_inscrito", inscritos.get(position).getNome());

                        Intent intent = new Intent(getActivity(), AtividadesActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                })
        );

        return rootView;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private Tutor bundleToTutor() {
        Tutor tutor = new Tutor();
        tutor.setId(this.getArguments().getInt("id_tutor"));
        tutor.setNome(this.getArguments().getString("nome_tutor"));
        tutor.setEmail(this.getArguments().getString("email_tutor"));

        return tutor;
    }
}
