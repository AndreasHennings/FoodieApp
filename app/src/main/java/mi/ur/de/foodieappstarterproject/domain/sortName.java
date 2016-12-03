package mi.ur.de.foodieappstarterproject.domain;

import java.util.Comparator;


public class sortName implements Comparator<FoodieItem>
{
    @Override
    public int compare(FoodieItem foodieItem, FoodieItem t1)
    {
        int compareName= foodieItem.getName().compareToIgnoreCase(t1.getName()) ;
        return compareName;
    }
}
