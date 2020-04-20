package com.fennec.seo_checklist.json;

import android.content.Context;
import android.util.Log;

import com.fennec.seo_checklist.controller.HomeActivity;
import com.fennec.seo_checklist.controller.MainActivity;
import com.fennec.seo_checklist.entity.App;
import com.fennec.seo_checklist.entity.Client;
import com.fennec.seo_checklist.myInterface.IonHandler;
import com.fennec.seo_checklist.repository.AppRepository;
import com.fennec.seo_checklist.repository.ClientRepository;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUrlApp implements IonHandler {

    public boolean result_succes = false;
    public boolean result_error = false;


    @Override
    public void onSucces(Object obj)
    {
        MainActivity.onSuccesApp();
    }

    @Override
    public void onFailed(Object obj)
    {
        MainActivity.onFaild();
    }


    public JsonUrlApp(String link , final Context ctx)
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
                App json_app = new App();

                try
                {
                    // //INSERT INTO `apps`(`id`, `title`, `link1`, `link2`, `text1`, `text2`, `indication`, `version_app`)

                    JSONObject oneObject = jArray.getJSONObject(i);
                    json_app.id        = Integer.parseInt(oneObject.getString("id"));
                    json_app.title = oneObject.getString("title");
                    json_app.link1 = oneObject.getString("link1");
                    json_app.link2 = oneObject.getString("link2");
                    json_app.text1 = oneObject.getString("text1");
                    json_app.text2 = oneObject.getString("text2");
                    json_app.indication = oneObject.getString("indication");
                    json_app.version_app = oneObject.getString("version_app");

                }
                catch (JSONException e)
                {
                    //Log.e("tag_json", ""+e);
                }

                AppRepository.list_App.add(json_app);

            }
        }
        catch (Exception e)
        {

        }
    }
}
