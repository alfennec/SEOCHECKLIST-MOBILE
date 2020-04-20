package com.fennec.seo_checklist.json;

import android.content.Context;

import com.fennec.seo_checklist.controller.CheckActivity;
import com.fennec.seo_checklist.entity.App;
import com.fennec.seo_checklist.entity.Item;
import com.fennec.seo_checklist.myInterface.IonHandler;
import com.fennec.seo_checklist.repository.AppRepository;
import com.fennec.seo_checklist.repository.ItemRepository;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUrlItem implements IonHandler {

    public boolean result_succes = false;
    public boolean result_error = false;


    @Override
    public void onSucces(Object obj)
    {
        CheckActivity.onSuccesItem();
    }

    @Override
    public void onFailed(Object obj)
    {

    }


    public JsonUrlItem(String link , final Context ctx)
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
                Item json_item = new Item();

                try
                {
                    //INSERT INTO `items`(`id`, `id_cat`, `intituler`, `stats`, `link`)

                    JSONObject oneObject    = jArray.getJSONObject(i);
                    json_item.id            = Integer.parseInt(oneObject.getString("id"));
                    json_item.id_cat        = Integer.parseInt(oneObject.getString("id_cat"));
                    json_item.intituler     = oneObject.getString("intituler");
                    json_item.stats         = Integer.parseInt(oneObject.getString("stats"));
                    json_item.link          = oneObject.getString("link");

                }
                catch (JSONException e)
                {
                    //Log.e("tag_json", ""+e);
                }

                ItemRepository.list_item.add(json_item);

            }
        }
        catch (Exception e)
        {

        }
    }
}

