package and.mocaccino;

import android.animation.Animator.AnimatorListener;
import android.graphics.PointF;
import android.view.animation.Interpolator;

public class AnimatorProps {

	/**
	 * scaleX, scaleY, rotation, x, y, alpha, shadowDepth, saturation, height, width,
	 * rotationX, rotationY, translationX, translationY
	 */
	private String propertyName;
	private AnimatorListener listener;
	private long duration=0;
	
	/**
	 * LinearInterpolator
		AccelerateDecelerateInterpolator
		AccelerateInterpolator
		AnticipateInterpolator
		AnticipateOvershootInterpolator
		BounceInterpolator
		CycleInterpolator
		DecelerateInterpolator
		LinearInterpolator
		OvershootInterpolator
	 */
	private Interpolator interpolator;
	private PointF target;
	private boolean playTogether;
	
	public AnimatorProps(String propertyName,PointF target, long duration, 
			boolean playTogether, AnimatorListener listener) {
		this.propertyName = propertyName;
		this.listener = listener;
		this.duration = duration;
		this.target = target;
		this.playTogether = playTogether;
	}

	public AnimatorProps(AnimatorListener listener, long duration) {
		this.listener = listener;
		this.duration = duration;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public AnimatorListener getListener() {
		return listener;
	}

	public void setListener(AnimatorListener listener) {
		this.listener = listener;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public Interpolator getInterpolator() {
		return interpolator;
	}

	public void setInterpolator(Interpolator interpolator) {
		this.interpolator = interpolator;
	}

	public boolean isPlayTogether() {
		return playTogether;
	}

	public void setPlayTogether(boolean playTogether) {
		this.playTogether = playTogether;
	}

	public PointF getTarget() {
		return target;
	}

	public void setTarget(PointF target) {
		this.target = target;
	}
	
	
}
