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


public class HomeFragment extends Fragment {

    String API="455a09ecdbc245bb9bbd0ea3d1d07975";
    ArrayList<ModalClass> list;
    AdapterClass adapterClass;
    String country="in";
    RecyclerView recyclerViewoHome;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_home,null);
        recyclerViewoHome=v.findViewById(R.id.recyclerviewhome);
        list= new ArrayList<>();
        recyclerViewoHome.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterClass= new AdapterClass(getContext(),list);
        recyclerViewoHome.setAdapter(adapterClass);

        findNews();
        return v;
    }
    private void findNews() {
        ApiUtilities.getApiInterface().getNews(country,100,API).enqueue(new Callback<mainNews>() {
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
