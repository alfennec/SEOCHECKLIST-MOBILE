package com.fennec.seo_checklist.adapter;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;
import com.fennec.seo_checklist.R;
import com.fennec.seo_checklist.controller.CheckActivity;
import com.fennec.seo_checklist.entity.Item;
import com.fennec.seo_checklist.entity.Progress;
import com.fennec.seo_checklist.json.JsonSetProgress;
import com.fennec.seo_checklist.json.JsonUrlAssigned;
import com.fennec.seo_checklist.json.JsonUrlProgress;
import com.fennec.seo_checklist.json.constant;
import com.fennec.seo_checklist.repository.CatRepository;
import com.fennec.seo_checklist.repository.ClientRepository;
import com.fennec.seo_checklist.repository.ItemRepository;
import com.fennec.seo_checklist.repository.ProgressRepository;

import java.util.Calendar;

public class ItemlistViewHolder extends ChildViewHolder {

    private CheckBox checkBox2;
    public ImageButton button_link;

    public boolean inif = false;

    public ItemlistViewHolder(View itemView)
    {
        super(itemView);
        checkBox2 = itemView.findViewById(R.id.checkBox2);
        button_link = itemView.findViewById(R.id.button_link);

    }

    public void bind(final Item mychecklist)
    {
        checkBox2.setText(mychecklist.intituler);
        checkBox2.setChecked(ProgressRepository.getCheckedItem(mychecklist.id));

        button_link.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String url = "http://"+mychecklist.link;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                CheckActivity.main.startActivity(i);
            }
        });

        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                int id_client = ClientRepository.main_Client.id;
                int id_app = CatRepository.getIdApp(mychecklist.id_cat);
                int id_cat = mychecklist.id_cat;
                int id_item = mychecklist.id;
                int status = 1;
                String date_status =  ""+Calendar.getInstance().getTime();

                if(isChecked)
                {
                    //ProgressRepository.list_progress.add(new Progress(id_client, id_app, id_cat, id_item, 1, date_status));
                    for (int i = 0; i < ProgressRepository.list_progress.size(); i++)
                    {
                        if(ProgressRepository.list_progress.get(i).id_app == CheckActivity.id_app
                                && ProgressRepository.list_progress.get(i).id_item == mychecklist.id)
                        {
                            ProgressRepository.list_progress.get(i).status = 1;
                        }
                    }


                    /** get data only for this user **/
                    //localhost/commando/json/setProgress.php?id_client=1&id_app=1&id_cat=1&id_item=1&status=1&date_status=1

                    String url_informations     = constant.url_host+"/json/setProgress.php";
                    url_informations            +="?id_client="+ClientRepository.main_Client.id;
                    url_informations            +="&id_app="+CatRepository.getIdApp(mychecklist.id_cat);
                    url_informations            +="&id_cat="+mychecklist.id_cat;
                    url_informations            +="&id_item="+mychecklist.id;
                    url_informations            +="&status=1";
                    url_informations            +="&date_status=1";


                    JsonSetProgress jsonUrlSetProgress = new JsonSetProgress(url_informations, CheckActivity.main);

                    Log.e("TAG_JSON_URL",url_informations);
                    CheckActivity.update_progress();

                }else {
                    //ProgressRepository.list_progress.add(new Progress(id_client, id_app, id_cat, id_item, 0, date_status));
                    for (int i = 0; i < ProgressRepository.list_progress.size(); i++)
                    {
                        if(ProgressRepository.list_progress.get(i).id_app == CheckActivity.id_app
                                && ProgressRepository.list_progress.get(i).id_item == mychecklist.id)
                        {
                            ProgressRepository.list_progress.get(i).status = 0;
                        }
                    }

                    /** get data only for this user **/
                    //localhost/commando/json/setProgress.php?id_client=1&id_app=1&id_cat=1&id_item=1&status=1&date_status=1

                    String url_informations     = constant.url_host+"/json/setProgress.php?";
                    url_informations            +="?id_client="+ClientRepository.main_Client.id;
                    url_informations            +="&id_app="+CatRepository.getIdApp(mychecklist.id_cat);
                    url_informations            +="&id_cat="+mychecklist.id_cat;
                    url_informations            +="&id_item="+mychecklist.id;
                    url_informations            +="&status=0";
                    url_informations            +="&date_status=1";


                    JsonSetProgress jsonUrlSetProgress = new JsonSetProgress(url_informations, CheckActivity.main);

                    Log.e("TAG_JSON_URL",url_informations);
                    CheckActivity.update_progress();
                }




            }
        });
    }
}
