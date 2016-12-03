package mi.ur.de.foodieappstarterproject.domain;

import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;

import java.util.Comparator;

import mi.ur.de.foodieappstarterproject.util.FoodieImageFileHelper;

/**
 * Created by Andreas Hennings
 */
public class FoodieItem
{
    private String name;
    private String image;
    private float rating;
    private Bitmap bitmap;

    public FoodieItem(String name, String image, float rating)
    {
        this.name=name;
        this.image=image;
        this.rating=rating;
        bitmap = FoodieImageFileHelper.getScaledBitmap(image);
    }

    public String getName()
    {
        return name;
    }

    public Bitmap getBitmap() {return bitmap;}

    public String getImage()
    {
        return image;
    }

    public float getRating()
    {
        return rating;
    }


}
