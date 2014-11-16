package and.mocaccino;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * This class is used to manage dimension in the application. <br>
 * Ratio is calculated based on current display size(in pixels) and template bitmap
 * background size(in pixels). This ratio is then used to calculate new dimension values. <br>
 * Calculated ratio works with pixels. 
 * 
 * Safe zone - A Relative layout with 1760x1120 size aligned in the center of unsafe zone.
 * 
 * UnSafe zone - A Parent Relative layout of the xml layout file.
 * 
 */
public class ADimen {
	
	public static PointF LANDSCAPE = new PointF(1920, 1200);
	
	public static PointF PORTRAIT = new PointF(1200, 1920);
	
	public static PointF display;
	
	public static PointF background;
	
	public static PointF RATIO;
	/** 
	 * Ratio for safe zone(1760x1120)
	 */
	public static float safeRatio;
	/** 
	 * Ratio for unsafe zone(1920x1200)
	 */
	public static float unsafeRatio;
	
	@SuppressWarnings("deprecation")
	@SuppressLint("NewApi")
	public static void init(Context context, PointF backgroundSize) {
		DisplayMetrics displayMetrics = new DisplayMetrics();
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		wm.getDefaultDisplay().getMetrics(displayMetrics);
		
		background = backgroundSize;

		if (Build.VERSION.SDK_INT < 13) {
			display = new PointF();
			Display theDisplay = wm.getDefaultDisplay();
			display.set(theDisplay.getWidth(), theDisplay.getHeight());
		} else {
			Point size = new Point();
			wm.getDefaultDisplay().getSize(size);
			display = new PointF(size);
		}
		RATIO = new PointF(background.x / display.x, background.y / display.y);
		safeRatio = Math.max(RATIO.x, RATIO.y);
		unsafeRatio = Math.min(RATIO.x, RATIO.y);
		//Log.d("AD", "x:"+RATIO.x+";y:"+RATIO.y+";ratio:"+safeRatio);
	}
	
	/**
	 * Convert size for Safe zone.
	 * @param size
	 * @return
	 */
	public static int cs(float size) {
		return (int) Math.ceil(size / safeRatio);
	}
	
	/**
	 * Convert size for Unsafe zone.
	 * @param size
	 * @return
	 */
	public static int cu(float size) {
		return (int) Math.ceil(size / unsafeRatio);
	}
}
