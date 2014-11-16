package and.mocaccino;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.VideoView;

public class AUtils {
	
	public static final int sdk = android.os.Build.VERSION.SDK_INT;
	
	static final String TAG = "AUtils";
	

	public static ViewGroup.LayoutParams WW = new ViewGroup.LayoutParams(
			ViewGroup.LayoutParams.WRAP_CONTENT,
	        ViewGroup.LayoutParams.WRAP_CONTENT);
	
	public static ViewGroup.LayoutParams MM = new ViewGroup.LayoutParams(
			ViewGroup.LayoutParams.MATCH_PARENT,
			ViewGroup.LayoutParams.MATCH_PARENT);
	
	public static ViewGroup.LayoutParams WM = new ViewGroup.LayoutParams(
			ViewGroup.LayoutParams.WRAP_CONTENT,
			ViewGroup.LayoutParams.MATCH_PARENT);
	
	public static ViewGroup.LayoutParams MW = new ViewGroup.LayoutParams(
			ViewGroup.LayoutParams.MATCH_PARENT,
			ViewGroup.LayoutParams.WRAP_CONTENT);
	
	public AUtils() {
	}

	public static Bitmap scale(Bitmap bitmap, int width, int height) {
		return Bitmap.createScaledBitmap(bitmap, width, height, true);
	}

	/**
	 * Set size for view.
	 * 
	 * @param view
	 * @param width
	 * @param height
	 */
	public static void size(View view, int width, int height) {
		LayoutParams params = view.getLayoutParams();
		params.height = height;
		params.width = width;
		view.setLayoutParams(params);
	}

	/**
	 * Set transparency property for collection of views.
	 * @param views
	 * @param alpha
	 */
	public static void alpha(Collection<? extends View> views, float alpha) {
		if(sdk < 9){
			alpha = (int)alpha;
		}
		
		for(View view : views){
			view.setAlpha(alpha);
		}
	}
	
	/**
	 * Set transparency property for view.
	 * @param view
	 * @param alpha
	 */
	public static void alpha(View view, float alpha) {
		if(sdk < 9){
			alpha = (int)alpha;
		}
		view.setAlpha(alpha);
	}
	
	/**
	 * Set visible property for collection of views.
	 * @param viewIds
	 * @param visibility - View.GONE, View.VISIBLE, View.INVISIBLE
	 * @return
	 */
	public static void visible(Collection<? extends View> views, int visibility) {
		
		for(View view : views){
			view.setVisibility(visibility);
		}
	}
	
	/**
	 * Set visible property for view.
	 * @param view
	 * @param visibility - View.GONE, View.VISIBLE, View.INVISIBLE
	 */
	public static void visible(View view, int visibility) {
		view.setVisibility(visibility);
	}
	
	/**
	 * Set layout params for map of views.
	 * @param views
	 * @param params
	 */
	public static void setParams(Map<Integer, ? extends View> views, Map<Integer, ? extends ViewGroup.LayoutParams> params) {
		
		for(Entry<Integer, ? extends View> entry : views.entrySet()){
			setParams(entry.getValue(), params.get(entry.getKey()));
		}
	}
	
	/**
	 * Set layout params for view.
	 * @param view
	 * @param params
	 */
	public static void setParams(View view, ViewGroup.LayoutParams params) {
		view.setLayoutParams(params);
	}
	
	/**
	 * Get linear params based on margin params.
	 * @param margins
	 * @return
	 */
	public static LinearLayout.LayoutParams getLinearParams(ViewGroup.MarginLayoutParams margins) {
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(margins);
		params.setMargins(margins.leftMargin, margins.topMargin, margins.rightMargin, margins.bottomMargin);
		return params;
	}
	
	/**
	 * Get linear params.
	 * @param params
	 * @return
	 */
	public static LinearLayout.LayoutParams getLinearParams(ViewGroup.LayoutParams params) {
		return new LinearLayout.LayoutParams(params);
	}
	
