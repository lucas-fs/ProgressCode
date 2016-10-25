package com.teste.progresscode.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.teste.progresscode.R;
import com.teste.progresscode.activity.AboutUsActivity;
import com.teste.progresscode.adapter.EncontroAdapter;
import com.teste.progresscode.model.Encontro;
import com.teste.progresscode.model.response.EncontroResponse;
import com.teste.progresscode.other.DividerItemDecoration;
import com.teste.progresscode.other.RecyclerItemClickListener;
import com.teste.progresscode.rest.ApiClient;
import com.teste.progresscode.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EncontrosFragment extends Fragment {

    private static final String TAG = IncritosFragment.class.getSimpleName();

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    protected RecyclerView recyclerView;

    List<Encontro> encontros;

    private String mParam1;
    private String mParam2;

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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
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

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<EncontroResponse> call = apiService.getAllEncontros();
        call.enqueue(new Callback<EncontroResponse>() {
            @Override
            public void onResponse(Call<EncontroResponse> call, Response<EncontroResponse> response) {
                int statusCode = response.code();
                encontros = response.body().getEncontros();
                recyclerView.setAdapter(new EncontroAdapter(encontros, R.layout.row_encontro, getActivity()));
            }

            @Override
            public void onFailure(Call<EncontroResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });

        // Listener do click em item da lista
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Toast.makeText(getActivity(), "Encontro: "+encontros.get(position).getDataRealizao(), Toast.LENGTH_SHORT).show();

                        Bundle bundle = new Bundle();
                        bundle.putInt("idEncontro",encontros.get(position).getId());
                        Intent intent = new Intent(getActivity(), AboutUsActivity.class);
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
        void onFragmentInteraction(Uri uri);
    }
}
