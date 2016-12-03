package mi.ur.de.foodieappstarterproject.log;

public class Log {
	public static final boolean DEBUG = true;
	public static final String TAG = "FoodieApp";
	
	public static void d(String tag, String msg) {
		if (DEBUG) {
			android.util.Log.d(tag, msg);
		}
	}

	public static void d(String msg) {
		if (DEBUG) {
			android.util.Log.d(TAG, msg);
		}
	}
}
