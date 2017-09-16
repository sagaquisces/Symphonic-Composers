package com.epicodus.symphoniccomposers.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.epicodus.symphoniccomposers.R;
import com.epicodus.symphoniccomposers.adapters.ComposerListAdapter;
import com.epicodus.symphoniccomposers.models.SymphonyComposer;
import com.epicodus.symphoniccomposers.services.WikiService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ComposersActivity extends AppCompatActivity {
    public static final String TAG = ComposersActivity.class.getSimpleName();

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    @Bind(R.id.countryTextView) TextView mCountryTextView;
    private ComposerListAdapter mAdapter;

    public ArrayList<SymphonyComposer> mSymphonyComposers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_composers);

        ButterKnife.bind(this);

//        mListView.setOnItemClickListener(this);

        Intent intent = getIntent();
        String country = intent.getStringExtra("country");
        String message = "";
        if (country.contains("All")){
            message = "Here are all symphonic composers in our list";
        } else {
            message = "Here are all the %s composers";
        }
        mCountryTextView.setText(String.format(message, country));

        getSymphonyComposers(country);
    }

//    @Override
//    public void onItemClick(AdapterView<?> adapterView, View v, int i, long l) {
//        Intent intent = new Intent(ComposersActivity.this, ComposerDetailActivity.class);
//        intent.putExtra("composer", ((TextView)v).getText().toString());
//        startActivity(intent);
//    }

    private void getSymphonyComposers(final String country) {
        final WikiService wikiService = new WikiService();
        wikiService.findSymphonyComposers(country, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mSymphonyComposers = wikiService.processResults(country, response);

                ComposersActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        mAdapter = new ComposerListAdapter(getApplicationContext(), mSymphonyComposers);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ComposersActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);

                    }
                });
            }

        });
    }
}
