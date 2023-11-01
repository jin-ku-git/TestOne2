package com.qw.adse.ui.yingbi;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ImageView;

import com.qw.adse.R;

import java.util.HashSet;
import java.util.Set;


public class TossImageView extends ImageView {

    public static final int DIRECTION_NONE = 0;
    public static final int DIRECTION_CLOCKWISE = 1;
    public static final int DIRECTION_ABTUCCLOCKWISE = -1;

    public static final int RESULT_FRONT = 1;
    public static final int RESULT_REVERSE = -1;


    private int mCircleCount;

    private int mXAxisDirection;

    private int mYAxisDirection;

    private int mZAxisDirection;

    private int mResult;

    private int mDuration;

    private int mStartOffset;
    /**
     * Interpolator
     */
    private Interpolator mInterpolator = new DecelerateInterpolator();


    private Drawable mFrontDrawable;


    private Drawable mReversetDrawable;


    private TossAnimation.TossAnimationListener mTossAnimationListener;

    private Set<Animation> mOtherAnimation = new HashSet<Animation>();

    public TossImageView(Context context) {
        super(context);
        setCoinDrawableIfNecessage();
    }

    public TossImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TossAnimation);

        mCircleCount = a.getInteger(R.styleable.TossAnimation_circleCount, context.getResources().getInteger(R.integer.toss_default_circleCount));
        mXAxisDirection = a.getInteger(R.styleable.TossAnimation_xAxisDirection, context.getResources().getInteger(R.integer.toss_default_xAxisDirection));
        mYAxisDirection = a.getInteger(R.styleable.TossAnimation_yAxisDirection, context.getResources().getInteger(R.integer.toss_default_yAxisDirection));
        mZAxisDirection = a.getInteger(R.styleable.TossAnimation_zAxisDirection, context.getResources().getInteger(R.integer.toss_default_zAxisDirection));
        mResult = a.getInteger(R.styleable.TossAnimation_result, context.getResources().getInteger(R.integer.toss_default_result));

        mFrontDrawable = a.getDrawable(R.styleable.TossAnimation_frontDrawable);
        mReversetDrawable = a.getDrawable(R.styleable.TossAnimation_reverseDrawable);

        mDuration = a.getInteger(R.styleable.TossAnimation_duration, context.getResources().getInteger(R.integer.toss_default_duration));
        mStartOffset = a.getInteger(R.styleable.TossAnimation_startOffset, context.getResources().getInteger(R.integer.toss_default_startOffset));

        a.recycle();

        setCoinDrawableIfNecessage();
    }

    public TossImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TossAnimation, defStyleAttr, 0);

        mCircleCount = a.getInteger(R.styleable.TossAnimation_circleCount, context.getResources().getInteger(R.integer.toss_default_circleCount));
        mXAxisDirection = a.getInteger(R.styleable.TossAnimation_xAxisDirection, context.getResources().getInteger(R.integer.toss_default_xAxisDirection));
        mYAxisDirection = a.getInteger(R.styleable.TossAnimation_yAxisDirection, context.getResources().getInteger(R.integer.toss_default_yAxisDirection));
        mZAxisDirection = a.getInteger(R.styleable.TossAnimation_zAxisDirection, context.getResources().getInteger(R.integer.toss_default_zAxisDirection));
        mResult = a.getInteger(R.styleable.TossAnimation_result, context.getResources().getInteger(R.integer.toss_default_result));

        mFrontDrawable = a.getDrawable(R.styleable.TossAnimation_frontDrawable);
        mReversetDrawable = a.getDrawable(R.styleable.TossAnimation_reverseDrawable);

        mDuration = a.getInteger(R.styleable.TossAnimation_duration, context.getResources().getInteger(R.integer.toss_default_duration));
        mStartOffset = a.getInteger(R.styleable.TossAnimation_startOffset, context.getResources().getInteger(R.integer.toss_default_startOffset));

        a.recycle();

        setCoinDrawableIfNecessage();
    }


    private void setCoinDrawableIfNecessage() {
        if (mFrontDrawable == null) {
            mFrontDrawable = getDrawable();
        }
        if (mReversetDrawable == null) {
            mReversetDrawable = getDrawable();
        }
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
        setCoinDrawableIfNecessage();
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        setCoinDrawableIfNecessage();
    }

    @Override
    public void setImageResource(int resId) {
        super.setImageResource(resId);
        setCoinDrawableIfNecessage();
    }

    @Override
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        setCoinDrawableIfNecessage();
    }


    public TossImageView setCircleCount(int circleCount) {
        this.mCircleCount = circleCount;
        return this;
    }


    public TossImageView setXAxisDirection(int xAxisDirection) {
        if(Math.abs(xAxisDirection) > 1){
            throw new RuntimeException("Math.abs(Direction) must be less than 1");
        }
        this.mXAxisDirection = xAxisDirection;
        return this;
    }

    /**
     *
     *
     * @param yAxisDirection TossAnimation.DIRECTION_NONE  or  TossAnimation.DIRECTION_CLOCKWISE  or  TossAnimation.DIRECTION_ABTUCCLOCKWISE
     * @return
     */
    public TossImageView setYAxisDirection(int yAxisDirection) {
        if(Math.abs(yAxisDirection) > 1){
            throw new RuntimeException("Math.abs(Direction) must be less than 1");
        }
        this.mYAxisDirection = yAxisDirection;
        return this;
    }

    /**
     *
     *
     * @param zAxisDirection TossAnimation.DIRECTION_NONE  or  TossAnimation.DIRECTION_CLOCKWISE  or  TossAnimation.DIRECTION_ABTUCCLOCKWISE
     * @return
     */
    public TossImageView setZAxisDirection(int zAxisDirection) {
        if(Math.abs(zAxisDirection) > 1){
            throw new RuntimeException("Math.abs(Direction) must be less than 1");
        }
        this.mZAxisDirection = zAxisDirection;
        return this;
    }

    /**
     *
     *
     * @param result TossAnimation.RESULT_FRONT
     *               TossAnimation.RESULT_REVERSE
     * @return
     */
    public TossImageView setResult(int result) {
        if(Math.abs(result) != 1){
            throw new RuntimeException("Math.abs(Direction) must be 1");
        }
        this.mResult = result;
        return this;
    }

    public TossImageView setFrontDrawable(Drawable frontDrawable) {
        this.mFrontDrawable = frontDrawable;
        return this;
    }


    public TossImageView setReversetDrawable(Drawable reversetDrawable) {
        this.mReversetDrawable = reversetDrawable;
        return this;
    }

    public TossImageView setDuration(int duration) {
        this.mDuration = duration;
        return this;
    }

    public TossImageView setStartOffset(int startOffset) {
        this.mStartOffset = startOffset;
        return this;
    }

    /**
     * set Interpolator
     *
     * @param interpolator
     * @return
     */
    public TossImageView setInterpolator(Interpolator interpolator) {
        this.mInterpolator = interpolator;
        return this;
    }

    public TossImageView addOtherAnimation(Animation animation) {
        mOtherAnimation.add(animation);
        return this;
    }

    public TossImageView removeOtherAnimation(Animation animation) {
        mOtherAnimation.remove(animation);
        return this;
    }

    public TossImageView cleareOtherAnimation() {
        mOtherAnimation.clear();
        return this;
    }

    public TossImageView setTossAnimationListener(TossAnimation.TossAnimationListener tossAnimationListener) {
        this.mTossAnimationListener = tossAnimationListener;
        return this;
    }

    public void startToss() {

        clearAnimation();

        TossAnimation tossAnimation = new TossAnimation(mCircleCount, mXAxisDirection, mYAxisDirection, mZAxisDirection, mResult);
        tossAnimation.setDuration(mDuration);
        tossAnimation.setStartOffset(mStartOffset);
        tossAnimation.setInterpolator(mInterpolator);
        tossAnimation.setTossAnimationListener(new QTTossAnimationListener(mTossAnimationListener));

        AnimationSet as = new AnimationSet(false);
        as.addAnimation(tossAnimation);

        for (Animation animation : mOtherAnimation) {
            as.addAnimation(animation);
        }

        startAnimation(as);

    }

    public class QTTossAnimationListener implements TossAnimation.TossAnimationListener {

        private TossAnimation.TossAnimationListener mTossAnimationListener;

        public QTTossAnimationListener(TossAnimation.TossAnimationListener tossAnimationListener) {
            mTossAnimationListener = tossAnimationListener;
        }

        @Override
        public void onDrawableChange(int result, TossAnimation animation) {
            switch (result) {
                case TossAnimation.RESULT_FRONT:
                    setImageDrawable(mFrontDrawable);
                    break;
                case TossAnimation.RESULT_REVERSE:
                    setImageDrawable(mReversetDrawable);
                    break;
            }
            if (mTossAnimationListener != null) {
                mTossAnimationListener.onDrawableChange(result, animation);
            }
        }

        @Override
        public void onAnimationStart(Animation animation) {
            if (mTossAnimationListener != null) {
                mTossAnimationListener.onAnimationStart(animation);
            }
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if (mTossAnimationListener != null) {
                mTossAnimationListener.onAnimationEnd(animation);
            }
            Log.e("qingtian", "end");
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
            if (mTossAnimationListener != null) {
                mTossAnimationListener.onAnimationRepeat(animation);
            }
        }
    }
}
