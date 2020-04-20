package com.fennec.seo_checklist.repository;

import com.fennec.seo_checklist.entity.Progress;

import java.util.ArrayList;

public class ProgressRepository {

    public static ArrayList<Progress> list_progress = new ArrayList<>();

    public static boolean getCheckedItem(int id)
    {
        for (int i = 0; i < list_progress.size(); i++)
        {
            if(list_progress.get(i).id_client == ClientRepository.main_Client.id && list_progress.get(i).id_item == id)
            {
                if(list_progress.get(i).status == 1)
                {
                    return true;
                }else {
                    return false;
                }
            }
        }

        return false;
    }

    public static ArrayList<Progress> getByIdApp(int id)
    {
        ArrayList<Progress> current_list = new ArrayList<>();

        for (int i = 0; i < list_progress.size(); i++)
        {
            if(list_progress.get(i).id_app == id)
            {
                current_list.add(list_progress.get(i));
            }
        }

        return current_list;
    }
}
