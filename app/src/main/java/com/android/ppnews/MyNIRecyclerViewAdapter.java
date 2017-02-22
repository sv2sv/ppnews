package com.android.ppnews;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.ppnews.NewsFragment.OnListFragmentInteractionListener;
import com.android.ppnews.dummy.DummyContent.DummyItem;
import com.android.ppnews.pojo.JHNew;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyNIRecyclerViewAdapter extends RecyclerView.Adapter<MyNIRecyclerViewAdapter.ViewHolder> {

    private static String TAG = "adapter";
    private final List<JHNew.ResultBean.DataBean> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyNIRecyclerViewAdapter(List<JHNew.ResultBean.DataBean> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
        if (items!=null){

            Log.i(TAG, "MyNIRecyclerViewAdapter: "+mValues.size());
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_newsitem, parent, false);
        Log.i(TAG, "onCreateViewHolder: "+"oncreateviewholder");
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.mItem = mValues.get(position);
        holder.mTitle.setText(holder.mItem.getTitle());
        holder.mTime.setText(holder.mItem.getDate());
        holder.mFrom.setText(holder.mItem.getCategory());
        Log.i(TAG, "onBindViewHolder: "+holder.mItem.toString());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.i(TAG, "getItemCount: "+mValues.size());
        return mValues.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mTitle;
        public final TextView mDesc;
        public final ImageView mImg;
        public final TextView mFrom;
        public final TextView mTime;
        public final ImageButton mLater;
        public final ImageButton mShare;
        public final ImageButton mMore;
        public final View mView;
        public JHNew.ResultBean.DataBean mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTitle = (TextView) mView.findViewById(R.id.title_news);
            mDesc = (TextView) mView.findViewById(R.id.desc_news);
            mImg = (ImageView) mView.findViewById(R.id.img_news);
            mTime = (TextView) mView.findViewById(R.id.time_news);
            mFrom = (TextView) mView.findViewById(R.id.from_news);
            mLater = (ImageButton) mView.findViewById(R.id.later_btn);
            mShare = (ImageButton) mView.findViewById(R.id.share_btn);
            mMore = (ImageButton) mView.findViewById(R.id.more_btn);
        }

    }
}
