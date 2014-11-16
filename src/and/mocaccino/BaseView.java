package and.mocaccino;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;



public abstract class BaseView <T extends BaseView<T>>{
	
	static final String TAG = "BaseView";
	
	public static final int sdk = android.os.Build.VERSION.SDK_INT;
	
	static int availableProcessors = Runtime.getRuntime().availableProcessors();
	
	static ExecutorService loadService  = availableProcessors > 2 ? Executors.newFixedThreadPool(availableProcessors - 1) : Executors.newFixedThreadPool(1);
	
	protected int currentId = View.NO_ID;
	
	protected Activity activity;
	
	protected ViewGroup currentLayout;
	
	protected final Resources resources;
	
	protected final AssetManager assetManager;
	
	public BaseView(Activity activity) {
		this.activity = activity;
		this.resources = activity.getResources();
		this.assetManager = activity.getAssets();
	}
	
	public Map<String, Bitmap> getBitmap(Collection<String> fileNames, float ratio) {
		Map<String, Bitmap> bitmaps = new HashMap<String, Bitmap>();
		for(String fileName : fileNames){
			bitmaps.put(fileName, getBitmap(fileName, ratio));
		}
		return bitmaps;
	}
	
	public Bitmap getBitmap(String fileName, float ratio) {
    	BitmapFactory.Options op = new BitmapFactory.Options();
		op.inPreferredConfig = fileName.endsWith(".png")?Bitmap.Config.ARGB_8888:Bitmap.Config.RGB_565;
		op.inPurgeable = true;
		op.inInputShareable = true;
		op.inScaled = true;
		InputStream is;
		try {
			is = this.assetManager.open(fileName);
			Bitmap bitmap = BitmapFactory.decodeStream(is, null, op);
			int height =(int) Math.ceil(bitmap.getHeight() / ratio);
			int width = (int) Math.ceil(bitmap.getWidth() / ratio);
			return Bitmap.createScaledBitmap(bitmap, width, height, true);
		} catch(OutOfMemoryError e){
			Log.e(TAG, "getBitmap:" + e.getMessage());
		} catch (Exception e) {
			Log.e(TAG, "getBitmap:" + e.getMessage());
		}
		return null;
	}
	
	public Map<Integer, Drawable> getDrawable(Collection<Integer> resourceIds, float ratio) {
		Map<Integer, Drawable> result = new HashMap<Integer, Drawable>(resourceIds.size());
		for(Integer id : resourceIds){
			result.put(id, new BitmapDrawable(this.resources, getBitmap(id, ratio)));
		}
		return result;
	}
	
	public Drawable getDrawable(int resourceId, float ratio){
		return new BitmapDrawable(this.resources, getBitmap(resourceId, ratio));
	}
	
	public Bitmap getBitmap(int resourceId, float ratio) {
    	
    	Bitmap bitmap = BitmapFactory.decodeResource(this.resources, resourceId);
    	int height =(int) Math.ceil(bitmap.getHeight() / ratio);
		int width = (int) Math.ceil(bitmap.getWidth() / ratio);
		return Bitmap.createScaledBitmap(bitmap, width, height, true);
	}
	
	public Drawable getDrawable(Bitmap bitmap) {
		return new BitmapDrawable(this.resources, bitmap);
	}
	
	public Drawable getDrawable(int resourceId){
    	return new BitmapDrawable(this.resources, getBitmap(resourceId));
    }
	
	public Bitmap getBitmap(int resourceId) {
    	return BitmapFactory.decodeResource(this.resources, resourceId);
	}
	
	public Bitmap getBitmap(String fileName) {
    	BitmapFactory.Options op = new BitmapFactory.Options();
		op.inPreferredConfig = fileName.endsWith(".png")?Bitmap.Config.ARGB_8888:Bitmap.Config.RGB_565;
		op.inPurgeable = true;
		op.inInputShareable = true;
		op.inScaled = true;
		InputStream is;
		try {
			is = this.assetManager.open(fileName);
			return BitmapFactory.decodeStream(is, null, op);
		} catch(OutOfMemoryError e){
			Log.e(TAG, "getBitmap:" + e.getMessage());
		} catch (Exception e) {
			Log.e(TAG, "getBitmap:" + e.getMessage());
		}
		return null;
	}
	
