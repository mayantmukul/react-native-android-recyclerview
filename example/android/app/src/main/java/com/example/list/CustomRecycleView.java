package com.example.list;

import android.content.Context;
import android.view.ContextThemeWrapper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.R;

import java.util.ArrayList;
import java.util.List;

public class CustomRecycleView extends RecyclerView {

    public CustomRecycleView(@NonNull Context context) {
        super(new ContextThemeWrapper(context, R.style.ScrollbarRecyclerView));
        setHasFixedSize(false);
        ((DefaultItemAnimator)getItemAnimator()).setSupportsChangeAnimations(false);
        setLayoutManager(new LinearLayoutManager(context));
        setAdapter(new ReactListAdapter(this));
    }

    private static class ConcreteViewHolder extends ViewHolder {
        public ConcreteViewHolder(View itemView) {
            super(itemView);
        }
    }

    void addViewToAdapter(RNRecycleviewItemview child, int index) {
        ((ReactListAdapter) getAdapter()).addView(child, index);
    }

    View getChildAtFromAdapter(int index) {
        return ((ReactListAdapter) getAdapter()).getView(index);
    }

    int getChildCountFromAdapter() {
        return ((ReactListAdapter) getAdapter()).getViewCount();
    }

    void setItemCount(int itemCount) {
        ((ReactListAdapter) getAdapter()).setItemCount(itemCount);
    }

    int getItemCount() {

        return getAdapter().getItemCount();
    }

    static class RecyclableWrapperViewGroup extends ViewGroup {

        private ReactListAdapter mAdapter;
        private int mLastMeasuredWidth;
        private int mLastMeasuredHeight;
        private Boolean isScrollingDown;

        public RecyclableWrapperViewGroup(Context context, ReactListAdapter adapter) {
            super(context);
            mAdapter = adapter;
            mLastMeasuredHeight = 10;
            mLastMeasuredWidth = 10;
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

            if (getChildCount() > 0) {
                View child = getChildAt(0);
                mLastMeasuredWidth = child.getMeasuredWidth();
                mLastMeasuredHeight = child.getMeasuredHeight();
                setMeasuredDimension(mLastMeasuredWidth, mLastMeasuredHeight);
            } else {
                setMeasuredDimension(mLastMeasuredWidth, mLastMeasuredHeight);
            }
        }

        public ReactListAdapter getAdapter() {
            return mAdapter;
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

    static class ReactListAdapter extends Adapter<ConcreteViewHolder> {

        private final List<RNRecycleviewItemview> mViews = new ArrayList<>();
        private final CustomRecycleView mScrollView;
        private boolean mIsScrollingDown = true;

        private OnScrollListener scrollListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    mIsScrollingDown = false;
                } else {
                    mIsScrollingDown = true;
                }
            }
        };
        public ReactListAdapter(CustomRecycleView scrollView) {
            mScrollView = scrollView;
        }

        public void addView(RNRecycleviewItemview child, int index) {
            if(mIsScrollingDown) {
                mViews.add(index, child);
                final int itemIndex = child.getItemIndex();
                notifyItemChanged(itemIndex);
            }
        }

        public void removeViewAt(int index) {
//            RNRecycleviewItemview child = mViews.get(index);
//            if (child != null) {
//                mViews.remove(index);
//            }
        }

        public int getViewCount() {
            return mViews.size();
        }

        @Override
        public ConcreteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ConcreteViewHolder(new RecyclableWrapperViewGroup(parent.getContext(), this));
        }

        @Override
        public void onBindViewHolder(ConcreteViewHolder holder, int position) {
            RecyclableWrapperViewGroup vg = (RecyclableWrapperViewGroup) holder.itemView;
            View row = getViewByItemIndex(position);
            if (row != null && row.getParent() != vg) {
                if (row.getParent() != null) {
                    ((ViewGroup) row.getParent()).removeView(row);
                }
                vg.addView(row, 0);
            }
        }

        @Override
        public void onViewRecycled(ConcreteViewHolder holder) {
            super.onViewRecycled(holder);
            //((RecyclableWrapperViewGroup) holder.itemView).removeAllViews();
        }

        @Override
        public int getItemCount() {
            return mViews.size();
        }

        public void setItemCount(int itemCount) {
            //this.mItemCount = itemCount;
        }

        public View getView(int index) {
            return mViews.get(index);
        }

        public RNRecycleviewItemview getViewByItemIndex(int position) {
            if(position < mViews.size()) {
                return mViews.get(position);
            }
//            for (int i = 0; i < mViews.size(); i++) {
//                if (mViews.get(i).getItemIndex() == position) {
//                    return mViews.get(i);
//                }
//            }
            return null;
        }
    }
}
