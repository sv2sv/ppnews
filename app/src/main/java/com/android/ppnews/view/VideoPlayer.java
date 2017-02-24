package com.android.ppnews.view;

/**
 * Created by wangyao on 24/2/17.
 */

public class VideoPlayer {/*
    private static final Queue EXECUTOR = new Queue("VIDEO", Executors.newSingleThreadExecutor());
    private static final LruCache<UrlVideoSource, Long> PLAYBACK_POSITION_CACHE = new LruCache(10);
    private static DefaultExtractorsFactory extractorsFactory;
    private Context appContext;
    private VideoView2 attachedVideoView;
    private AudioManager audioManager;
    private Factory dataSourceFactory;
    private SimpleExoPlayer exoPlayer;
    private final Handler mainHandler = new Handler();
    private MediaSource playingMediaSource;
    private UrlVideoSource playingVideoSource;

    public static final class Constants {
        static final long DEFAULT_BUFFER_AFTER_REBUFFER_MS = TimeUnit.SECONDS.toMillis(5);
        static final long DEFAULT_BUFFER_FOR_PLAYBACK_MS = TimeUnit.SECONDS.toMillis(1);
        static final long DEFAULT_MAX_BUFFER_MS = TimeUnit.SECONDS.toMillis(20);
        static final long DEFAULT_MIN_BUFFER_MS = TimeUnit.SECONDS.toMillis(10);
    }

    public VideoPlayer(Context context) {
        this.appContext = context.getApplicationContext();
    }

    private void initializeIfNecessary() {
        AsyncUtil.checkNotMainThread();
        if (extractorsFactory == null) {
            extractorsFactory = new DefaultExtractorsFactory();
        }
        if (this.dataSourceFactory == null) {
            this.dataSourceFactory = new CacheDataSourceFactory(createCache(), new DefaultDataSourceFactory(this.appContext, Util.getUserAgent(this.appContext, "Newsstand")), 0);
        }
    }

    private static Executor backgroundExecutor() {
        return EXECUTOR;
    }

    private void createExoplayerIfNeeded() {
        if (this.exoPlayer == null) {
            this.exoPlayer = ExoPlayerFactory.newSimpleInstance(this.appContext, new DefaultTrackSelector(), createLoadControl());
        }
    }

    private Cache createCache() {
        AsyncUtil.checkNotMainThread();
        return new SimpleCache(new File(this.appContext.getCacheDir(), "_exoplayer_cache"), new LeastRecentlyUsedCacheEvictor(26214400));
    }

    private LoadControl createLoadControl() {
        return new DefaultLoadControl(new DefaultAllocator(false, 65536), (int) Constants.DEFAULT_MIN_BUFFER_MS, (int) Constants.DEFAULT_MAX_BUFFER_MS, (long) ((int) Constants.DEFAULT_BUFFER_FOR_PLAYBACK_MS), (long) ((int) Constants.DEFAULT_BUFFER_AFTER_REBUFFER_MS));
    }

    private MediaSource getMediaSource(UrlVideoSource urlVideoSource) {
        MediaSource buildMediaSource = buildMediaSource(Uri.parse(urlVideoSource.getUrl()));
        if (urlVideoSource.looping) {
            return new LoopingMediaSource(buildMediaSource);
        }
        return buildMediaSource;
    }

    public void attach(VideoView2 videoView2) {
        AsyncUtil.checkMainThread();
        if (this.attachedVideoView != null) {
            this.attachedVideoView.stop(this);
        }
        this.attachedVideoView = videoView2;
        createExoplayerIfNeeded();
        this.exoPlayer.setVideoTextureView(videoView2);
    }

    public void playAsync(final UrlVideoSource urlVideoSource) {
        AsyncUtil.checkMainThread();
        if (this.attachedVideoView == null) {
            throw new IllegalStateException("Cannot play video without an attached VideoView");
        } else if (this.exoPlayer == null || this.playingVideoSource == null || !this.playingVideoSource.equals(urlVideoSource)) {
            this.playingVideoSource = urlVideoSource;
            createExoplayerIfNeeded();
            backgroundExecutor().execute(new Runnable() {
                public void run() {
                    VideoPlayer.this.playInternal(urlVideoSource);
                }
            });
        } else {
            resume();
        }
    }

    private void playInternal(UrlVideoSource urlVideoSource) {
        AsyncUtil.checkNotMainThread();
        initializeIfNecessary();
        if (this.playingMediaSource != null) {
            saveMediaSourceState();
        }
        this.playingMediaSource = getMediaSource(urlVideoSource);
        this.exoPlayer.setPlayWhenReady(true);
        this.exoPlayer.prepare(this.playingMediaSource, true, true);
        Long l = (Long) PLAYBACK_POSITION_CACHE.get(urlVideoSource);
        if (l != null) {
            this.exoPlayer.seekTo(l.longValue());
        }
    }

    public void detach(VideoView2 videoView2) {
        AsyncUtil.checkMainThread();
        if (this.exoPlayer != null) {
            pause();
            this.attachedVideoView = null;
        }
    }

    public void stopAsync() {
        if (this.exoPlayer != null) {
            AsyncUtil.checkMainThread();
            saveMediaSourceState();
            this.exoPlayer.setPlayWhenReady(false);
            this.exoPlayer.clearVideoSurface();
            this.playingVideoSource = null;
            backgroundExecutor().execute(new Runnable() {
                public void run() {
                    VideoPlayer.this.exoPlayer.stop();
                }
            });
        }
    }

    private void saveMediaSourceState() {
        if (this.playingMediaSource != null) {
            try {
                PLAYBACK_POSITION_CACHE.put(this.playingVideoSource, Long.valueOf(this.exoPlayer.getCurrentPosition()));
            } catch (IndexOutOfBoundsException e) {
            }
            this.playingMediaSource = null;
        }
    }

    public void pause() {
        AsyncUtil.checkMainThread();
        this.exoPlayer.setPlayWhenReady(false);
    }

    public void resume() {
        AsyncUtil.checkMainThread();
        this.exoPlayer.setPlayWhenReady(true);
    }

    public void destroy() {
        AsyncUtil.checkMainThread();
        if (this.exoPlayer != null) {
            this.exoPlayer.stop();
            this.exoPlayer.clearVideoSurface();
            this.exoPlayer.release();
            this.exoPlayer = null;
        }
    }

    public boolean isDestroyed() {
        return this.exoPlayer == null;
    }

    public boolean isPlaying() {
        return this.exoPlayer != null && this.exoPlayer.getPlayWhenReady();
    }

    public void setVolume(float f) {
        if (this.audioManager == null) {
            this.audioManager = (AudioManager) this.appContext.getSystemService("audio");
        }
        this.exoPlayer.setVolume(f);
        if (f > 0.0f) {
            this.audioManager.requestAudioFocus(null, 3, 1);
        } else {
            this.audioManager.abandonAudioFocus(null);
        }
    }

    public void addListener(EventListener eventListener) {
        this.exoPlayer.addListener(eventListener);
    }

    public void removeListener(EventListener eventListener) {
        this.exoPlayer.removeListener(eventListener);
    }

    public void setVideoListener(VideoListener videoListener) {
        this.exoPlayer.setVideoListener(videoListener);
    }

    public long getCurrentPosition() {
        try {
            return this.exoPlayer.getCurrentPosition();
        } catch (IndexOutOfBoundsException e) {
            return 0;
        }
    }

    public long getDuration() {
        try {
            return this.exoPlayer.getDuration();
        } catch (IndexOutOfBoundsException e) {
            return 0;
        }
    }

    public int getBufferedPercentage() {
        return this.exoPlayer.getBufferedPercentage();
    }

    public void seekTo(long j) {
        this.exoPlayer.seekTo(j);
    }

    public UrlVideoSource getVideo() {
        return this.playingVideoSource;
    }

    private MediaSource buildMediaSource(Uri uri) {
        switch (Util.inferContentType(uri.getLastPathSegment())) {
            case 0:
                return new DashMediaSource(uri, this.dataSourceFactory, new DefaultDashChunkSource.Factory(this.dataSourceFactory), this.mainHandler, null);
            case 1:
                return new SsMediaSource(uri, this.dataSourceFactory, new DefaultSsChunkSource.Factory(this.dataSourceFactory), this.mainHandler, null);
            case 2:
                return new HlsMediaSource(uri, this.dataSourceFactory, this.mainHandler, null);
            default:
                return new ExtractorMediaSource(uri, this.dataSourceFactory, extractorsFactory, this.mainHandler, null);
        }
    }*/
}
