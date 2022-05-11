package hcmute.edu.vn.caodinhsyvy_19110143.foody2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import hcmute.edu.vn.caodinhsyvy_19110143.foody2.base.BaseData;
import hcmute.edu.vn.caodinhsyvy_19110143.foody2.database.DBManager;
import hcmute.edu.vn.caodinhsyvy_19110143.foody2.entity.DishEntity;
import hcmute.edu.vn.caodinhsyvy_19110143.foody2.entity.RestaurantEntity;
import hcmute.edu.vn.caodinhsyvy_19110143.foody2.utils.Utils;

public class DishDetailActivity extends AppCompatActivity {

    TextView txtBuy, txtDishName, txtRating, txtQuantitySold, txtDescription, txtRestaurantName;
    ImageView imgReturn, imgDish, imgFavorite, imgLocation;

    private DBManager dbManager;
    private DishEntity dishEntity;
    private RestaurantEntity restaurantEntity;

    private void mapping() {
        txtBuy = findViewById(R.id.dishDetailActivity_txtBuy);
        imgReturn = findViewById(R.id.dishDetailActivity_imgReturn);
        imgLocation = findViewById(R.id.dishDetailActivity_imgLocation);
        txtDishName = findViewById(R.id.dishDetailActivity_txtDishName);
        txtRating = findViewById(R.id.dishDetailActivity_txtRating);
        txtQuantitySold = findViewById(R.id.dishDetailActivity_txtQuantitySold);
        txtDescription = findViewById(R.id.dishDetailActivity_txtDescription);
        imgDish = findViewById(R.id.dishDetailActivity_dishImg);
        imgFavorite = findViewById(R.id.dishDetailActivity_imgFavorite);
        dbManager = new DBManager(this);
        txtRestaurantName = findViewById(R.id.dishDetailActivity_txtRestaurantName);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_detail);
        mapping();

        setUpInitData();
        setEventListener();
    }

    private void setUpInitData() {
        DishEntity dishEntity = (DishEntity) getIntent().getSerializableExtra("dishEntity");
        this.dishEntity = dishEntity;
        int id = getResources().getIdentifier(dishEntity.getPicture(), "drawable", getPackageName());
        imgDish.setImageResource(id);
        txtDishName.setText(dishEntity.getName());
        txtRating.setText(dishEntity.getRating().toString() + " Rating");
        txtQuantitySold.setText(dishEntity.getQuantitySold().toString() + "+ Over");
        txtDescription.setText(dishEntity.getDescription() + dishEntity.getDescription() + dishEntity.getDescription() + dishEntity.getDescription());

        // get restaurant
        Cursor resCursor = dbManager.GetData("SELECT * FROM [Restaurant] " +
                "WHERE [id] = " + dishEntity.getRestaurantId().toString());

        if (resCursor.moveToNext())
            restaurantEntity = Utils.restaurantMapping(resCursor);
        txtRestaurantName.setText(restaurantEntity.getName());

    }

    private void setEventListener() {
        txtBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DishDetailActivity.this, ConfirmOrderActivity.class);
                intent.putExtra("dishEntity", dishEntity);
                startActivity(intent);
            }
        });

        imgReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        imgLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DishDetailActivity.this, RestaurantDetailActivity.class);
                intent.putExtra("restaurantEntity", restaurantEntity);
                startActivity(intent);
            }
        });

        txtRestaurantName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DishDetailActivity.this, RestaurantDetailActivity.class);
                intent.putExtra("restaurantEntity", restaurantEntity);
                startActivity(intent);
            }
        });

        imgFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // check if dish exists already in favorite dish list of user
                Cursor checkExistingDishCursor = dbManager.GetData("SELECT * FROM [Favorite] " +
                        "WHERE [email] = '" + BaseData.userEntity.getEmail() + "' AND [dish_id] = " + dishEntity.getId());

                if (checkExistingDishCursor.moveToNext()) {
                    Toast.makeText(DishDetailActivity.this, "This dish has existed in your favorite list", Toast.LENGTH_SHORT).show();
                    return;
                }

                String query = "INSERT INTO [Favorite] VALUES ('" + BaseData.userEntity.getEmail() + "', " + dishEntity.getId() + ")";
                dbManager.QueryData(query);
                Toast.makeText(DishDetailActivity.this, "Add to favorite list successfully!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}