package hcmute.edu.vn.caodinhsyvy_19110143.foody2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import hcmute.edu.vn.caodinhsyvy_19110143.foody2.card.DishCard;
import hcmute.edu.vn.caodinhsyvy_19110143.foody2.database.DBManager;
import hcmute.edu.vn.caodinhsyvy_19110143.foody2.entity.DishEntity;
import hcmute.edu.vn.caodinhsyvy_19110143.foody2.entity.RestaurantEntity;
import hcmute.edu.vn.caodinhsyvy_19110143.foody2.utils.Utils;

public class RestaurantDetailActivity extends AppCompatActivity {
    DBManager dbManager;

    public ImageView imgRestaurant, imgReturn;
    public TextView txtRating, txtAddress, txtName;
    public GridLayout gvContainer;

    private void mapping() {
        imgReturn = findViewById(R.id.restaurantDetailActivity_imgReturn);
        imgRestaurant = findViewById(R.id.restaurantDetailActivity_restaurantImg);
        txtName = findViewById(R.id.restaurantDetailActivity_txtRestaurantName);
        txtRating = findViewById(R.id.restaurantDetailActivity_txtRating);
        txtAddress = findViewById(R.id.restaurantDetailActivity_txtAddress);
        gvContainer = findViewById(R.id.restaurantDetailActivity_gvContainer);
        dbManager = new DBManager(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);
        mapping();
        setUpInitData();
        setEventListener();

    }

    private void setEventListener() {
        imgReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setUpInitData() {
        RestaurantEntity restaurantEntity = (RestaurantEntity) getIntent().getSerializableExtra("restaurantEntity");
        int id = getResources().getIdentifier(restaurantEntity.getPicture(), "drawable", getPackageName());
        imgRestaurant.setImageResource(id);
        txtName.setText(restaurantEntity.getName());
        txtRating.setText(restaurantEntity.getRating() + " Rating");
        txtAddress.setText(restaurantEntity.getAddress());
        addDishData(restaurantEntity.getId());
    }

    private void addDishData(Integer restaurantId) {
        gvContainer.removeAllViews();
        List<DishEntity> dishEntities = Utils.getDishesByRestaurantId(restaurantId, dbManager);

        for (DishEntity dishEntity: dishEntities) {
            DishCard dishCard = new DishCard(this);
            int id = this.getResources().getIdentifier(dishEntity.getPicture(), "drawable", this.getPackageName());
            dishCard.img.setImageResource(id);
            dishCard.txtName.setText(dishEntity.getName());
            dishCard.txtPrice.setText("$" + dishEntity.getPrice().toString());
            dishCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(RestaurantDetailActivity.this, DishDetailActivity.class);
                    intent.putExtra("dishEntity", dishEntity);
                    startActivity(intent);
                }
            });
            gvContainer.addView(dishCard.getView());
        }


    }



}