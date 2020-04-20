package com.fennec.seo_checklist.repository;


import com.fennec.seo_checklist.entity.App;

import java.util.ArrayList;

public class AppRepository {

    public static ArrayList<App> list_App = new ArrayList<App>();

    public static ArrayList<App> AffectedApp()
    {
        ArrayList<App> temp_listApp = new ArrayList<>();

        for (int i = 0; i < list_App.size(); i++)
        {
            for (int j = 0; j < AssignedRepository.list_assigned.size(); j++)
            {
                if(AssignedRepository.list_assigned.get(j).id_app == list_App.get(i).id)
                {
                    temp_listApp.add(list_App.get(i));
                }
            }
        }

        return temp_listApp;
    }
}
