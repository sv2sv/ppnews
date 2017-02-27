package com.android.ppnews.tabfragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.ppnews.PPFragment;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

import java.util.ArrayList;

/**
 * Created by wangyao on 24/2/17.
 */

public abstract class StatefullFragment<S extends Parcelable> extends PPFragment {
    private final S defaultstate;
    private final int fragmentLayoutResId;
    private final String stateExtraKey;
    private ArrayList<S> stateStack = new ArrayList<>();
    protected EventHandle<S> mEventHandle;
    private boolean isChangingState;
    private Bundle initialState;

    protected StatefullFragment(S s, String str, int i) {
        this.stateExtraKey = str;
        this.defaultstate = s;
        this.fragmentLayoutResId = i;

    }

    public interface EventHandle<S extends Parcelable> {
        void onStateChanged(StatefullFragment<S> sStatefullFragment, S s, S s2);
    }

    public S state() {
        return this.stateStack.isEmpty() ? null : (S) this.stateStack.get(this.stateStack.size() - 1);
    }

    public void setInitialState(S s) {
        Bundle bundle = new Bundle(getClass().getClassLoader());
        ArrayList newArrayList = Lists.newArrayList();
        newArrayList.add(s);
        bundle.putParcelableArrayList(this.stateExtraKey, newArrayList);
        setArguments(bundle);
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelableArrayList(this.stateExtraKey, this.stateStack);
    }

    public final void changeState(S s, boolean z) {
        if (state() == s) {
            Log.d("state unchanged", String.valueOf(new Object[0]));
        } else if (!this.isChangingState) {
            this.isChangingState = true;
            S state = state();
            if (this.stateStack.isEmpty()) {
                this.stateStack.add(s);
            } else {
                this.stateStack.set(this.stateStack.size() - 1, s);
            }
            Log.d("changeState: %s", String.valueOf(new Object[]{s}));
            onStateSet(s);
            updateViews(s, state);
            this.isChangingState = false;
            if (z && this.mEventHandle != null) {
                this.mEventHandle.onStateChanged(this, s, state);
            }
        }
    }

    

    @Override
    protected View doOnCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(this.fragmentLayoutResId, viewGroup, false);

        return inflate;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        boolean handleExtras;
        if (savedInstanceState != null) {
            handleExtras = handleExtras(savedInstanceState);
        } else {
            handleExtras = false;
        }
        if (!(handleExtras || getArguments() == null)) {
            handleExtras = handleExtras(getArguments());
        }
        if (!handleExtras) {
            changeState(this.defaultstate, false);
        }
    }

    private boolean handleExtras(Bundle savedInstanceState) {
        if (null == savedInstanceState) {
            throw new NullPointerException();
        }
        Log.d("Got extras: %s", String.valueOf(new Object[]{savedInstanceState}));
        if (!savedInstanceState.containsKey(this.stateExtraKey)) {
            return false;
        }
        ArrayList arrayList;
        this.initialState = savedInstanceState;
        boolean z = savedInstanceState.getBoolean("addToBackStack", false);
        Object obj = savedInstanceState.get(this.stateExtraKey);
        if (obj instanceof ArrayList) {
            arrayList = (ArrayList) obj;
        } else {
            ArrayList newArrayList = Lists.newArrayList();
            newArrayList.add((Parcelable) obj);
            arrayList = newArrayList;
        }
        if (z && mActivity.isActivityStarted() && (this.stateStack.size() > 0 || !Objects.equal(this.defaultstate, state()))) {
            pushState((S) (Parcelable) arrayList.get(0));
        } else {
            changeStateStack(arrayList);
        }
        this.initialState = null;
        return true;
    }

    protected abstract void updateViews(S s, S state);

    protected boolean isChangingState() {
        return this.isChangingState;
    }

    protected void onStateSet(S s) {
        tagRootViewWithAnalytics();
    }

    private void tagRootViewWithAnalytics() {
        if (!(Looper.getMainLooper().getThread() == Thread.currentThread())) {
            throw new IllegalStateException();
        }

    }

    @SuppressLint("LongLogTag")
    public final void pushState(S s, boolean z) {
        if(this.isChangingState){
            throw  new IllegalStateException("isChangingState");
        }

        if(state() == s || (s!=null && s.equals(state()))){
            Log.w("Trying to push the same state", String.valueOf(new Object[0]));
            return;
        }
        this.isChangingState = true;
        Parcelable state = state();
        if (z && this.stateStack.contains(s)) {
            this.stateStack.remove(s);
        }
        this.stateStack.add(s);
        Log.d("pushState: %s", String.valueOf(new Object[]{s}));
        onStateSet(s);
        updateViews(s, (S) state);
        this.isChangingState = false;
        if (this.mEventHandle != null) {
            this.mEventHandle.onStateChanged(this, s, (S) state);
        }
    }

    private boolean popStateIfPossible() {
        if(isChangingState){
            throw new IllegalStateException("ischangingState");
        }
        Parcelable state = state();
        while (this.stateStack.size() > 1) {
            this.stateStack.remove(this.stateStack.size() - 1);
            Parcelable state2 = state();
            if (!state2.equals(state)) {
                this.isChangingState = true;
                Log.d("popState: %s", String.valueOf(new Object[]{state2}));
                onStateSet((S) state2);
                updateViews((S)state2,  (S)state);
                this.isChangingState = false;
                if (this.mEventHandle != null) {
                    this.mEventHandle.onStateChanged(this, (S) state2, (S) state);
                }
                return true;
            }
        }
        return false;
    }

    public final void pushState(S s) {
        pushState(s, false);
    }

    public boolean handleOnBackPressed() {
        return popStateIfPossible();
    }

    private void changeStateStack(ArrayList<S> arrayList) {
        if(isChangingState){
            throw new IllegalStateException("ischangingState");
        }
        this.isChangingState = true;
        Parcelable state = state();
        this.stateStack = arrayList;
        Parcelable state2 = state();
        Log.d("changeState: %s", String.valueOf(new Object[]{state2}));
        onStateSet((S) state2);
        updateViews((S) state2, (S) state);
        this.isChangingState = false;
    }
}

