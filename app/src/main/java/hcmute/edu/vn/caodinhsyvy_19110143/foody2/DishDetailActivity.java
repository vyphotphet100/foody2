package hcmute.edu.vn.caodinhsyvy_19110143.foody2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import hcmute.edu.vn.caodinhsyvy_19110143.foody2.entity.DishEntity;

public class DishDetailActivity extends AppCompatActivity {

    TextView txtBuy, txtDishName, txtRating, txtQuantitySold, txtDescription;
    ImageView imgReturn, imgDish;

    private void mapping() {
        txtBuy = findViewById(R.id.dishDetailActivity_txtBuy);
        imgReturn = findViewById(R.id.dishDetailActivity_imgReturn);
        txtDishName = findViewById(R.id.dishDetailActivity_txtDishName);
        txtRating = findViewById(R.id.dishDetailActivity_txtRating);
        txtQuantitySold = findViewById(R.id.dishDetailActivity_txtQuantitySold);
        txtDescription = findViewById(R.id.dishDetailActivity_txtDescription);
        imgDish = findViewById(R.id.dishDetailActivity_dishImg);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_detail);
        mapping();

        setEventListener();
        setUpInitData();
    }

    private void setUpInitData() {
        DishEntity dishEntity = (DishEntity) getIntent().getSerializableExtra("dishEntity");
        int id = getResources().getIdentifier(dishEntity.getPicture(), "drawable", getPackageName());
        imgDish.setImageResource(id);
        txtDishName.setText(dishEntity.getName());
        txtRating.setText(dishEntity.getRating().toString() + " Rating");
        txtQuantitySold.setText(dishEntity.getQuantitySold().toString() + "+ Over");
        txtDescription.setText(dishEntity.getDescription() + dishEntity.getDescription() + dishEntity.getDescription() + dishEntity.getDescription());
    }

    private void setEventListener() {
        txtBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DishDetailActivity.this, ConfirmOrderActivity.class);
                startActivity(intent);
            }
        });

        imgReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}