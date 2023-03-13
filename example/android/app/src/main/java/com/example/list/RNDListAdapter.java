package com.example.list;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RNDListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RNDListItemViewContainer view = new RNDListItemViewContainer(parent.getContext());
        return new RecyclerView.ViewHolder(view) {};
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RNDListItemViewContainer container = (RNDListItemViewContainer) holder.itemView;
        container.setBackgroundFor(position);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    static class RNDListItemViewContainer extends View {
        private final int BLUE = getResources().getColor(android.R.color.holo_blue_dark);
        private final int ORANGE = getResources().getColor(android.R.color.holo_orange_dark);
        private final int GRAY = getResources().getColor(android.R.color.darker_gray);

        public RNDListItemViewContainer(Context context) {
            super(context);
            setBackgroundColor(GRAY);
            setMinimumHeight(400);
            setMinimumWidth(400);
        }

        public void setBackgroundFor(int position) {
            if (position % 2 == 0) {
                setBackgroundColor(BLUE);
            } else {
                setBackgroundColor(ORANGE);
            }
        }
    }
}
