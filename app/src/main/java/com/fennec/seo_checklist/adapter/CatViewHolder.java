package com.fennec.seo_checklist.adapter;

import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ParentViewHolder;
import com.fennec.seo_checklist.R;
import com.fennec.seo_checklist.entity.Cat;

public class CatViewHolder extends ParentViewHolder {

    private TextView intituler_category;
    public TextView tv_nbr;


    public CatViewHolder(View itemView)
    {
        super(itemView);
        intituler_category = itemView.findViewById(R.id.tv_title);
        tv_nbr   = (TextView) itemView.findViewById(R.id.tv_nbr);
    }

    public void bind(Cat myfam_exAdapter)
    {
        intituler_category.setText(myfam_exAdapter.intituler);
        tv_nbr.setText(""+myfam_exAdapter.nbr_item+" items");
    }
}