	/**
	 * Get relative params based on margin params.
	 * @param margins
	 * @return
	 */
	public static RelativeLayout.LayoutParams getRelativeParams(ViewGroup.MarginLayoutParams margins) {
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(margins);
		params.setMargins(margins.leftMargin, margins.topMargin, margins.rightMargin, margins.bottomMargin);
		return params;
	}
	
	/**
	 * Get relative layout params.
	 * @param width
	 * @param height
	 * @return
	 */
	public static RelativeLayout.LayoutParams getRelativeParams(int width, int height) {
		return new RelativeLayout.LayoutParams(width, height);
	}
	
	/**
	 * Get marginParams based on layout params.
	 * @param gparams
	 * @param left
	 * @param top
	 * @param right
	 * @param bottom
	 * @return
	 */
	public static ViewGroup.MarginLayoutParams getMargin(ViewGroup.LayoutParams gparams, int left, int top, int right, int bottom) {
		ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(gparams);
		params.setMargins(left, top, right, bottom);
		return params;
	}
    
    /**
     * Set bitmaps for map of imageViews.
     * @param views
     * @param bitmaps
     * @param convert
     */
	public static void setBitmaps(Map<Integer, ImageView> views, Map<String, Bitmap> bitmaps, Map<Integer, String> convert) {
		for(Entry<Integer, ImageView>entry: views.entrySet()){
			Bitmap bm = bitmaps.get(convert.get(entry.getKey()));
			entry.getValue().setImageBitmap(bm);
		}
	}
    
	/**
	 * Set drawables for map of views.
	 * @param views
	 * @param drawables
	 * @param convert
	 */
	public static void setDrawables(Map<Integer, ImageView> views, Map<Integer, Drawable> drawables, Map<Integer, Integer> convert) {
		for (Entry<Integer, ImageView> entry : views.entrySet()) {
			Drawable drawable = drawables.get(convert.get(entry.getKey()));
			entry.getValue().setImageDrawable(drawable);
		}
	}
    
    /**
     * Play video file from res folder.
     * Then call mediaPlayer.start();
     * @param fileName
     * @param listener
     * @return
     * @throws Exception
     */
	public static MediaPlayer playSound(AssetManager assetManager, String fileName, MediaPlayer.OnCompletionListener listener) throws Exception {
    	MediaPlayer mediaPlayer = new MediaPlayer();
		if (listener != null) {
			mediaPlayer.setOnCompletionListener(listener);
		}
		
		AssetFileDescriptor descriptor = assetManager.openFd(fileName);
		mediaPlayer.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(),
				descriptor.getLength());
		descriptor.close();
		mediaPlayer.prepare();
		
