package com.teste.progresscode.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teste.progresscode.R;
import com.teste.progresscode.adapter.InscritoAdapter;
import com.teste.progresscode.model.Inscrito;
import com.teste.progresscode.model.InscritoResponse;
import com.teste.progresscode.rest.ApiClient;
import com.teste.progresscode.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IncritosFragment extends Fragment {

    private static final String TAG = IncritosFragment.class.getSimpleName();

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    protected RecyclerView recyclerView;

    List<Inscrito> inscritos;

    private String mParam1;
    private String mParam2;

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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_incritos, container, false);
        rootView.setTag(TAG);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.inscritos_rv);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<InscritoResponse> call = apiService.getAllInscritos();
        call.enqueue(new Callback<InscritoResponse>() {
            @Override
            public void onResponse(Call<InscritoResponse> call, Response<InscritoResponse> response) {
                int statusCode = response.code();
                inscritos = response.body().getInscritos();
                recyclerView.setAdapter(new InscritoAdapter(inscritos, R.layout.row_inscrito, getContext()));
            }

            @Override
            public void onFailure(Call<InscritoResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });

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
}
