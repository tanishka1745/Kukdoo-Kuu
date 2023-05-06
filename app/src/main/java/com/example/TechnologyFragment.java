package com.example;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kukdookuu.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TechnologyFragment extends Fragment {

    String API="455a09ecdbc245bb9bbd0ea3d1d07975";
    AdapterClass adapterClass;
    RecyclerView recyclerViewofTechno;
    ArrayList<ModalClass>list;
    String category="technology";
    String country="in";





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_technology,container,false);
        recyclerViewofTechno=v.findViewById(R.id.recyclerviewentertechnology);
        recyclerViewofTechno.setLayoutManager(new LinearLayoutManager(getContext()));
        list= new ArrayList<>();
        adapterClass=new AdapterClass(getContext(),list);
        recyclerViewofTechno.setAdapter(adapterClass);
        findNews();
        return v;
    }
    private void findNews()
    {
        ApiUtilities.getApiInterface().getCategoryNews(country,category,100,API).enqueue(new Callback<mainNews>() {
            @Override
            public void onResponse(Call<mainNews> call, Response<mainNews> response) {
                //get the articles which contains all data in the list
                list.addAll(response.body().getArticles());
                adapterClass.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<mainNews> call, Throwable t) {

            }
        });
    }

}