		return mediaPlayer;
	}
    
    /**
     * Play video file from res folder.
     * Then call video.start();
     * @param activity - current Activity
     * @param videoViewId R.id.introVideo
     * @param videoResourceId R.raw.intro - res/raw/intro.mp4
     * @return VideoView
     */
	public static VideoView playVideo(Activity activity, int videoViewId, int videoResourceId, MediaPlayer.OnCompletionListener listener) {
    	activity.getWindow().setFormat(PixelFormat.TRANSLUCENT);
		VideoView view = (VideoView)activity.findViewById(videoViewId);		
		view.setVideoURI(Uri.parse("android.resource://" + activity.getPackageName() +File.separator+videoResourceId));
		view.setKeepScreenOn(true);
		view.setMediaController(null);
		view.setOnCompletionListener(listener);
		view.requestFocus();
		return view;
	}
    
    /**
     * Set listener for collection of views.
     * @param listener
     * @param views
     */
	public static void listener(View.OnClickListener listener, Collection<? extends View> views) {
    	for(View view : views){
    		view.setOnClickListener(listener);
    	}
	}
    
    /**
     * Set listener for array of views.
     * @param listener
     * @param views
     */
	public static void listener(View.OnClickListener listener, View ... views ) {
		listener(listener, Arrays.asList(views));
	}
    
    /**
     * Update texts for map of textButton views.
     * @param views
     * @param texts
     */
	public static void uTextBtn(Map<Integer, Button>views, Map<Integer, String> texts) {
		for(Entry<Integer, Button>entry : views.entrySet()){
			Button button = entry.getValue();
			button.setText(texts.get(entry.getKey()));
		}
	}
    
	public static void openImageAction(Activity activity, int requestCode) {
		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		intent.addCategory(Intent.CATEGORY_OPENABLE);
		activity.startActivityForResult(intent, requestCode);
	}
 
    /**
     * Then call recorder.start();
     * @param audiofile
     * @param format MediaRecorder.OutputFormat.THREE_GPP - ".3gp"
     * @throws Exception
     * @return MediaRecorder
     */
	public static MediaRecorder startRecordAction(File audiofile, int format)
			throws Exception {
		MediaRecorder recorder = new MediaRecorder();
		recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		recorder.setOutputFormat(format);
		recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
		recorder.setOutputFile(audiofile.getAbsolutePath());
		recorder.prepare();

		return recorder;
	}

	public static void stopRecordAction(MediaRecorder recorder) {
		if (recorder != null) {
			recorder.stop();
			recorder.release();
			recorder = null;
		}
	}
    
	public static void playRecordAction(File audiofile) throws Exception {
		MediaPlayer mp = new MediaPlayer();
		FileInputStream fis = new FileInputStream(audiofile.getAbsolutePath());
		mp.setDataSource(fis.getFD());
		mp.prepare();
		fis.close();
		mp.start();
	}

	@TargetApi(19)
	public static void setUIVisibility(Activity activity) {
		View decorView = activity.getWindow().getDecorView();
		int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
		if (Build.VERSION.SDK_INT >= 16) {
			uiOptions = uiOptions | View.SYSTEM_UI_FLAG_FULLSCREEN;
		}
		if (Build.VERSION.SDK_INT >= 19) {
			uiOptions = uiOptions | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
		}
		decorView.setSystemUiVisibility(uiOptions);
	}

	@TargetApi(19)
	public static void setUIVisibility(Dialog activity) {
		View decorView = activity.getWindow().getDecorView();
		int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
		if (Build.VERSION.SDK_INT >= 16) {
			uiOptions = uiOptions | View.SYSTEM_UI_FLAG_FULLSCREEN;
		}
		if (Build.VERSION.SDK_INT >= 19) {
			uiOptions = uiOptions | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
		}
		decorView.setSystemUiVisibility(uiOptions);
	}
    
    /**
     * Method for finding the flag given its index.
     * @param index The index of the flag of one of the 32 countries.
     * @param width The width of the source image.
     * @return Rect
     * 
     * <p>
     * Usage for BitmapRegiondDecoder.
     *  It decodes a region of a bitmap. 
     *  It can be very useful if you only need a region of a large image.
     *  <p>
     */
	public static Rect getRectForIndex(int index, int width, int countAllImages, int countPerRow, int space) {
        // check if index is valid
        if (index < 0 && index >= countAllImages)
            throw new IllegalArgumentException("Index must be between 0 and 31.");
     
        // calculate one side of a single flag
        int oneSide = (width - ((countPerRow + 1) * space)) / countPerRow;
     
        // calculate the row and col of the given index
        int row = (index / countPerRow);
        int col = (index % countPerRow);
     
        // left and right sides of the rectangle
        int left = (oneSide * col) + (space * (col + 1));
        int right = left + oneSide;
     
        // top and bottom sides of the rectangle
        int top = (oneSide * row) + (space * (row + 1));
        int bottom = top + oneSide;
     
        // the resulting rectangle
        return new Rect(left, top, right, bottom);
    }
}
