package com.example.list;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.R;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<ConcreteViewHolder> {
    private final List<CustomItemView> mViews = new ArrayList<>();

    @NonNull
    @Override
    public ConcreteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CustomItemView itemView = mViews.get(viewType);
        return new ConcreteViewHolder(itemView);
        //return new ConcreteViewHolder(new RecyclableWrapperViewGroup(parent.getContext()));
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(@NonNull ConcreteViewHolder holder, int position) {
        //CustomAdapter.RecyclableWrapperViewGroup vg = (CustomAdapter.RecyclableWrapperViewGroup) holder.itemView;
//        View row = getChildAtIndex(position);
//        if (row != null && row.getParent() != vg) {
//            if (row.getParent() != null) {
//                ((ViewGroup) row.getParent()).removeView(row);
//            }
//            vg.addView(row, 0);
//        }
    }

    @Override
    public int getItemCount() {
        return mViews.size();
    }

    public void addViewToAdapter(CustomItemView view) {
        mViews.add(view);
    }

    public CustomItemView getChildAtIndex(int index) {
        return mViews.get(index);
    }

    static class RecyclableWrapperViewGroup extends ViewGroup {

        private int mLastMeasuredWidth;
        private int mLastMeasuredHeight;
        private Boolean isScrollingDown;

        public RecyclableWrapperViewGroup(Context context) {
            super(context);
            mLastMeasuredHeight = 10;
            mLastMeasuredWidth = 10;
            setBackgroundColor(getResources().getColor(R.color.catalyst_redbox_background));
            //setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        }

        private OnLayoutChangeListener mChildLayoutChangeListener = new OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                int oldHeight = (oldBottom - oldTop);
                int newHeight = (bottom - top);

                if (oldHeight != newHeight) {
                    if (getParent() != null) {
                        requestLayout();
                        getParent().requestLayout();
                    }
                }
            }
        };

        @Override
        protected void onLayout(boolean changed, int l, int t, int r, int b) {
            // This view will only have one child that is managed by the `NativeViewHierarchyManager` and
            // its position and dimensions are set separately. We don't need to handle its layouting here
        }

        @Override
        public void onViewAdded(View child) {
            super.onViewAdded(child);
            child.addOnLayoutChangeListener(mChildLayoutChangeListener);
        }

        @Override
        public void onViewRemoved(View child) {
            super.onViewRemoved(child);
            child.removeOnLayoutChangeListener(mChildLayoutChangeListener);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            // We override measure spec and use dimensions of the children. Children is a view added
            // from the adapter and always have a correct dimensions specified as they are calculated
            // and set with NativeViewHierarchyManager.
            // In case there is no view attached, we use the last measured dimensions.

            //if (getChildCount() > 0) {
             //   View child = getChildAt(0);
                mLastMeasuredWidth = 800;
                mLastMeasuredHeight = 400;
                setMeasuredDimension(mLastMeasuredWidth, mLastMeasuredHeight);
           // } else {
             //   setMeasuredDimension(mLastMeasuredWidth, mLastMeasuredHeight);
            //}
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            // Similarly to ReactViewGroup, we return true.
            // In this case it is necessary in order to force the RecyclerView to intercept the touch events,
            // in this way we can exactly know when the drag starts because "onInterceptTouchEvent"
            // of the RecyclerView will return true.
            return true;
        }
    }
}
