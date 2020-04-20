package com.fennec.seo_checklist.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.fennec.seo_checklist.R;
import com.fennec.seo_checklist.controller.MainActivity;
import com.fennec.seo_checklist.entity.App;
import com.fennec.seo_checklist.repository.CatRepository;

import java.util.List;

public class AppAdapter extends RecyclerView.Adapter<AppAdapter.MyViewHolder> {

    public List<App> list;
    public boolean showAdd = false;

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        public TextView title, tv_nbr;
        public View parent;


        public MyViewHolder(View view)
        {
            super(view);
            parent=view;
            title = (TextView) view.findViewById(R.id.tv_title);
            tv_nbr = (TextView) view.findViewById(R.id.tv_nbr);
        }
    }

    public AppAdapter(List<App> list)
    {
        this.list = list;
    }

    @Override
    public AppAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_app, parent, false);

        return new AppAdapter.MyViewHolder(itemView);
    }

    @Override
    public int getItemViewType(int position)
    {
        /*if(position == getItemCount()-1 && showAdd)
            return 2;
        return 1;*/

        if (position == 0) return 1;
        else return 2;
    }

    @Override
    public void onBindViewHolder(final AppAdapter.MyViewHolder holder, final int position)
    {
        final App myApp = list.get(position);
        holder.title.setText(myApp.title);
        holder.tv_nbr.setText(myApp.text1);

        holder.parent.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                MainActivity.open_act(myApp.id);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }
}


