package com.fennec.seo_checklist.json;

import android.content.Context;
import android.util.Log;

import com.fennec.seo_checklist.entity.Progress;
import com.fennec.seo_checklist.myInterface.IonHandler;
import com.fennec.seo_checklist.repository.ProgressRepository;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonSetProgress implements IonHandler {


    @Override
    public void onSucces(Object obj)
    {

    }

    @Override
    public void onFailed(Object obj)
    {

    }


    public JsonSetProgress(String link , final Context ctx)
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
                            Log.e("TAG_PROGRESS", "onClick: SEND URL " + result);
                            ConditionResult( result );
                        }
                    }
                });

    }

    public void ConditionResult(String result)
    {
        if(result.equals("400"))
        {
            onSucces(result);
        }
        else
        {
            onFailed(result);
        }
    }

}

