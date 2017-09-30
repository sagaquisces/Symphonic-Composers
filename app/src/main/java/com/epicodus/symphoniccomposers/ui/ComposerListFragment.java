package com.epicodus.symphoniccomposers.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epicodus.symphoniccomposers.Constants;
import com.epicodus.symphoniccomposers.R;
import com.epicodus.symphoniccomposers.adapters.ComposerListAdapter;
import com.epicodus.symphoniccomposers.models.SymphonyComposer;
import com.epicodus.symphoniccomposers.services.WikiService;
import com.epicodus.symphoniccomposers.util.OnComposerSelectedListener;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ComposerListFragment extends Fragment {

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    private ComposerListAdapter mAdapter;
    private SharedPreferences mSharedPreferences;
    public ArrayList<SymphonyComposer> mSymphonyComposers = new ArrayList<>();
    private String mRecentCountry;
    private OnComposerSelectedListener mOnComposerSelectedListener;

    public ComposerListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mOnComposerSelectedListener = (OnComposerSelectedListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString() + e.getMessage());
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_composer_list, container, false);
        ButterKnife.bind(this, view);
        mRecentCountry = mSharedPreferences.getString(Constants.PREFERENCES_COUNTRY_KEY, null);

        if (mRecentCountry != null) {
            getSymphonyComposers(mRecentCountry);
        }

        return view;
    }

    private void getSymphonyComposers(final String country) {
        final WikiService wikiService = new WikiService();
        wikiService.findSymphonyComposers(country, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mSymphonyComposers = wikiService.processResults(country, response);

                getActivity().runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        mAdapter = new ComposerListAdapter(getActivity(), mSymphonyComposers, mOnComposerSelectedListener);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);

                    }
                });
            }

        });
    }

}
