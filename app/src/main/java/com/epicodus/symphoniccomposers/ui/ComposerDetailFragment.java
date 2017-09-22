package com.epicodus.symphoniccomposers.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.symphoniccomposers.R;
import com.epicodus.symphoniccomposers.models.SymphonyComposer;

import org.parceler.Parcels;
import org.w3c.dom.Text;

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

    private SymphonyComposer mComposer;


    public ComposerDetailFragment() {
        // Required empty public constructor
    }

    public static ComposerDetailFragment newInstance(SymphonyComposer symphonyComposer) {
        ComposerDetailFragment composerDetailFragment = new ComposerDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("symphonyComposer", Parcels.wrap(symphonyComposer));
        composerDetailFragment.setArguments(args);
        return composerDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mComposer = Parcels.unwrap(getArguments().getParcelable("symphonyComposer"));
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_composer_detail, container, false);
        ButterKnife.bind(this, view);

        //Picasso.with(view.getContext())load(mComposer.getImageUrl()).into(mImageLabel);

        mNameLabel.setText(mComposer.getName());
        mBirthDeathLabel.setText(mComposer.getBirthDeath());
        mContentLabel.setText(mComposer.getContent());
        mPageUrlLabel.setText(mComposer.getPageUrl());

        mPageUrlLabel.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == mPageUrlLabel) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(mComposer.getPageUrl()));
            startActivity(webIntent);
        }

    }
}
