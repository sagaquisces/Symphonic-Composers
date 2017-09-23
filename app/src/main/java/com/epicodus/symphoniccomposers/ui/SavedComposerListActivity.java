package com.epicodus.symphoniccomposers.ui;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.epicodus.symphoniccomposers.Constants;
import com.epicodus.symphoniccomposers.R;
import com.epicodus.symphoniccomposers.adapters.FirebaseComposerViewHolder;
import com.epicodus.symphoniccomposers.models.SymphonyComposer;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedComposerListActivity extends AppCompatActivity {

    private DatabaseReference mComposerReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    @Bind(R.id.countryTextView) TextView mCountryTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_composers);
        ButterKnife.bind(this);

        mComposerReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_COMPOSERS);
        setUpFirebaseAdapter();

        mCountryTextView.setText("Your saved composers:");
    }

    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<SymphonyComposer, FirebaseComposerViewHolder>(SymphonyComposer.class, R.layout.composer_list_item, FirebaseComposerViewHolder.class, mComposerReference) {

            @Override
            protected void populateViewHolder(FirebaseComposerViewHolder viewHolder, SymphonyComposer model, int position) {
                viewHolder.bindComposer(model);
            }


        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }


}
