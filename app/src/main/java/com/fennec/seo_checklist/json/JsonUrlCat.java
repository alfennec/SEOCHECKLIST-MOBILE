package com.fennec.seo_checklist.json;

import android.content.Context;

import com.fennec.seo_checklist.controller.CheckActivity;
import com.fennec.seo_checklist.entity.App;
import com.fennec.seo_checklist.entity.Cat;
import com.fennec.seo_checklist.myInterface.IonHandler;
import com.fennec.seo_checklist.repository.AppRepository;
import com.fennec.seo_checklist.repository.CatRepository;
import com.fennec.seo_checklist.repository.ItemRepository;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUrlCat implements IonHandler {

    public boolean result_succes = false;
    public boolean result_error = false;


    @Override
    public void onSucces(Object obj)
    {
        CheckActivity.onSuccesFam();
    }

    @Override
    public void onFailed(Object obj)
    {

    }


    public JsonUrlCat(String link , final Context ctx)
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
                Cat json_cat = new Cat();

                try
                {
                    //INSERT INTO `Cat`(`id`, `id_app`, `intituler`, `nbr_item`)

                    JSONObject oneObject    = jArray.getJSONObject(i);
                    json_cat.id             = Integer.parseInt(oneObject.getString("id"));
                    json_cat.id_app         = Integer.parseInt(oneObject.getString("id_app"));
                    json_cat.intituler      = oneObject.getString("intituler");
                    json_cat.nbr_item       = ItemRepository.getByIdCat(json_cat.id).size();

                    json_cat.item_list = ItemRepository.getByIdCat(json_cat.id);
                }
                catch (JSONException e)
                {
                    //Log.e("tag_json", ""+e);
                }

                CatRepository.list_cat.add(json_cat);

            }
        }
        catch (Exception e)
        {

        }
    }
}
