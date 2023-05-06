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

public class HealthFragment extends Fragment {
    String API="455a09ecdbc245bb9bbd0ea3d1d07975";
    AdapterClass adapterClass;
    RecyclerView recyclerViewofHealth;
    String country="in";
    String category="health";
    ArrayList<ModalClass> list;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_health,container,false);
        recyclerViewofHealth=v.findViewById(R.id.recyclerviewenterhealth);
        list= new ArrayList<>();
        recyclerViewofHealth.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterClass= new AdapterClass(getContext(), list);
        recyclerViewofHealth.setAdapter(adapterClass);
        findNews();
        return v;
    }
    private void  findNews()
    {
        ApiUtilities.getApiInterface().getCategoryNews(country, category,100,API).enqueue(new Callback<mainNews>() {
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