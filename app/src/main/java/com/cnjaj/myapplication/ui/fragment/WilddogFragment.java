package com.cnjaj.myapplication.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wilddog.client.*;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/17.
 */
public class WilddogFragment extends Fragment implements ChildEventListener {
    private static final String CHILD = "child";
    private String child;
    private WilddogAdapter wilddogAdapter;

    public static void newInstance(String child) {
        WilddogFragment fragment = new WilddogFragment();
        Bundle args = new Bundle();
        args.putString(CHILD, child);
        fragment.setArguments(args);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        child = args.getString(CHILD);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView view = new RecyclerView(getActivity());
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        wilddogAdapter = new WilddogAdapter();
        view.setAdapter(wilddogAdapter);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SyncReference ref = WilddogSync.getInstance().getReferenceFromUrl(child);
        ref.addChildEventListener(this);
    }

    @Override
    public void onChildAdded(DataSnapshot snapshot, String previousChildName) {
        snapshot.getChildren().iterator().
    }

    @Override
    public void onChildChanged(DataSnapshot snapshot, String previousChildName) {
    }

    @Override
    public void onChildRemoved(DataSnapshot snapshot) {

    }

    @Override
    public void onChildMoved(DataSnapshot snapshot, String previousChildName) {

    }

    @Override
    public void onCancelled(SyncError error) {

    }

    private class WilddogAdapter extends RecyclerView.Adapter<WilddogHolder> {
        private ArrayList<DataSnapshot> snapshots = new ArrayList<>();

        private WilddogAdapter() {

        }

        @Override
        public WilddogHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(WilddogHolder holder, int position) {

        }

        public void add(DataSnapshot snapshot) {
            snapshots.add(snapshot);
            notifyDataSetChanged();
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }

    private class WilddogHolder extends RecyclerView.ViewHolder {
        public WilddogHolder(View itemView) {
            super(itemView);
        }
    }
}
