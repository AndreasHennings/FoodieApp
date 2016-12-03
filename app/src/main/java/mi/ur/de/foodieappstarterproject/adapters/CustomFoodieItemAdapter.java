package mi.ur.de.foodieappstarterproject.adapters;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

import mi.ur.de.foodieappstarterproject.R;
import mi.ur.de.foodieappstarterproject.domain.FoodieItem;


/**
 * Created by Andreas Hennings
 */
public class CustomFoodieItemAdapter extends ArrayAdapter<FoodieItem>
{
    private ArrayList<FoodieItem> allItems;
    private Context context;
    private int customFoodieItemView;

    public CustomFoodieItemAdapter(Context context, ArrayList<FoodieItem> allItems)
    {
        super(context, R.layout.item, allItems);
        this.context=context;
        this.allItems=allItems;
        this.customFoodieItemView=R.layout.item;
    }

    @Override
    public int getCount()
    {
        return allItems.size();
    }

    @Override
    public FoodieItem getItem(int position)
    {
        return allItems.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View foodieListItem;

        if (convertView==null)
        {
            LayoutInflater loi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            foodieListItem = loi.inflate(customFoodieItemView, null);
        }

        else
        {
            foodieListItem=convertView;
        }

        ImageView imageView = (ImageView) foodieListItem.findViewById(R.id.imageView);
        TextView editText = (TextView) foodieListItem.findViewById(R.id.editText);
        RatingBar ratingBar = (RatingBar) foodieListItem.findViewById(R.id.ratingBar);

        imageView.setImageBitmap(allItems.get(position).getBitmap());
        editText.setText(allItems.get(position).getName());
        ratingBar.setRating(allItems.get(position).getRating());

        return foodieListItem;
    }
}
