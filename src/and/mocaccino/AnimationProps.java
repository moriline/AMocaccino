package and.mocaccino;

import android.view.animation.Animation.AnimationListener;
import android.view.animation.Interpolator;

public class AnimationProps {

	private AnimationListener listener;
	private long duration=0;
	//private Animation animation;
	private Interpolator interpolator;
	private boolean fillAfter=false;
	private boolean fillBefore=false;
	private boolean fillEnabled=false;
	//ObjectAnimator.INFINITE
	private int repeatCount = 0;
	//ValueAnimator.REVERSE
	private int repeatMode=0;
	private long startOffset = 0;
	private long startTime = 0;
	
	public AnimationProps(AnimationListener listener, long duration) {
		this.listener = listener;
		this.duration = duration;
	}

	public AnimationListener getListener() {
		return listener;
	}

	public void setListener(AnimationListener listener) {
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

	public boolean isFillAfter() {
		return fillAfter;
	}

	public void setFillAfter(boolean fillAfter) {
		this.fillAfter = fillAfter;
	}

	public boolean isFillBefore() {
		return fillBefore;
	}

	public void setFillBefore(boolean fillBefore) {
		this.fillBefore = fillBefore;
	}

	public boolean isFillEnabled() {
		return fillEnabled;
	}

	public void setFillEnabled(boolean fillEnabled) {
		this.fillEnabled = fillEnabled;
	}

	public int getRepeatCount() {
		return repeatCount;
	}

	public void setRepeatCount(int repeatCount) {
		this.repeatCount = repeatCount;
	}

	public int getRepeatMode() {
		return repeatMode;
	}

	public void setRepeatMode(int repeatMode) {
		this.repeatMode = repeatMode;
	}

	public long getStartOffset() {
		return startOffset;
	}

	public void setStartOffset(long startOffset) {
		this.startOffset = startOffset;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
}
