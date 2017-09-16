package com.epicodus.symphoniccomposers.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.symphoniccomposers.R;
import com.epicodus.symphoniccomposers.models.SymphonyComposer;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Guest on 9/15/17.
 */

public class ComposerListAdapter extends RecyclerView.Adapter<ComposerListAdapter.ComposerViewHolder> {
    private ArrayList<SymphonyComposer> mSymphonyComposers = new ArrayList<>();
    private Context mContext;

    public ComposerListAdapter(Context context, ArrayList<SymphonyComposer> symphonyComposers ) {
        mContext = context;
        mSymphonyComposers = symphonyComposers;
    }

    @Override
    public ComposerListAdapter.ComposerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.composer_list_item, parent, false);
        ComposerViewHolder viewHolder = new ComposerViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ComposerListAdapter.ComposerViewHolder holder, int position) {
        holder.bindComposer(mSymphonyComposers.get(position));
    }

    @Override
    public int getItemCount() {
        return mSymphonyComposers.size();
    }

    public class ComposerViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.composerImageView) ImageView mSymphonicComposerImageView;
        @Bind(R.id.composerNameTextView) TextView mNameTextView;
        @Bind(R.id.birthDeathTextView) TextView mBirthDeathTextView;
        @Bind(R.id.contentTextView) TextView mContentTextView;

        private Context mContext;

        public ComposerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindComposer(SymphonyComposer symphonyComposer) {
            mNameTextView.setText(symphonyComposer.getName());
            mBirthDeathTextView.setText(symphonyComposer.getBirthDeath());
            mContentTextView.setText(symphonyComposer.getContent());
        }
    }
}
