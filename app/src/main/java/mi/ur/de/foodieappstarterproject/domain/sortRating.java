package mi.ur.de.foodieappstarterproject.domain;

import java.util.Comparator;


public class sortRating implements Comparator<FoodieItem>
{
    @Override
    public int compare(FoodieItem foodieItem, FoodieItem t1)
    {
        if (foodieItem.getRating()==t1.getRating())
        {return 0;}

        if (foodieItem.getRating()<t1.getRating())
        {return 1;}

        return -1;
    }
}
