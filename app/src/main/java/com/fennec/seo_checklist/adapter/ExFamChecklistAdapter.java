package com.fennec.seo_checklist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;
import com.fennec.seo_checklist.R;
import com.fennec.seo_checklist.entity.Cat;
import com.fennec.seo_checklist.entity.Item;

import java.util.ArrayList;

public class ExFamChecklistAdapter extends ExpandableRecyclerAdapter<Cat, Item, CatViewHolder, ItemlistViewHolder> {

    private LayoutInflater mInflater;

    public ExFamChecklistAdapter(Context context, @NonNull ArrayList<Cat> myfam_checklist)
    {
        super(myfam_checklist);
        mInflater = LayoutInflater.from(context);
    }

    // onCreate ...
    @Override
    public CatViewHolder onCreateParentViewHolder(@NonNull ViewGroup parentViewGroup, int viewType)
    {
        View myfamViewHolder = mInflater.inflate(R.layout.item_cat, parentViewGroup, false);
        return new CatViewHolder(myfamViewHolder);
    }

    @Override
    public ItemlistViewHolder onCreateChildViewHolder(@NonNull ViewGroup childViewGroup, int viewType)
    {
        View mychecklistViewHolder = mInflater.inflate(R.layout.item_check, childViewGroup, false);
        return new ItemlistViewHolder(mychecklistViewHolder);
    }

    @Override
    public void onBindParentViewHolder(@NonNull CatViewHolder myFamViewHolder, int parentPosition, @NonNull Cat myfam_checklist)
    {
        myFamViewHolder.bind(myfam_checklist);
    }

    @Override
    public void onBindChildViewHolder(@NonNull ItemlistViewHolder mychecklistViewHolder, int parentPosition, int childPosition, @NonNull Item mychecklist)
    {
        mychecklistViewHolder.bind(mychecklist);
    }
}

