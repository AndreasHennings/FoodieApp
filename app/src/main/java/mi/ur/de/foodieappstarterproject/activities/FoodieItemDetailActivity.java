package mi.ur.de.foodieappstarterproject.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import mi.ur.de.foodieappstarterproject.R;
import mi.ur.de.foodieappstarterproject.persistence.FoodieItemRepository;
import mi.ur.de.foodieappstarterproject.util.FoodieImageFileHelper;

/**
 * Created by Andreas Hennings
 */
public class FoodieItemDetailActivity extends Activity


{
    ImageView iv;
    EditText tv;
    RatingBar rb;
    Button b;

    String name;
    String image;
    float rating;

    FoodieItemRepository myRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodie_item_detail);
        myRepo= new FoodieItemRepository(this);
        myRepo.open();

        getViews();
        getData();
        setViews();

    }

    private void setViews()
    {
        iv.setImageBitmap(FoodieImageFileHelper.getBitmap(image));
        tv.setText(name);
        rb.setRating(rating);
    }

    private void getViews()
    {
        iv = (ImageView) findViewById(R.id.imageView2);
        tv =(EditText) findViewById(R.id.textView);
        rb =(RatingBar) findViewById(R.id.ratingBar2);

        b = (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                myRepo.updateRating(image, rb.getRating());
                myRepo.updateTitle(image, tv.getText().toString());
                myRepo.close();

                Intent i = new Intent(FoodieItemDetailActivity.this, FoodieGalleryActivity.class);
                startActivity(i);

            }
        });


    }

    private void getData()
    {
        Bundle extras = getIntent().getExtras();
        name = extras.getString("name");
        image = extras.getString("image");
        rating = extras.getFloat("rating");
    }





}
