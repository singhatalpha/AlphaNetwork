package Utils;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

/**
 * Created by User on 8/21/2017.
 */

public class LikesToggle {

    private static final String TAG = "Heart";

    private static final DecelerateInterpolator DECCELERATE_INTERPOLATOR = new DecelerateInterpolator();
    private static final AccelerateInterpolator ACCELERATE_INTERPOLATOR = new AccelerateInterpolator();

    public ImageView imgView_like, imgView_liked, imgView_dislike, imgView_disliked;

    public LikesToggle(ImageView imgView_like, ImageView imgView_liked, ImageView imgView_dislike, ImageView imgView_disliked) {
        this.imgView_like = imgView_like;
        this.imgView_liked = imgView_liked;
        this.imgView_dislike = imgView_dislike;
        this.imgView_disliked = imgView_disliked;
    }

    public void toggleLike() {
        Log.d(TAG, "toggleLike: toggling likes.");

        AnimatorSet animationSet = new AnimatorSet();


        if (imgView_liked.getVisibility() == View.VISIBLE) {
            Log.d(TAG, "toggleLike: toggling red heart off.");
            imgView_liked.setScaleX(0.1f);
            imgView_liked.setScaleY(0.1f);

            ObjectAnimator scaleDownY = ObjectAnimator.ofFloat(imgView_liked, "scaleY", 1f, 0f);
            scaleDownY.setDuration(300);
            scaleDownY.setInterpolator(ACCELERATE_INTERPOLATOR);

            ObjectAnimator scaleDownX = ObjectAnimator.ofFloat(imgView_liked, "scaleX", 1f, 0f);
            scaleDownX.setDuration(300);
            scaleDownX.setInterpolator(ACCELERATE_INTERPOLATOR);

            imgView_liked.setVisibility(View.GONE);
            imgView_like.setVisibility(View.VISIBLE);

            animationSet.playTogether(scaleDownY, scaleDownX);
        } else if (imgView_liked.getVisibility() == View.GONE) {
            Log.d(TAG, "toggleLike: toggling red heart on.");
            imgView_liked.setScaleX(0.1f);
            imgView_liked.setScaleY(0.1f);

            ObjectAnimator scaleDownY = ObjectAnimator.ofFloat(imgView_liked, "scaleY", 0.1f, 1f);
            scaleDownY.setDuration(300);
            scaleDownY.setInterpolator(DECCELERATE_INTERPOLATOR);

            ObjectAnimator scaleDownX = ObjectAnimator.ofFloat(imgView_liked, "scaleX", 0.1f, 1f);
            scaleDownX.setDuration(300);
            scaleDownX.setInterpolator(DECCELERATE_INTERPOLATOR);

            imgView_liked.setVisibility(View.VISIBLE);
            imgView_like.setVisibility(View.GONE);

            animationSet.playTogether(scaleDownY, scaleDownX);
        }
        animationSet.start();
    }


        public void toggleDisLike() {
            Log.d(TAG, "toggleLike: toggling likes.");

            AnimatorSet animationSet = new AnimatorSet();

            if (imgView_disliked.getVisibility() == View.VISIBLE) {
                Log.d(TAG, "toggleLike: toggling dislike off.");
                imgView_disliked.setScaleX(0.1f);
                imgView_disliked.setScaleY(0.1f);

                ObjectAnimator scaleDownY = ObjectAnimator.ofFloat(imgView_disliked, "scaleY", 1f, 0f);
                scaleDownY.setDuration(300);
                scaleDownY.setInterpolator(ACCELERATE_INTERPOLATOR);

                ObjectAnimator scaleDownX = ObjectAnimator.ofFloat(imgView_disliked, "scaleX", 1f, 0f);
                scaleDownX.setDuration(300);
                scaleDownX.setInterpolator(ACCELERATE_INTERPOLATOR);

                imgView_disliked.setVisibility(View.GONE);
                imgView_dislike.setVisibility(View.VISIBLE);

                animationSet.playTogether(scaleDownY, scaleDownX);
            } else if (imgView_disliked.getVisibility() == View.GONE) {
                Log.d(TAG, "toggleLike: toggling dislike on.");
                imgView_disliked.setScaleX(0.1f);
                imgView_disliked.setScaleY(0.1f);

                ObjectAnimator scaleDownY = ObjectAnimator.ofFloat(imgView_disliked, "scaleY", 0.1f, 1f);
                scaleDownY.setDuration(300);
                scaleDownY.setInterpolator(DECCELERATE_INTERPOLATOR);

                ObjectAnimator scaleDownX = ObjectAnimator.ofFloat(imgView_disliked, "scaleX", 0.1f, 1f);
                scaleDownX.setDuration(300);
                scaleDownX.setInterpolator(DECCELERATE_INTERPOLATOR);

                imgView_disliked.setVisibility(View.VISIBLE);
                imgView_dislike.setVisibility(View.GONE);

                animationSet.playTogether(scaleDownY, scaleDownX);
            }


            animationSet.start();
        }

    }




