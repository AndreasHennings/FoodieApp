package mi.ur.de.foodieappstarterproject.activities;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;


import java.util.ArrayList;
import java.util.Collections;

import mi.ur.de.foodieappstarterproject.adapters.CustomFoodieItemAdapter;
import mi.ur.de.foodieappstarterproject.domain.FoodieItem;

import mi.ur.de.foodieappstarterproject.R;
import mi.ur.de.foodieappstarterproject.domain.sortName;
import mi.ur.de.foodieappstarterproject.domain.sortRating;
import mi.ur.de.foodieappstarterproject.persistence.FoodieItemRepository;
import mi.ur.de.foodieappstarterproject.util.FoodieImageFileHelper;


public class FoodieGalleryActivity extends ActionBarActivity
{

    FoodieItemRepository myRepo;
    ArrayList<FoodieItem> allItems;
    CustomFoodieItemAdapter cfa;
    GridView gridView;
    AlertDialog.Builder adb;
    int choice;
    AlertDialog ad;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodie_gallery);
        setup();
    }


    @Override
    public void onPause()
    {
        myRepo.close();
        super.onPause();
    }


    public void onActivityResult()
    {
        setup();
    }


    private void setup()
    {

        allItems = new ArrayList<FoodieItem>();

        myRepo = new FoodieItemRepository(this);
        myRepo.open();
        allItems = myRepo.getAllFoodieItems();

        gridView=(GridView) findViewById(R.id.Grid);
        cfa = new CustomFoodieItemAdapter(this, allItems);
        gridView.setAdapter(cfa);
        adb = new AlertDialog.Builder(this);
        setupadb();


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {


                Intent showItemDetail = new Intent(FoodieGalleryActivity.this, FoodieItemDetailActivity.class);
                String name = allItems.get(i).getName();
                String image = allItems.get(i).getImage();
                float rating = allItems.get(i).getRating();
                showItemDetail.putExtra("name", name);
                showItemDetail.putExtra("image", image);
                showItemDetail.putExtra("rating", rating);
                myRepo.close();
                startActivityForResult(showItemDetail, 0);

            }
        });



        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int a, long l)
            {
                choice=a;
                ad.show();


                return true;
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        super.onCreateOptionsMenu(menu);

        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.menu_foodie_gallery, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        switch(id)
        {
            case R.id.cameraSymbol:

                takePicture();
                break;

            case R.id.sortAZ:

                Collections.sort(allItems, new sortName());
                cfa.notifyDataSetChanged();
                break;

            case R.id.sortRating:

                Collections.sort(allItems, new sortRating());
                cfa.notifyDataSetChanged();
                break;

        }


        return super.onOptionsItemSelected(item);
    }

    private void takePicture ()
    {
        Uri fileUri = FoodieImageFileHelper.getOutputImageFileURL() ; // create a file to save the image
        grabImageFromCamera(fileUri) ;
        persistFoodieMetadata(fileUri) ;
        cfa.notifyDataSetChanged();
    }

    private void grabImageFromCamera ( Uri fileUri )
    {
        Intent takeFoodieImage = new Intent( MediaStore.ACTION_IMAGE_CAPTURE ) ;
        takeFoodieImage.putExtra(MediaStore.EXTRA_OUTPUT, fileUri) ; // set the image file name
        takeFoodieImage.putExtra("fileName", fileUri.getPath()) ;
        startActivityForResult(takeFoodieImage, 0) ;
    }

    private void persistFoodieMetadata(Uri fileUri)
    {
        FoodieItem newItem = new FoodieItem("", fileUri.getPath(), 0);
        long result = myRepo.addFoodieItem(newItem);
    }

    private void setupadb()
    {
        adb.setTitle("Erase this item?");
        adb.setPositiveButton("Erase", new Dialog.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                FoodieItem fi = allItems.get(choice);
                String image = fi.getImage();
                int result = myRepo.deleteFoodieItem(image);
                allItems.remove(choice);
                cfa.notifyDataSetChanged();

            }
        });

        adb.setNegativeButton("Cancel", new Dialog.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {

            }
        });



        ad = adb.create();
    }


}
