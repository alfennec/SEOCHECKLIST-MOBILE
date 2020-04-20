package com.fennec.seo_checklist.json;

import android.content.Context;

import com.fennec.seo_checklist.entity.App;
import com.fennec.seo_checklist.entity.Progress;
import com.fennec.seo_checklist.myInterface.IonHandler;
import com.fennec.seo_checklist.repository.AppRepository;
import com.fennec.seo_checklist.repository.ProgressRepository;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUrlProgress implements IonHandler {

    public boolean result_succes = false;
    public boolean result_error = false;


    @Override
    public void onSucces(Object obj)
    {
        //Restaurant_Activity.onLoadCategory();
    }

    @Override
    public void onFailed(Object obj)
    {

    }


    public JsonUrlProgress(String link , final Context ctx)
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
                            //Log.d("TAG_JSON", "onClick: SEND URL " + result);
                            ConditionResult( result );
                        }
                    }
                });

    }

    public void ConditionResult(String result)
    {
        if(result.equals("succes"))
        {
            result_succes = true;
        }
        else if(result.equals("error"))
        {
            result_error = true;
        }
        else {
            result_succes = true;
            parse_data(result);
            onSucces(result.toString());
        }
    }

    public void parse_data(String result)
    {
        try
        {
            //JSONObject jObject = new JSONObject(result);
            JSONArray jArray = new JSONArray(result);


            for (int i=0; i < jArray.length(); i++)
            {
                Progress json_progress = new Progress();

                try
                {
                    //INSERT INTO `Progress`(`id`, `id_client`, `id_app`, `id_cat`, `id_item`, `status`, `date_status`)p`)

                    JSONObject oneObject = jArray.getJSONObject(i);
                    json_progress.id                = Integer.parseInt(oneObject.getString("id"));
                    json_progress.id_client         = Integer.parseInt(oneObject.getString("id_client"));
                    json_progress.id_app            = Integer.parseInt(oneObject.getString("id_app"));
                    json_progress.id_cat            = Integer.parseInt(oneObject.getString("id_cat"));
                    json_progress.id_item           = Integer.parseInt(oneObject.getString("id_item"));
                    json_progress.status            = Integer.parseInt(oneObject.getString("status"));
                    json_progress.date_status       = oneObject.getString("date_status");


                }
                catch (JSONException e)
                {
                    //Log.e("tag_json", ""+e);
                }

                ProgressRepository.list_progress.add(json_progress);

            }
        }
        catch (Exception e)
        {

        }
    }
}

