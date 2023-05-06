package com.example;

import android.annotation.SuppressLint;
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


public class EntertainmentFragment extends Fragment {

    String API="455a09ecdbc245bb9bbd0ea3d1d07975";
    AdapterClass adapterClass;
    RecyclerView recyclerViewofEnter;
    ArrayList<ModalClass>list;
    String country="in";
    String category="entertainment";







    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_entertainment,container,false);
        recyclerViewofEnter=v.findViewById(R.id.recyclerviewentertainmet);
        recyclerViewofEnter.setLayoutManager(new LinearLayoutManager(getContext()));
        list= new ArrayList<>();
        adapterClass= new AdapterClass(getContext(),list);
        recyclerViewofEnter.setAdapter(adapterClass);
        findNews();
        return v;
    }
    private void findNews()
    {
        ApiUtilities.getApiInterface().getCategoryNews(country,category,100,API).enqueue(new Callback<mainNews>() {
            @Override
            public void onResponse(Call<mainNews> call, Response<mainNews> response) {
                list.addAll(response.body().getArticles());
                adapterClass.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<mainNews> call, Throwable t) {

            }
        });
    }


}