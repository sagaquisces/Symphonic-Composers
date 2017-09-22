package com.epicodus.symphoniccomposers.services;

import android.util.Log;

import com.epicodus.symphoniccomposers.Constants;
import com.epicodus.symphoniccomposers.models.SymphonyComposer;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Guest on 9/15/17.
 */

public class WikiService {

    public static void findSymphonyComposers(String country, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.WIKI_BASE_API_URL).newBuilder();

        urlBuilder.addQueryParameter("action", "query");
        urlBuilder.addQueryParameter("titles", "List_of_symphony_composers");
        urlBuilder.addQueryParameter("prop", "revisions");
        urlBuilder.addQueryParameter("rvprop", "content");
        urlBuilder.addQueryParameter("format", "json");
        String url = urlBuilder.build().toString();

        Request request= new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<SymphonyComposer> processResults(String country, Response response) {
        ArrayList<SymphonyComposer> symphonyComposers = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject wikiJSON = new JSONObject(jsonData);
                String wikitext = wikiJSON.getJSONObject("query")
                        .getJSONObject("pages")
                        .getJSONObject("9855199")
                        .getJSONArray("revisions")
                        .getJSONObject(0)
                        .getString("*");
                List<String> parts = new LinkedList<>(Arrays.asList(wikitext.split("\\r?\\n")));
                for (int i=0; i < parts.size(); i++) {
                    String part = parts.get(i);
                    if (part.indexOf("*[[") == 0 && part.contains("(") && part.contains("),") && ( part.contains(country) || country.contains("All"))) {
                        String name = part.substring(part.indexOf("[")+2, part.indexOf("]"));
                        String birthDeath = part.substring(part.indexOf("(")+1, part.indexOf(")"));
                        String content = part.substring(part.indexOf("), ")+3, part.length());
                        String pageUrl = String.format("%s%s", Constants.WIKI_BASE_PAGE_URL, name);
                        Log.d("HI", part);
                        SymphonyComposer symphonyComposer = new SymphonyComposer(name, birthDeath, content, pageUrl);
                        symphonyComposers.add(symphonyComposer);
                    }


                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return symphonyComposers;
    }
}
