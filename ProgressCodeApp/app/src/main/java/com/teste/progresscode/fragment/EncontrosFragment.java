package com.teste.progresscode.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.teste.progresscode.R;
import com.teste.progresscode.adapter.EncontroAdapter;
import com.teste.progresscode.model.object.Encontro;
import com.teste.progresscode.model.object.Tutor;
import com.teste.progresscode.model.dao.EncontroDAO;
import com.teste.progresscode.other.DividerItemDecoration;
import com.teste.progresscode.other.RecyclerItemClickListener;

import java.util.List;

public class EncontrosFragment extends Fragment {

    private static final String TAG = IncritosFragment.class.getSimpleName();

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    protected RecyclerView recyclerView;

    List<Encontro> encontros;
    private Tutor tutor;

    private OnFragmentInteractionListener mListener;

    public EncontrosFragment() {
        // Required empty public constructor
    }


    public static EncontrosFragment newInstance(String param1, String param2) {
        EncontrosFragment fragment = new EncontrosFragment();
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
            tutor = bundleToTutor();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_encontros, container, false);
        rootView.setTag(TAG);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.encontros_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));

        EncontroDAO encontroDAO = new EncontroDAO(getActivity());
        encontroDAO.openConection();
        encontros = encontroDAO.getAllEncontros();
        encontroDAO.closeConection();

        recyclerView.setAdapter(new EncontroAdapter(encontros, R.layout.row_encontro, getActivity()));

        // Listener do click em item da lista
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Toast.makeText(getActivity(), "Encontro: " + encontros.get(position).getDataRealizao(), Toast.LENGTH_SHORT).show();

                        Bundle bundle = new Bundle();

                        bundle.putInt("id_encontro", encontros.get(position).getId());
                        bundle.putInt("id_tutor", tutor.getId());
                        bundle.putString("nome_tutor", tutor.getNome());
                        bundle.putString("email_tutor", tutor.getEmail());

                        Fragment incritosFragment = new IncritosFragment();
                        incritosFragment.setArguments(bundle);

                        FragmentManager fm = getFragmentManager();
                        FragmentTransaction transaction = fm.beginTransaction();
                        transaction.replace(R.id.frame, incritosFragment);
                        transaction.commit();

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
