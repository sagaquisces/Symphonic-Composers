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

public class ComposersActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
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
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View v, int i, long l) {
        Intent newintent = new Intent(ComposersActivity.this, ComposerDetailActivity.class);
        startActivity(newintent);
    }
}
