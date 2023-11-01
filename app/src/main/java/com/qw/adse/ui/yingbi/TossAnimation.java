package com.qw.adse.ui.yingbi;

import static com.xuexiang.xui.utils.ResUtils.getResources;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;


public class TossAnimation extends Animation {

    public static final int DIRECTION_NONE = 0;
    public static final int DIRECTION_CLOCKWISE = 1;
    public static final int DIRECTION_ABTUCCLOCKWISE = -1;

    public static final int RESULT_FRONT = 1; // 正面
    public static final int RESULT_REVERSE = -1; // 反面


    private int mCircleCount;

    private int mXAxisDirection;

    private int mYAxisDirection;

    private int mZAxisDirection;

    private int mResult;


    private int mTotalAngle;

    private int mCurrentResult = -1;

    private Camera mCamera;

    private int mWidth;
    private int mHeight;

    float scale = 1;

    public TossAnimation(int circleCount, int xAxisDirection, int yAxisDirection, int zAxisDirection, int result) {
        this.mCircleCount = circleCount;
        this.mXAxisDirection = xAxisDirection;
        this.mYAxisDirection = yAxisDirection;
        this.mZAxisDirection = zAxisDirection;
        this.mResult = result;

        mTotalAngle = 360 * mCircleCount;
        mCamera = new Camera();

        scale = getResources().getDisplayMetrics().density;
    }

    private TossAnimationListener mTossAnimationListener;

    public void setTossAnimationListener(TossAnimationListener mTossAnimationListener) {
        this.mTossAnimationListener = mTossAnimationListener;
        setAnimationListener(mTossAnimationListener);
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        mWidth = width;
        mHeight = height;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {


        int degreeInCircle = ((int) (interpolatedTime * mTotalAngle)) % 360;


        if (degreeInCircle > 90 && degreeInCircle < 270) {
            if (mCurrentResult != -mResult) {
                mCurrentResult = -mResult;

                if (mTossAnimationListener != null) {
                    mTossAnimationListener.onDrawableChange(mCurrentResult, this);
                }
            }
        } else {
            if (mCurrentResult != mResult) {
                mCurrentResult = mResult;

                if (mTossAnimationListener != null) {
                    mTossAnimationListener.onDrawableChange(mCurrentResult, this);
                }
            }
        }

        Matrix matrix = t.getMatrix();


        mCamera.save();
        mCamera.rotate(mXAxisDirection * degreeInCircle, mYAxisDirection * degreeInCircle, mZAxisDirection * degreeInCircle);
        mCamera.getMatrix(matrix);
        mCamera.restore();


        float[] mValues = new float[9];
        matrix.getValues(mValues);
        mValues[6] = mValues[6]/scale;
        mValues[7] = mValues[7]/scale;
        matrix.setValues(mValues);


        matrix.preTranslate(-(mWidth >> 1), -(mHeight >> 1));
        matrix.postTranslate(mWidth >> 1, mHeight >> 1);

    }

    public interface TossAnimationListener extends AnimationListener {


        void onDrawableChange(int result, TossAnimation animation);
    }
}
