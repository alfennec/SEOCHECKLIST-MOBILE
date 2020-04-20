package com.fennec.seo_checklist.controller.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fennec.seo_checklist.R;
import com.fennec.seo_checklist.adapter.AppAdapter;
import com.fennec.seo_checklist.repository.AppRepository;
import com.fennec.seo_checklist.repository.ClientRepository;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    public static RecyclerView recyclerView;
    public static LayoutInflater inflater;

    public static View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        root = inflater.inflate(R.layout.fragment_home, container, false);

        this.inflater = inflater;
        //final TextView textView = root.findViewById(R.id.text_home);

        return root;
    }

    public static void onSucces()
    {
        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);
        LinearLayoutManager lm = new LinearLayoutManager(inflater.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(lm);

        AppAdapter appAdapter = new AppAdapter(AppRepository.AffectedApp());
        recyclerView.setAdapter(appAdapter);

        Log.d("TAG_ARRAY", "onSucces: "+ AppRepository.AffectedApp().size());
        Log.e("TAG_ARRAY", "onSucces: "+ ClientRepository.main_Client.first_name);

        for (int i = 0; i < AppRepository.AffectedApp().size(); i++) {

            Log.e("TAG_ARRAY", "onSucces: "+ AppRepository.AffectedApp().get(i).title);
        }
    }
}