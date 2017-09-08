package com.epicodus.symphoniccomposers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ComposersActivity extends AppCompatActivity {
    @Bind(R.id.listView) ListView mListView;
    @Bind(R.id.countryTextView) TextView mCountryTextView;

    private String[] composers = new String[] {"Ludwig van Beethoven", "Johannes Brahms", "Felix Mendelssohn", "Robert Schumann", "Richard Strauss", "Paul Hindemith", "Felix Draeseke", "Louis Spohr", "Max Bruch", "Robert Volkmann", "Karl Amadeus Hartmann", "Wilhelm Furtwängler"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_composers);

        ButterKnife.bind(this);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, composers);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String composer = ((TextView) view).getText().toString();
                Toast.makeText(ComposersActivity.this, composer, Toast.LENGTH_LONG).show();
            }
        });

        Intent intent = getIntent();
        String country = intent.getStringExtra("country");
        mCountryTextView.setText("Here are all the composers from: " + country);
    }
}
