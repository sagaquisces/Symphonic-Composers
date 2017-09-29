package com.epicodus.symphoniccomposers.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.TextView;

import com.epicodus.symphoniccomposers.Constants;
import com.epicodus.symphoniccomposers.R;
import com.epicodus.symphoniccomposers.adapters.FirebaseComposerListAdapter;
import com.epicodus.symphoniccomposers.adapters.FirebaseComposerViewHolder;
import com.epicodus.symphoniccomposers.models.SymphonyComposer;
import com.epicodus.symphoniccomposers.util.OnStartDragListener;
import com.epicodus.symphoniccomposers.util.SimpleItemTouchHelperCallback;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedComposerListActivity extends AppCompatActivity implements OnStartDragListener {

    private DatabaseReference mComposerReference;
    private FirebaseComposerListAdapter mFirebaseAdapter;
    private ItemTouchHelper mItemTouchHelper;

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    @Bind(R.id.countryTextView) TextView mCountryTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_composers);
        ButterKnife.bind(this);

        setUpFirebaseAdapter();

        mCountryTextView.setText("Your saved composers:");
    }

    private void setUpFirebaseAdapter() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        mComposerReference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_COMPOSERS)
                .child(uid);

        mFirebaseAdapter = new FirebaseComposerListAdapter(SymphonyComposer.class, R.layout.composer_list_item_drag, FirebaseComposerViewHolder.class, mComposerReference, this, this);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mFirebaseAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }


}
