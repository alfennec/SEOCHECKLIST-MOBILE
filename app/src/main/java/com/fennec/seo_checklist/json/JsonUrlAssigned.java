package com.fennec.seo_checklist.json;

import android.content.Context;

import com.fennec.seo_checklist.controller.MainActivity;
import com.fennec.seo_checklist.entity.App;
import com.fennec.seo_checklist.entity.Assigned;
import com.fennec.seo_checklist.myInterface.IonHandler;
import com.fennec.seo_checklist.repository.AppRepository;
import com.fennec.seo_checklist.repository.AssignedRepository;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUrlAssigned implements IonHandler {

    public boolean result_succes = false;
    public boolean result_error = false;


    @Override
    public void onSucces(Object obj)
    {
        MainActivity.onSuccesAssigned();
    }

    @Override
    public void onFailed(Object obj)
    {

    }


    public JsonUrlAssigned(String link , final Context ctx)
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
                Assigned json_assigned = new Assigned();

                try
                {
                    // //INSERT INTO `apps`(`id`, `title`, `link1`, `link2`, `text1`, `text2`, `indication`, `version_app`)

                    JSONObject oneObject = jArray.getJSONObject(i);
                    json_assigned.id        = Integer.parseInt(oneObject.getString("id"));
                    json_assigned.id_app        = Integer.parseInt(oneObject.getString("id_app"));
                    json_assigned.id_client        = Integer.parseInt(oneObject.getString("id_client"));

                }
                catch (JSONException e)
                {
                    //Log.e("tag_json", ""+e);
                }

                AssignedRepository.list_assigned.add(json_assigned);

            }
        }
        catch (Exception e)
        {

        }
    }
}

