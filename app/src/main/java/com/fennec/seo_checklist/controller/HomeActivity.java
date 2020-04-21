package com.fennec.seo_checklist.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.fennec.seo_checklist.R;
import com.fennec.seo_checklist.json.JsonUrlClient;
import com.fennec.seo_checklist.json.constant;
import com.fennec.seo_checklist.repository.ClientRepository;
import com.google.android.material.textfield.TextInputLayout;

public class HomeActivity extends AppCompatActivity {

    public static HomeActivity main;
    public static String MY_PREFS_NAME = "first_log";

    public ProgressBar progressBar2;
    public int progress = 0;

    TextInputLayout editText_email;
    TextInputLayout editText_pass;

    public static ProgressDialog dialog;

    public Handler handler = new Handler();

    public JsonUrlClient jsonClient;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        main = this;

        //setContentView(R.layout.activity_home);

        if(isNetworkConnected())
        {
            if(isSharedPreferences())
            {
                Intent intent = new Intent(main, MainActivity.class);
                startActivity(intent);

                main.finish();
            }
            else{
                setContentView(R.layout.login_form);

                editText_email = (TextInputLayout) findViewById(R.id.editText_email);
                editText_pass = (TextInputLayout) findViewById(R.id.editText_pass);

                Button Button_valider = (Button) findViewById(R.id.Button_valider);

                Button Button_registre = (Button) findViewById(R.id.Button_registre);

                Button_valider.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        if(verifyIfBlank(editText_email) && verifyIfBlank(editText_pass))
                        {
                            String url_informations = "json/getClient.php?";

                            String email = "email="+editText_email.getEditText().getText().toString();
                            String pass = "&pass="+editText_pass.getEditText().getText().toString();

                            url_informations = constant.url_host+url_informations+email+pass;

                            //Toast.makeText(main,""+editText_email.getEditText().getText().toString(), Toast.LENGTH_SHORT).show();

                            Log.d("TAG_DEPLOY", " app : "+url_informations);

                            jsonClient = new JsonUrlClient(url_informations, main);

                            dialog = ProgressDialog.show(main, "", "\n" + "Data processing. Please wait ...", true);
                        }else
                        {
                            OnFailedLogin();
                        }
                    }
                });

                Button_registre.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        /*Intent intent = new Intent(main, Register_form.class);
                        main.startActivity(intent);*/

                        Costum_toast("Contact your provider for an account");
                    }
                });

            }
        }else {
            setContentView(R.layout.not_connected);

            Button btn_rafraichir = (Button) findViewById(R.id.btn_rafraichir);

            btn_rafraichir.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    main.finish();
                    startActivity(main.getIntent());
                }
            });
        }
    }


    public static boolean verifyIfBlank(TextInputLayout input)
    {
        if (TextUtils.isEmpty(input.getEditText().getText().toString()))
        {
            input.setError("Empty row");
            return false;
        }else {
            input.setErrorEnabled(false);
            return true;
        }
    }

    public static void OnSuccesLogin()
    {
        dialog.dismiss();

        Costum_toast("\n" + "Connection made successfully !! ");

        Intent intent = new Intent(main, MainActivity.class);
        main.startActivity(intent);

        SharedPreferences prefs = main.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor edit= prefs.edit();

        edit.putInt("id", ClientRepository.main_Client.id);
        edit.putString("first_name", ClientRepository.main_Client.first_name);
        edit.putString("last_name", ClientRepository.main_Client.last_name);
        edit.putString("tel", ClientRepository.main_Client.tel);
        edit.putInt("sexe", ClientRepository.main_Client.sexe);
        edit.putString("email", ClientRepository.main_Client.email);
        edit.putInt("role", ClientRepository.main_Client.role);

        edit.commit();
        main.finish();

    }

    public static void OnFailedLogin()
    {
        dialog.dismiss();
        Costum_toast(" \n" + "Incorrect email or password !");
    }

    public boolean isSharedPreferences()
    {
        //INSERT INTO `client`(`id`, `first_name`, `last_name`, `tel`, `sexe`, `email`, `pass`, `role`)

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        int id = prefs.getInt("id", 0);
        String first_name = prefs.getString("first_name", "vide");
        String last_name = prefs.getString("last_name", "vide");
        String tel = prefs.getString("tel", "vide");
        int sexe = prefs.getInt("sexe", 0);
        String email = prefs.getString("email", "vide");
        int role = prefs.getInt("role", 0);

        ClientRepository.main_Client.id = id;
        ClientRepository.main_Client.first_name = first_name;
        ClientRepository.main_Client.last_name = last_name;
        ClientRepository.main_Client.tel = tel;
        ClientRepository.main_Client.sexe = sexe;
        ClientRepository.main_Client.email = email;
        ClientRepository.main_Client.role = role;

        if(id == 0 && email.equals("vide"))
        {
            return false;
        }else {
            return true;
        }
    }

    private boolean isNetworkConnected()
    {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    public static void Costum_toast(String msg)
    {
        /** Costume toast to test**/
        LayoutInflater inflater = main.getLayoutInflater();
        View layout = inflater.inflate(R.layout.activity_costum_toast,
                (ViewGroup) main.findViewById(R.id.toast_layout_root));

        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText(msg);

        Toast toast = new Toast(main.getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
        /** end teaosot **/
    }

    public static void quitter()
    {
        SharedPreferences prefs = main.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor edit= prefs.edit();

        edit.putInt("id", 0);
        edit.putString("email", "vide");
        edit.putString("nom", "vide");
        edit.putString("prenom", "vide");
        edit.putString("tel", "vide");
        edit.putString("adresse", "vide");
        edit.putString("ville", "vide");
        edit.putInt("sexe", 0);

        edit.commit();
    }
}
