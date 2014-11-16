package and.mocaccino;

import android.app.Activity;

public class AView extends BaseView<AView>{

	public AView(Activity activity) {
		super(activity);
	}

	public void run(Runnable runnable) {
		super.loadService.execute(runnable);
	}
}
