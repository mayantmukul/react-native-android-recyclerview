package com.example.list;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RNDListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final ArrayList<View> mViews = new ArrayList<>();
    private int mLimit = 0;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mViews.get(viewType);
        return new RecyclerView.ViewHolder(view){};
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return mViews.size();
    }

    public void addViewAtIndex(View child, int index) throws IndexOutOfBoundsException {
        mViews.add(index, child);
    }

    public boolean isFilled() {
        return mViews.size() == mLimit;
    }

    public void setLimit(int limit) {
        mLimit = limit;
    }
}
