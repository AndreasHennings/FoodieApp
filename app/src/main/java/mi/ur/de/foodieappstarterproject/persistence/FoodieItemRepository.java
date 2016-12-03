package mi.ur.de.foodieappstarterproject.persistence;

import java.util.ArrayList;

import mi.ur.de.foodieappstarterproject.domain.FoodieItem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by Andreas Hennings
 * This class creates and interacts with a SQL-Database
 */


public class FoodieItemRepository
{
    private static final String DATABASE_NAME = "foodieDataBase.db";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_TABLE = "foodieitems";

    //There are four pieces of information we want to store
    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_RATING = "rating";


    private FIDBOH dbHelper;

    private SQLiteDatabase db;

    public FoodieItemRepository(Context context)
    {
        dbHelper = new FIDBOH(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public void open()
    {
        try
        {
            db = dbHelper.getWritableDatabase();
        }
        catch (SQLException e)
        {
            db = dbHelper.getReadableDatabase();
        }
    }

    public void close()
    {
        db.close();
    }


    public long addFoodieItem(FoodieItem foodieItem)
    {
        ContentValues content = new ContentValues();
        content.put(KEY_NAME, foodieItem.getName());
        content.put(KEY_IMAGE, foodieItem.getImage());
        content.put(KEY_RATING, foodieItem.getRating());
        return db.insert(DATABASE_TABLE, null, content);
    }

    public int deleteFoodieItem(String foodieItemID)
    {
        String whereClause = KEY_IMAGE+"=?";
        db.delete(DATABASE_TABLE, whereClause, new String[]{foodieItemID});
        return 0;
    }

    public long updateRating(String foodieItemID, float rating)
    {

        String whereClause=KEY_IMAGE+"=?";
        ContentValues content = new ContentValues();
        content.put(KEY_RATING, rating);
        db.update(DATABASE_TABLE, content, whereClause, new String[]{foodieItemID});

        return 0;
    }

    public long updateTitle(String foodieItemID, String title)
    {
        String whereClause=KEY_IMAGE+"=?";
        ContentValues content = new ContentValues();
        content.put(KEY_NAME, title);
        db.update(DATABASE_TABLE, content, whereClause, new String[]{foodieItemID});
        return 0;
    }


    public ArrayList<FoodieItem> getAllFoodieItems()
    {
        ArrayList<FoodieItem> items = new ArrayList<FoodieItem>();
        Cursor cursor = db.query(DATABASE_TABLE, new String[] {KEY_ID, KEY_NAME, KEY_IMAGE, KEY_RATING}, null, null, null, null, null);

        if (cursor.moveToFirst())
        {
            do
            {
                String name = cursor.getString(1);
                String image = cursor.getString(2);
                float rating = cursor.getFloat(3);

                items.add(new FoodieItem(name, image, rating));
            }

            while (cursor.moveToNext());
        }
        return items;
    }


    private class FIDBOH extends SQLiteOpenHelper
    {
        private static final String DATABASE_CREATE = "create table "
                + DATABASE_TABLE
                + " ("
                + KEY_ID
                + " integer primary key autoincrement, "
                + KEY_NAME
                + " text, "
                + KEY_IMAGE
                + " text not null, "
                + KEY_RATING
                + " text);";


        public FIDBOH(Context c, String dbname, SQLiteDatabase.CursorFactory factory, int version)
        {
            super(c, dbname, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {

        }
    }
}


