package com.fennec.seo_checklist.json;
import android.content.Context;

import com.fennec.seo_checklist.entity.Client;
import com.fennec.seo_checklist.myInterface.IonHandler;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Json_url implements IonHandler {

    public int last_id=0;

    @Override
    public void onSucces(Object obj)
    {
        last_id = Integer.parseInt(obj.toString());
        getJsonClient(obj.toString());
    }

    @Override
    public void onFailed(Object obj)
    {

    }

    public Json_url(String link , final Context ctx)
    {

        Ion.with(ctx)
                .load(link)
                .asString()
                .setCallback(new FutureCallback<String>()
                {
                    @Override
                    public void onCompleted(Exception e, String result)
                    {
                        if(result != null)
                        {
                            if(result == "error")
                            {
                                onFailed("error");
                            }
                            else {
                                onSucces(result);
                            }
                        }
                    }
                });

    }


    public void getJsonClient( String result )
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
                    JSONObject oneObject = jArray.getJSONObject(i);

                    json_client.id = Integer.parseInt(oneObject.getString("id"));
                    //json_fam.nbr_plat = Integer.parseInt(oneObject.getString("nbr_plat"));
                }
                catch (JSONException e)
                {
                    //Log.e("tag_json", ""+e);
                }

                //ClientRepository.list_Client.add(json_client);
            }
        }
        catch (Exception e)
        {

        }
    }

}