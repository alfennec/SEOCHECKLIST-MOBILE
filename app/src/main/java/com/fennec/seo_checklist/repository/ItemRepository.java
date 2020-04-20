package com.fennec.seo_checklist.repository;

import com.fennec.seo_checklist.entity.Item;

import java.util.ArrayList;

public class ItemRepository {

    public static ArrayList<Item> list_item = new ArrayList<>();

    public static ArrayList<Item> getByIdCat(int id_cat)
    {
        ArrayList<Item> current_check = new ArrayList<>();

        for (int i = 0; i < list_item.size(); i++)
        {
            if(list_item.get(i).id_cat == id_cat)
            {
                current_check.add(list_item.get(i));
            }
        }

        return current_check;
    }

    public static int getIdCat(int id)
    {
        int current_var = 0;
        for (int i = 0; i < list_item.size(); i++)
        {
            if(list_item.get(i).id == id )
            {
                current_var = list_item.get(i).id_cat;
            }
        }

        return current_var;
    }
}
