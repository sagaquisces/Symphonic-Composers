package com.epicodus.symphoniccomposers.adapters;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.symphoniccomposers.Constants;
import com.epicodus.symphoniccomposers.R;
import com.epicodus.symphoniccomposers.models.SymphonyComposer;
import com.epicodus.symphoniccomposers.ui.ComposerDetailActivity;
import com.epicodus.symphoniccomposers.util.ItemTouchHelperViewHolder;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;
import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Guest on 9/22/17.
 */

public class FirebaseComposerViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;
    public ImageView mComposerImageView;

    View mView;
    Context mContext;

    public FirebaseComposerViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
    }

    public void bindComposer(SymphonyComposer composer) {
        TextView nameTextView = (TextView) mView.findViewById(R.id.composerNameTextView);
        TextView birthDeathTextView = (TextView) mView.findViewById(R.id.birthDeathTextView);
        TextView contentTextView = (TextView) mView.findViewById(R.id.contentTextView);
        mComposerImageView = (ImageView) mView.findViewById(R.id.composerImageView);

        nameTextView.setText(composer.getName());
        birthDeathTextView.setText(composer.getBirthDeath());
        contentTextView.setText(composer.getContent());
    }

    @Override
    public void onItemSelected() {
        itemView.animate()
                .alpha(0.7f)
                .scaleX(0.9f)
                .scaleY(0.9f)
                .setDuration(500);
    }

    @Override
    public void onItemClear() {
        itemView.animate()
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f);
    }

}
