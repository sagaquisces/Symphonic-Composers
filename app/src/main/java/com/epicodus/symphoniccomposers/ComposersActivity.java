package com.epicodus.symphoniccomposers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ComposersActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    public static final String TAG = ComposersActivity.class.getSimpleName();

    @Bind(R.id.listView) ListView mListView;
    @Bind(R.id.countryTextView) TextView mCountryTextView;

    private String[] composers = new String[] {"Ludwig van Beethoven", "Johannes Brahms", "Felix Mendelssohn", "Robert Schumann", "Richard Strauss", "Paul Hindemith", "Felix Draeseke", "Louis Spohr", "Max Bruch", "Robert Volkmann", "Karl Amadeus Hartmann", "Wilhelm Furtwängler"};

    private String[] symphonies = new String[] {"9", "4", "4", "4", "1", "4", "4", "8", "2", "4", "5", "2"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_composers);

        ButterKnife.bind(this);

        MyComposersArrayAdapter adapter = new MyComposersArrayAdapter(this, android.R.layout.simple_list_item_1, composers, symphonies);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(this);

        Intent intent = getIntent();
        String country = intent.getStringExtra("country");
        mCountryTextView.setText("Here are all the composers from: " + country);

        getSymphonyComposers(country);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View v, int i, long l) {
        Intent intent = new Intent(ComposersActivity.this, ComposerDetailActivity.class);
        intent.putExtra("composer", ((TextView)v).getText().toString());
        startActivity(intent);
    }

    private void getSymphonyComposers(String country) {
        final WikiService wikiService = new WikiService();
        wikiService.findSymphonyComposers(country, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String jsonData = response.body().string();
                    Log.v(TAG, jsonData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
    }
}
