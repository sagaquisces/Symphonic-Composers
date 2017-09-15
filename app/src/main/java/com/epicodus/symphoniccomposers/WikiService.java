package com.epicodus.symphoniccomposers;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Guest on 9/15/17.
 */

public class WikiService {

    public static void findSymphonyComposers(String country, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.WIKI_BASE_URL).newBuilder();

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
}
