package and.mocaccino;


import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.Animator.AnimatorListener;
import android.app.Activity;
import android.graphics.Point;
import android.graphics.PointF;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;


/**
 * Helper class for animations.
 *
 */
public class Annim {

	Activity activity;
	
	public Annim(Activity activity) {
		this.activity = activity;
	}

	public Animation load(int resourceId) {
		Animation resAnimation = AnimationUtils.loadAnimation(this.activity, resourceId);
		
		return resAnimation;
	}
	
	public void loadAndStart(View view, int resourceId) {
		Animation resAnimation = AnimationUtils.loadAnimation(this.activity, resourceId);
		view.clearAnimation();
		view.startAnimation(resAnimation);
	}
	
	public AlphaAnimation alpha(PointF target, long duration) {
		AlphaAnimation animation = new AlphaAnimation(target.x, target.y);
		animation.setDuration(duration);
		
		return animation;
	}
	
	public AnimatorSet playTogether(View view, long duration, Point target) {
		AnimatorSet set = new AnimatorSet();
		set.playTogether(ObjectAnimator.ofFloat(view, "x", target.x).setDuration(duration), 
				ObjectAnimator.ofFloat(view, "y", target.y).setDuration(duration));
		
		return set;
	}
	
	public AnimatorSet animSet(Animator [] animators, AnimatorProps props) {
		AnimatorSet set = new AnimatorSet();
		
		AnimatorListener listener = props.getListener();
		if(listener != null) set.addListener(listener);
		long duration = props.getDuration();
		if(duration > 0) set.setDuration(duration);
		
		if(props.isPlayTogether()){
			set.playTogether(animators);
		}else {
			set.playSequentially(animators);
		}
		return set;
	}
	
	public static Animator buildAnimator(View view, AnimatorProps props) {
		Animator animator = ObjectAnimator.ofFloat(view, props.getPropertyName(), props.getTarget().x, props.getTarget().y);
		AnimatorListener listener = props.getListener();
		if(listener != null)animator.addListener(listener);
		long duration = props.getDuration();
		if(duration > 0) animator.setDuration(duration);
		Interpolator interpolator = props.getInterpolator();
		if(interpolator != null)animator.setInterpolator(interpolator);
		return animator;
	}
}
