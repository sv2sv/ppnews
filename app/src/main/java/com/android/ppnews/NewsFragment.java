package com.android.ppnews;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.ppnews.dummy.DummyContent;
import com.android.ppnews.dummy.DummyContent.DummyItem;
import com.android.ppnews.pojo.JHNew;

import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class NewsFragment extends BaseFragment {

    // TODO: Customize parameter argument names
    private static final String ARG_STRING  = "args";

    private String args ;

    private OnListFragmentInteractionListener mListener;

    public static NewsFragment mNewsFragment;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NewsFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static NewsFragment getInstance(String msg) {
        if(mNewsFragment==null){
            mNewsFragment = new NewsFragment();
        }else if(mNewsFragment.isAdded()){
            return null;
        }
        Bundle args = new Bundle();
        args.putString(ARG_STRING, msg);
        mNewsFragment.setArguments(args);
        return mNewsFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            args = getArguments().getString(ARG_STRING);
        }
    }


    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(new MyNIRecyclerViewAdapter(DummyContent.ITEMS, mListener));
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_newsitem_list;
    }




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(JHNew item);
    }
}
