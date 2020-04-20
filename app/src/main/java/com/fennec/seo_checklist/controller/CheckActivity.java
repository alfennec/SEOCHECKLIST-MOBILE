package com.fennec.seo_checklist.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.fennec.seo_checklist.R;
import com.fennec.seo_checklist.adapter.ExFamChecklistAdapter;
import com.fennec.seo_checklist.entity.Progress;
import com.fennec.seo_checklist.json.JsonSetProgress;
import com.fennec.seo_checklist.json.JsonUrlApp;
import com.fennec.seo_checklist.json.JsonUrlCat;
import com.fennec.seo_checklist.json.JsonUrlItem;
import com.fennec.seo_checklist.json.constant;
import com.fennec.seo_checklist.repository.CatRepository;
import com.fennec.seo_checklist.repository.ClientRepository;
import com.fennec.seo_checklist.repository.ItemRepository;
import com.fennec.seo_checklist.repository.ProgressRepository;

public class CheckActivity extends AppCompatActivity {

    public static CheckActivity main;
    public static RecyclerView recyclerView;

    public static ProgressDialog dialog;

    public static int id_app;

    public static TextView tv_pourcentage;
    public static ProgressBar pb;
    public static Button reset;

    public static int prog = 0;

    public static RecyclerView mRecyclerView;
    public static ExFamChecklistAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        main = this;

        Intent intent = getIntent();
        id_app = intent.getIntExtra("id_app",0);

        /************ DELETE ALL DATA **/
        CatRepository.list_cat.clear();
        ItemRepository.list_item.clear();

        /**** begin your lab to work **/
        dialog =  ProgressDialog.show(main, "", "Loading. Please wait...", true);
        Onload_item();

        /**** begin your lab to work **/

        tv_pourcentage = (TextView) findViewById(R.id.tv_pourcentage);
        pb = (ProgressBar) findViewById(R.id.progressBar);

        update_progress();

        reset = (Button) findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                reset_check();
                Toast.makeText(main, "Reset all checkbox ...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void reset_check()
    {
        for (int i = 0; i < ProgressRepository.list_progress.size(); i++)
        {
            if (id_app == ProgressRepository.list_progress.get(i).id_app)
            {
                if(ProgressRepository.list_progress.get(i).status == 1)
                {
                    ProgressRepository.list_progress.get(i).status = 0;

                    /** get data only for this user **/
                    //localhost/commando/json/setProgress.php?id_client=1&id_app=1&id_cat=1&id_item=1&status=1&date_status=1

                    String url_informations     = constant.url_host+"/json/setProgress.php";
                    url_informations            +="?id_client="+ ClientRepository.main_Client.id;
                    url_informations            +="&id_app="+CatRepository.getIdApp(ProgressRepository.list_progress.get(i).id_cat);
                    url_informations            +="&id_cat="+ProgressRepository.list_progress.get(i).id_cat;
                    url_informations            +="&id_item="+ProgressRepository.list_progress.get(i).id_item;
                    url_informations            +="&status=0";
                    url_informations            +="&date_status=1";


                    JsonSetProgress jsonUrlSetProgress = new JsonSetProgress(url_informations, main);

                    Log.e("TAG_JSON_URL",url_informations);
                }
            }
        }

        onSuccesFam();
        update_progress();
        tv_pourcentage.setText(""+0+"%");
        pb.setProgress(0);
    }

    public static void update_progress()
    {
        prog = 0;
        double count= 0;

        if(ProgressRepository.getByIdApp(id_app).size() != 0)
        {
            count = 100/(ProgressRepository.getByIdApp(id_app).size());
        }

        Log.e("TAG COUNT", "the count is : "+count+" "+ProgressRepository.getByIdApp(id_app).size());

        for (int i = 0; i < ProgressRepository.getByIdApp(id_app).size(); i++)
        {
            if(ProgressRepository.getByIdApp(id_app).get(i).status == 1)
            {
                prog+= count;
            }
        }

        tv_pourcentage.setText(""+prog+"%");
        pb.setProgress(prog);
    }

    public static void Onload_item()
    {
        String url_informations = constant.url_host+"/json/getTable.php?table=items";
        JsonUrlItem jsonUrlItem = new JsonUrlItem(url_informations, main);
        Log.d("TAG_DEPLOY", " item : "+url_informations);
    }

    public static void onSuccesItem()
    {
        Onload_fam();
    }

    public static void Onload_fam()
    {
        String url_informations = constant.url_host+"/json/getTable.php?table=cat";
        JsonUrlCat jsonUrlCat = new JsonUrlCat(url_informations, main);
        Log.d("TAG_DEPLOY", " cat : "+url_informations);
    }

    public static void onSuccesFam()
    {
        mRecyclerView = (RecyclerView) main.findViewById(R.id.recyclerView);
        adapter = new ExFamChecklistAdapter(main, CatRepository.getByIdApp(id_app));
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(main));

        //
        // update_progress();

        dialog.dismiss();
    }
}
