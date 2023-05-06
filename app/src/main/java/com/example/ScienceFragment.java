package com.example;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kukdookuu.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ScienceFragment extends Fragment {


    String API="455a09ecdbc245bb9bbd0ea3d1d07975";
    AdapterClass adapterclass;
    ArrayList<ModalClass> list;
    RecyclerView recyclerViewofScience;
    private String category="science";
    private String country="in";



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_science,container,false);
        recyclerViewofScience= v.findViewById(R.id.recyclerviewenterscience);
        list= new ArrayList<>();
        recyclerViewofScience.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterclass= new AdapterClass(getContext(),list);
        recyclerViewofScience.setAdapter(adapterclass);
        findNews();
        return v;
    }
    private void findNews()
    {
        ApiUtilities.getApiInterface().getCategoryNews(country,category,100,API).enqueue(new Callback<mainNews>() {
            @Override
            public void onResponse(Call<mainNews> call, Response<mainNews> response) {
                list.addAll(response.body().getArticles());
                adapterclass.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<mainNews> call, Throwable t) {

            }
        });
    }
}