	/**
     * Get localization string by id.
     * @param id - string id of localize resource
     * @return String
     */
    public String str(int id) {
		return this.resources.getString(id);
	}
    
	public T safeZone(int layoutId, Drawable background) {
		View view = this.activity.findViewById(layoutId);
		if(background != null){
			bg(view, background);
			LayoutParams params = view.getLayoutParams();
			params.height = background.getIntrinsicHeight();
			params.width = background.getIntrinsicWidth();
			view.setLayoutParams(params);
		}
		
		return (T) this;
	}
	
	public T unSafeZone(int layoutId, Drawable background) {
		View view = this.activity.findViewById(layoutId);
		if(background != null)bg(view, background);
		return (T) this;
	}
	
	public T img(ImageView imageView, Bitmap bitmap) {
		imageView.setImageBitmap(bitmap);
		return (T) this;
	}
	
	public T img(ImageView imageView, Drawable drawable) {
		imageView.setImageDrawable(drawable);
		return (T) this;
	}
	
	
	@SuppressLint("NewApi") 
	public void bg(View view, Drawable background) {
		//View view = this.activity.findViewById(viewId);
		if(sdk < 16){
			view.setBackgroundDrawable(background);
		}else {
			view.setBackground(background);
		}
	}
	
	public T setLayout(int layoutId) {
		
		currentLayout = (ViewGroup) activity.findViewById(layoutId);
		return (T) this;
	}
	
	public ViewGroup getLayout() {
		return this.currentLayout;
	}

	
	public List<View> getAll() {
    	List<View>views = new ArrayList<View>();
    	
    	int size = this.currentLayout.getChildCount();
    	for(int index=0; index<size; index++){
    		View view = this.currentLayout.getChildAt(index);
    		views.add(view);
    	}
		return views;
	}
	
	public void toActivity(Class clazz) {
		Intent intent = new Intent(this.activity, clazz);
		this.activity.startActivity(intent);
	}
	
	public void toActivityResult(Class clazz, int requestCode) {
		Intent intent = new Intent(this.activity, clazz);
		this.activity.startActivityForResult(intent, requestCode);
	}
	
	public Map<Integer, ? extends View> get(Integer ... ids) {
		Map<Integer, View>views = new HashMap<Integer, View>(ids.length);
    	
		for(Integer viewId : ids){
			View view = currentLayout.findViewById(viewId);
			views.put(viewId, view);
		}
    	return views;
	}
	
	public Map<Integer, ? extends View> get(Collection<Integer> ids) {
		return get(ids.toArray(new Integer[0]));
	}
	
	public View get(Integer id) {
		return currentLayout.findViewById(id);
	}
	
	public Map<Integer, ImageView> createIV(Collection<Integer> viewIds) {
		Map<Integer, ImageView> map = new HashMap<Integer, ImageView>(viewIds.size());
		for(Integer id : viewIds){
			ImageView view = new ImageView(this.activity);
			view.setId(id);
			map.put(id, view);
		}
		return map;
	}
	
	public ImageView createIV(int viewId) {
		ImageView view = new ImageView(this.activity);
		if(viewId == View.NO_ID){
			view.setId(View.generateViewId());
		}else {
			view.setId(viewId);
		}
		return view;
	}
	
	public T add(Collection<? extends View> views) {
		for(View view : views){
			this.currentLayout.addView(view);
		}
		
		return (T) this;
	}
	
	public T remove(Collection<View> views) {
		if(views != null){
			for(View view : views){
				this.currentLayout.removeView(view);
			}
		}else {
			this.currentLayout.removeAllViews();
		}
		return (T) this;
	}
}
