package mi.ur.de.foodieappstarterproject.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


public class FoodieImageFileHelper
{

    public static String IMAGE_DIRECTORY = "FoodieApp";

    public static String LOG_MESSAGE = "Foodie";

    public static Uri getOutputImageFileURL()  //returns a new filename as Uri
    {

        Log.d(LOG_MESSAGE, "external dir:" + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath());

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), IMAGE_DIRECTORY);

        if (!mediaStorageDir.exists())
        {
            if (!mediaStorageDir.mkdirs())
            {
                Log.d(LOG_MESSAGE, "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile = new File(mediaStorageDir.getAbsolutePath() + File.separator + "IMG_" + timeStamp + ".jpg");
        return Uri.fromFile(mediaFile);
    }



    public static Bitmap getScaledBitmap(String fileName)
    {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 8;
        return BitmapFactory.decodeFile(fileName, options);
    }



    public static Bitmap getBitmap(String fileName)
    {
        return BitmapFactory.decodeFile(fileName, null);
    }
}

