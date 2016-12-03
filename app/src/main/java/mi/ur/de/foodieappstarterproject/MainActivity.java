package mi.ur.de.foodieappstarterproject;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import mi.ur.de.foodieappstarterproject.activities.FoodieGalleryActivity;


public class MainActivity extends ActionBarActivity
{


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView welcome = (ImageView) findViewById(R.id.welcome);
        welcome.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), FoodieGalleryActivity.class);
                startActivity(intent);
            }
        });
    }


}
