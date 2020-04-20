package com.fennec.seo_checklist.controller;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import com.fennec.seo_checklist.R;
import com.fennec.seo_checklist.controller.ui.home.HomeFragment;
import com.fennec.seo_checklist.entity.Progress;
import com.fennec.seo_checklist.json.JsonUrlApp;
import com.fennec.seo_checklist.json.JsonUrlAssigned;
import com.fennec.seo_checklist.json.JsonUrlCat;
import com.fennec.seo_checklist.json.JsonUrlClient;
import com.fennec.seo_checklist.json.JsonUrlItem;
import com.fennec.seo_checklist.json.JsonUrlProgress;
import com.fennec.seo_checklist.json.constant;
import com.fennec.seo_checklist.repository.AppRepository;
import com.fennec.seo_checklist.repository.AssignedRepository;
import com.fennec.seo_checklist.repository.CatRepository;
import com.fennec.seo_checklist.repository.ClientRepository;
import com.fennec.seo_checklist.repository.ItemRepository;
import com.fennec.seo_checklist.repository.ProgressRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    public static MainActivity main;

    public static ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);

        main = this;



        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Visite our web site", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        /*********************** get assigned than get app **********************/

        dialog =  ProgressDialog.show(main, "", "Loading. Please wait...", true);
        Onload_Assigned();

    }

    public static void onSuccesAssigned()
    {
        Onload_App();
    }

    public static void onSuccesApp()
    {
        HomeFragment.onSucces();
        dialog.dismiss();
    }

    public static void onFaild()
    {
        //dialog.dismiss();
    }

    public static void Onload_Assigned()
    {
        /** get ALL DATA YOU NEED HERE **************************************************************/
        /** clear data first **/
        AppRepository.list_App.clear();
        AssignedRepository.list_assigned.clear();
        ProgressRepository.list_progress.clear();

        /** get data only for this user **/
        String url_informations1 = constant.url_host+"/json/getTableById.php?table=";
        String url_informations2 ="&id="+ ClientRepository.main_Client.id;

        JsonUrlAssigned jsonUrlAssigned = new JsonUrlAssigned(url_informations1+"affect"+url_informations2, main);
        JsonUrlProgress jsonUrlProgress = new JsonUrlProgress(url_informations1+"progress"+url_informations2, main);


        Log.d("TAG_JSON_URL",url_informations1+"affect"+url_informations2);
        Log.d("TAG_JSON_URL",url_informations1+"progress"+url_informations2);
    }

    public static void Onload_App()
    {
        /** get RESTAURANT & CATEGORIE PLAT **/
        String url_informations = constant.url_host+"/json/getTable.php?table=";
        JsonUrlApp jsonUrlApp = new JsonUrlApp(url_informations+"apps", main);

        Log.d("TAG_JSON_URL",url_informations+"apps");
    }

    public static void open_act(int id_app)
    {
        Intent intent = new Intent(main, CheckActivity.class);
        intent.putExtra("id_app",id_app);
        main.startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp()
    {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
