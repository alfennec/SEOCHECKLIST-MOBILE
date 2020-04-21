package com.fennec.seo_checklist.json;

import android.content.Context;
import android.util.Log;

import com.fennec.seo_checklist.controller.HomeActivity;
import com.fennec.seo_checklist.entity.Client;
import com.fennec.seo_checklist.myInterface.IonHandler;
import com.fennec.seo_checklist.repository.ClientRepository;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUrlClient implements IonHandler {

    @Override
    public void onSucces(Object obj)
    {
        String result = obj.toString().replaceAll("\\s","");

        try
        {
            if(Integer.parseInt(result) == 406 )
            {
                HomeActivity.OnFailedLogin();
            }else
            {
                HomeActivity.OnFailedLogin();
            }
        }catch (Exception e)
        {
            getJsonClient(obj.toString());
            HomeActivity.OnSuccesLogin();
        }



    }

    @Override
    public void onFailed(Object obj)
    {
        //MainActivity.OnFailedLogin();
        Log.d("TAG_JSON", "onClick: SEND URL on failed ");
    }

    public JsonUrlClient(String link , final Context ctx)
    {
        Ion.with(ctx)
                .load(link)
                .asString()
                .setCallback(new FutureCallback<String>()
                {
                    @Override
                    public void onCompleted(Exception e, String result)
                    {
                        try {

                            Log.d("TAG_JSON", "onClick: SEND URL before if " + result + " the exepction : "+e);
                            if(result != null)
                            {
                                Log.d("TAG_JSON", "onClick: SEND URLin if " + result +"LPR");
                                onSucces(result);
                            }else
                            {
                                Log.d("TAG_JSON", "onClick: SEND URLin else " + result);
                                onFailed(-1);
                            }

                        }catch (Exception ex)
                        {
                            Log.e("TAG_JSON", "problème :  " + ex);
                        }

                    }
                });
    }

    public void getJsonClient(String result)
    {
        try
        {
            //JSONObject jObject = new JSONObject(result);
            JSONArray jArray = new JSONArray(result);

            for (int i=0; i < jArray.length(); i++)
            {
                Client json_client = new Client();

                try
                {
                    //INSERT INTO `client`(`id`, `first_name`, `last_name`, `tel`, `sexe`, `email`, `pass`, `role`)

                    JSONObject oneObject = jArray.getJSONObject(i);

                    json_client.id      = Integer.parseInt(oneObject.getString("id"));
                    json_client.first_name   = oneObject.getString("first_name");
                    json_client.last_name    = oneObject.getString("last_name");
                    json_client.tel     = oneObject.getString("tel");
                    json_client.sexe  = Integer.parseInt(oneObject.getString("sexe"));
                    json_client.email     = oneObject.getString("email");
                    json_client.role    = Integer.parseInt(oneObject.getString("role"));

                }
                catch (JSONException e)
                {
                    //Log.e("tag_json", ""+e);
                }

                ClientRepository.main_Client = json_client;
                Log.e("tag_json", ClientRepository.main_Client.email);


            }
        }
        catch (Exception e)
        {

        }
    }
}

