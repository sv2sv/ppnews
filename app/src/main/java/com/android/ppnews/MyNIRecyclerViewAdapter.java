package com.android.ppnews;

import android.animation.Animator;
import android.os.Build;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.ppnews.net.JHNewsType;
import com.android.ppnews.pojo.JHNew;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;
import java.util.Random;


public class MyNIRecyclerViewAdapter extends RecyclerView.Adapter<MyNIRecyclerViewAdapter.ViewHolder> {

    private static String TAG = "adapter";
    private final List<JHNew.ResultBean.DataBean> mValues;
    private final onItemClickListener mListener;
    private static final  int ERROR = -1;
    public interface  onItemClickListener{
        void onClick(JHNew.ResultBean.DataBean item);
    }
    public MyNIRecyclerViewAdapter(List<JHNew.ResultBean.DataBean> items, onItemClickListener listener) {
        mValues = items;
        mListener = listener;
        if (items!=null){

            Log.i(TAG, "MyNIRecyclerViewAdapter: "+mValues.size());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(mValues == null || mValues.size() == 0){
            return  ERROR;
        }
        return 1;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if(viewType == ERROR){
                    view = LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.error_unline, parent, false);
            return new ErrorViewHolder(view);
                }
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_newsitem, parent, false);
        Log.i(TAG, "onCreateViewHolder: "+"oncreateviewholder");
        return new MyViewHolder(view);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if(holder instanceof  MyViewHolder) {
            final MyViewHolder mholder = (MyViewHolder) holder;
            mholder.mItem = mValues.get(position);
            mholder.mTitle.setText(mholder.mItem.getTitle());
            mholder.mTime.setText(mholder.mItem.getDate());
            mholder.mFrom.setText(mholder.mItem.getCategory());
            mholder.mfromImg.setVisibility(View.INVISIBLE);
            mholder.mDesc.setVisibility(View.INVISIBLE);
            String imgUrl = getRandomUrl(mholder.mItem);
            if (imgUrl == null || imgUrl.equals("")) {
                mholder.mImg.setVisibility(View.GONE);
            } else {
                Glide.with(PPDepend.appContext()).load(imgUrl).asBitmap()
                        .centerCrop()
                        .animate(android.R.anim.fade_in)
                        .placeholder(R.color.grey_300)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .thumbnail(0.1f)
                        .into(mholder.mImg);
            }
            Log.i(TAG, "onBindViewHolder: " + mholder.mItem.toString());

            mholder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != mListener) {
                        // Notify the active callbacks interface (the activity, if the
                        // fragment is attached to one) that an item has been selected.
                        mListener.onClick(mholder.mItem);
                    }
                }
            });
            if((!"up".equals(mholder.mItem.getUniquekey()))&&( !"down".equals(mholder.mItem.getUniquekey()))) {
                mholder.mView.setScaleX(0.8f);
                mholder.mView.setScaleY(0.9f);
                mholder.mView.setTranslationX(-2.0f);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mholder.mView.setTranslationZ(0.3f);
                }
                ViewPropertyAnimator anim = mholder.mView.animate().scaleX(1.0f).scaleY(1.0f).alpha(1.0f).translationXBy(1.0f).setDuration(700).setInterpolator(new AccelerateDecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                       mValues.get(position).setUniquekey("up");
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    anim.translationZ(1.0f);
                }
                anim.start();
            }else if(mholder.mItem.getUniquekey().equals("up")){

                mholder.mView.setScaleX(1.1f);
                mholder.mView.setScaleY(1.1f);
                mholder.mView.setTranslationY(0.9f);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mholder.mView.setTranslationZ(2.0f);
                }
                ViewPropertyAnimator anim = mholder.mView.animate().scaleX(1.0f).scaleY(1.0f).alpha(1.0f).translationY(1.0f).setDuration(700).setInterpolator(new AccelerateDecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mValues.get(position).setUniquekey("down");
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    anim.translationZ(1.0f);
                }
                anim.start();
            }
        }

    }

    @Override
    public int getItemCount() {

        if(mValues == null || mValues.size() == 0){
            return 1;
        }
        Log.i(TAG, "getItemCount: "+mValues.size());
        return mValues.size();
    }



    private String getRandomUrl(JHNew.ResultBean.DataBean data){
        int i = PPDepend.Random().nextInt(3);
        String a= "";
        switch (i){
            case 0:
                a = data.getThumbnail_pic_s();
                break;
            case 1:
                a = data.getThumbnail_pic_s02();
                break;
            case 2:
                a = data.getThumbnail_pic_s03();
                break;
        }
        if(a == null || a.equals("")){
            a = data.getThumbnail_pic_s();
        }
        return a;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public abstract  class  ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class MyViewHolder extends ViewHolder {
        public final TextView mTitle;
        public final TextView mDesc;
        public final ImageView mImg;
        public final TextView mFrom;
        public final TextView mTime;
        public final ImageButton mLater;
        public final ImageButton mShare;
        public final ImageButton mMore;
        public final View mView;
        public final ImageView mfromImg;
        public JHNew.ResultBean.DataBean mItem;

        public MyViewHolder(View view) {
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
            mfromImg = (ImageView) mView.findViewById(R.id.fi_news);
        }

    }
    public class ErrorViewHolder extends ViewHolder{
        private View view ;

        public ErrorViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
        }
    }
}
