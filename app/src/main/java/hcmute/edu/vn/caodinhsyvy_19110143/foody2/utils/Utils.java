package hcmute.edu.vn.caodinhsyvy_19110143.foody2.utils;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Parcelable;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;

import hcmute.edu.vn.caodinhsyvy_19110143.foody2.DishDetailActivity;
import hcmute.edu.vn.caodinhsyvy_19110143.foody2.card.DishCard;
import hcmute.edu.vn.caodinhsyvy_19110143.foody2.database.DBManager;
import hcmute.edu.vn.caodinhsyvy_19110143.foody2.entity.DishEntity;

public class Utils {
    public static void setUpPopularDishes(ViewGroup container, DBManager dbManager, Context context) {
        container.removeAllViews();

        Cursor dishData = dbManager.GetData("SELECT [picture], [name], [price], " +
                "[rating], [quantity_sold], [description] FROM [Dish];");
        int i=0;
        while (dishData.moveToNext() && i<19) {
            DishEntity dishEntity = new DishEntity();
            dishEntity.setName(dishData.getString(1));
            dishEntity.setPicture(dishData.getString(0));
            dishEntity.setPrice(dishData.getFloat(2));

            DishCard dishCard = new DishCard(context);
            int id = context.getResources().getIdentifier(dishEntity.getPicture(), "drawable", context.getPackageName());
            dishCard.img.setImageResource(id);
            dishCard.txtName.setText(dishEntity.getName());
            dishCard.txtPrice.setText("$" + dishEntity.getPrice().toString());
            dishCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dishEntity.setRating(dishData.getInt(3));
                    dishEntity.setQuantitySold(dishData.getInt(4));
                    dishEntity.setDescription(dishData.getString(5));

                    Intent intent = new Intent(context, DishDetailActivity.class);
                    intent.putExtra("dishEntity", dishEntity);
                    context.startActivity(intent);
                }
            });
            container.addView(dishCard.getView());

            i++;
        }
    }


    public static void setupOtherDishes(ViewGroup container, DBManager dbManager, Context context) {
        container.removeAllViews();

        Cursor dishData = dbManager.GetData("SELECT [picture], [name], [price] FROM [Dish];");
        for (int i=0; i<15; i++)
            dishData.moveToNext();

        int i=0;
        while (dishData.moveToNext() && i<10) {
            String picName = dishData.getString(0);
            String name = dishData.getString(1);
            String price = dishData.getString(2);

            DishCard dishCard = new DishCard(context);
            int id = context.getResources().getIdentifier(picName, "drawable", context.getPackageName());
            dishCard.img.setImageResource(id);
            dishCard.txtName.setText(name);
            dishCard.txtPrice.setText("$" + price);
            dishCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DishDetailActivity.class);
                    context.startActivity(intent);
                }
            });
            container.addView(dishCard.getView());

            i++;
        }

    }
}
