package com.android.ppnews.view;

import android.os.Parcelable;
import android.view.View;

/**
 * Created by wangyao on 24/2/17.
 */

public interface VideoPlayerView<T extends VideoPlayerView.VideoSource> {

    public interface VideoSource extends Parcelable {
        String getId();
    }

    long getCurrentPosition();

    void pause();

    void play(VideoPlayer videoPlayer, T t);

    void release();

    void seekTo(long j);

    void stop(VideoPlayer videoPlayer);

    View view();
}
