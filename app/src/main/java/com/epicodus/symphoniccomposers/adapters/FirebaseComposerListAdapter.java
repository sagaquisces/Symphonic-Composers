package com.epicodus.symphoniccomposers.adapters;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;

import com.epicodus.symphoniccomposers.Constants;
import com.epicodus.symphoniccomposers.models.SymphonyComposer;
import com.epicodus.symphoniccomposers.ui.ComposerDetailActivity;
import com.epicodus.symphoniccomposers.util.ItemTouchHelperAdapter;
import com.epicodus.symphoniccomposers.util.OnStartDragListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.apache.tools.ant.util.XMLFragment;
import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Guest on 9/29/17.
 */

public class FirebaseComposerListAdapter extends FirebaseRecyclerAdapter<SymphonyComposer, FirebaseComposerViewHolder> implements ItemTouchHelperAdapter {
    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;
    private ChildEventListener mChildEventListener;
    private ArrayList<SymphonyComposer> mComposers = new ArrayList<>();

    public FirebaseComposerListAdapter(Class<SymphonyComposer> modelClass, int modelLayout, Class<FirebaseComposerViewHolder> viewHolderClass, Query ref, OnStartDragListener onStartDragListener, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;

        mChildEventListener = mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mComposers.add(dataSnapshot.getValue(SymphonyComposer.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mComposers, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return false;
    }

    @Override
    public void onItemDismiss(int position) {
        mComposers.remove(position);
        getRef(position).removeValue();
    }

    @Override
    protected void populateViewHolder(final FirebaseComposerViewHolder viewHolder, SymphonyComposer model, int position) {
        viewHolder.bindComposer(model);
        viewHolder.mComposerImageView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mOnStartDragListener.onStartDrag(viewHolder);
                }
                return false;
            }


        });

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ComposerDetailActivity.class);
                intent.putExtra("position", viewHolder.getAdapterPosition());
                intent.putExtra("composers", Parcels.wrap(mComposers));

                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public void cleanup() {
        super.cleanup();
        setIndexInFirebase();
        mRef.removeEventListener(mChildEventListener);
    }

    private void setIndexInFirebase() {
        for (SymphonyComposer composer: mComposers) {
            int index = mComposers.indexOf(composer);
            DatabaseReference ref = getRef(index);
            composer.setIndex(Integer.toString(index));
            ref.setValue(composer);
        }
    }


}
