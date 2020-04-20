package com.fennec.seo_checklist.repository;

import com.fennec.seo_checklist.entity.Cat;

import java.util.ArrayList;

public class CatRepository {

    public static ArrayList<Cat> list_cat = new ArrayList<>();

    public static ArrayList<Cat> getByIdApp(int id_app)
    {
        ArrayList<Cat> current_cat = new ArrayList<>();

        for (int i = 0; i < list_cat.size(); i++)
        {
            if(list_cat.get(i).id_app == id_app)
            {
                current_cat.add(list_cat.get(i));
            }
        }

        return current_cat;
    }

    public static int getIdApp(int id)
    {
        int current_var = 0;
        for (int i = 0; i < list_cat.size(); i++)
        {
            if(list_cat.get(i).id == id )
            {
                current_var = list_cat.get(i).id_app;
            }
        }

        return current_var;
    }
}
