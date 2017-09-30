package com.epicodus.symphoniccomposers.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.symphoniccomposers.Constants;
import com.epicodus.symphoniccomposers.R;
import com.epicodus.symphoniccomposers.models.SymphonyComposer;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ComposerDetailFragment extends Fragment implements View.OnClickListener{
    @Bind(R.id.composerImageView) ImageView mImageLabel;
    @Bind(R.id.composerNameTextView) TextView mNameLabel;
    @Bind(R.id.birthDeathTextView) TextView mBirthDeathLabel;
    @Bind(R.id.contentTextView) TextView mContentLabel;
    @Bind(R.id.pageUrlTextView) TextView mPageUrlLabel;
    @Bind(R.id.saveComposerBtn) Button mSaveComposerButton;

    private SymphonyComposer mComposer;
    private ArrayList<SymphonyComposer> mComposers;
    private int mPosition;
    private String mSource;


    public ComposerDetailFragment() {
        // Required empty public constructor
    }

    public static ComposerDetailFragment newInstance(ArrayList<SymphonyComposer> symphonyComposers, Integer position, String source) {
        ComposerDetailFragment composerDetailFragment = new ComposerDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(Constants.EXTRA_KEY_COMPOSERS, Parcels.wrap(symphonyComposers));
        args.putInt(Constants.EXTRA_KEY_POSITION, position);
        args.putString(Constants.KEY_SOURCE, source);
        composerDetailFragment.setArguments(args);
        return composerDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mComposers = Parcels.unwrap(getArguments().getParcelable(Constants.EXTRA_KEY_COMPOSERS));
        mPosition = getArguments().getInt(Constants.EXTRA_KEY_POSITION);
        mSource = getArguments().getString(Constants.KEY_SOURCE);
        // setHasOptionsMenu(true);
        mComposer = mComposers.get(mPosition);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_composer_detail, container, false);
        ButterKnife.bind(this, view);

        if (mSource.equals(Constants.SOURCE_SAVED)) {
            mSaveComposerButton.setVisibility(View.GONE);
        } else {
            mSaveComposerButton.setOnClickListener(this);
        }

        //Picasso.with(view.getContext())load(mComposer.getImageUrl()).into(mImageLabel);

        mNameLabel.setText(mComposer.getName());
        mBirthDeathLabel.setText(mComposer.getBirthDeath());
        mContentLabel.setText(mComposer.getContent());
//        mPageUrlLabel.setText(mComposer.getPageUrl());

        mPageUrlLabel.setOnClickListener(this);
        mSaveComposerButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == mPageUrlLabel) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(mComposer.getPageUrl()));
            startActivity(webIntent);
        }
        if (v == mSaveComposerButton) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();

            final DatabaseReference composerRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_COMPOSERS)
                    .child(uid);

            composerRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    boolean duplicate = false;
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        Map<String, Object> model = (Map<String, Object>) child.getValue();
                        if(model.get("name").equals(String.valueOf(mComposer.getName()))) {
                            duplicate = true;
                            break;
                        }
                    }

                    if (duplicate) {
                        Toast.makeText(getContext(), "Already Saved", Toast.LENGTH_SHORT).show();
                    } else {
                        DatabaseReference pushRef = composerRef.push();
                        String pushId = pushRef.getKey();
                        mComposer.setPushId(pushId);
                        pushRef.setValue(mComposer);

                        Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }

    }
}
