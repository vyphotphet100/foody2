package hcmute.edu.vn.caodinhsyvy_19110143.foody2.card;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import hcmute.edu.vn.caodinhsyvy_19110143.foody2.DishDetailActivity;
import hcmute.edu.vn.caodinhsyvy_19110143.foody2.MainActivity;
import hcmute.edu.vn.caodinhsyvy_19110143.foody2.R;
import hcmute.edu.vn.caodinhsyvy_19110143.foody2.database.DBManager;
import hcmute.edu.vn.caodinhsyvy_19110143.foody2.utils.Utils;

public class WelcomeCard extends BaseCard{
    private DBManager dbManager;

    private WelcomeCard welcomeCard;
    private View view;
    private Context context;

    public TextView txtViewMoreNearRest, txtViewPopularDishes, txtViewMoreOthers;
    public FrameLayout containerHomeFrame;
    public LinearLayout popularDishesContainerLayout, otherDishesContainerLayout, nearestRestaurantContainerLayout;


    @Override
    protected void mapping() {
        dbManager = new DBManager(this.context);
        txtViewMoreNearRest = view.findViewById(R.id.txtViewMoreNearRest);
        txtViewPopularDishes = view.findViewById(R.id.txtViewPopularDishes);
        txtViewMoreOthers = view.findViewById(R.id.txtViewMoreOthers);
        containerHomeFrame = ((MainActivity) context).findViewById(R.id.containerHomeFrame);
        popularDishesContainerLayout = view.findViewById(R.id.welcomeCard_popularDishesContainerLayout);
        otherDishesContainerLayout = view.findViewById(R.id.welcomeCard_otherDishesContainerLayout);
        nearestRestaurantContainerLayout = view.findViewById(R.id.welcomeCard_nearestRestaurantContainerLayout);
    }

    public WelcomeCard(Context context) {
        this.context = context;
        welcomeCard = this;
        view = View.inflate(this.context, R.layout.card_welcome, null);
        onCreate();
    }

    @Override
    public View getView(){
        return this.view;
    }

    @Override
    protected void setListenerEvent() {
        txtViewMoreNearRest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ScrollView scrollView = ((MainActivity) context).findViewById(R.id.homeFragment_scrollView);
                scrollView.setScrollY(0);

                NearestRestaurantCard nearestRestaurantCard = new NearestRestaurantCard(context, welcomeCard);
                containerHomeFrame.removeAllViews();
                containerHomeFrame.addView(nearestRestaurantCard.getView());
            }
        });

        txtViewPopularDishes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ScrollView scrollView = ((MainActivity) context).findViewById(R.id.homeFragment_scrollView);
                scrollView.setScrollY(0);

                PopularDishesCard popularDishesCard = new PopularDishesCard(context, welcomeCard);
                containerHomeFrame.removeAllViews();
                containerHomeFrame.addView(popularDishesCard.getView());
            }
        });

        txtViewMoreOthers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ScrollView scrollView = ((MainActivity) context).findViewById(R.id.homeFragment_scrollView);
                scrollView.setScrollY(0);

                OtherDishesCard otherDishesCard = new OtherDishesCard(context, welcomeCard);
                containerHomeFrame.removeAllViews();
                containerHomeFrame.addView(otherDishesCard.getView());
            }
        });
    }

    @Override
    protected void onCreate() {
        super.onCreate();

        setupNearestRestaurant();
        Utils.setUpPopularDishes(popularDishesContainerLayout, dbManager, this.context);
        Utils.setupOtherDishes(otherDishesContainerLayout, dbManager, this.context);
    }

    private void setupNearestRestaurant() {
        nearestRestaurantContainerLayout.removeAllViews();

        Cursor restaurantData = dbManager.GetData("SELECT [picture], [name], [estimated_time] FROM [Restaurant];");
        int i=0;
        while (restaurantData.moveToNext() && i<9) {
            String name = restaurantData.getString(1);
            String estimatedTime = restaurantData.getString(2);
            String picName = restaurantData.getString(0);
            RestaurantCard restaurantCard = new RestaurantCard(context);

            int id = this.context.getResources().getIdentifier(picName, "drawable", this.context.getPackageName());
            restaurantCard.img.setImageResource(id);

            restaurantCard.txtName.setText(name);
            restaurantCard.txtEstimatedTime.setText(estimatedTime + " mins");
            nearestRestaurantContainerLayout.addView(restaurantCard.getView());

            i++;
        }

    }

    private void setupOtherDishes() {
        otherDishesContainerLayout.removeAllViews();

        Cursor dishData = dbManager.GetData("SELECT [picture], [name], [price] FROM [Dish];");
        for (int i=0; i<15; i++)
            dishData.moveToNext();

        int i=0;
        while (dishData.moveToNext() && i<10) {
            String picName = dishData.getString(0);
            String name = dishData.getString(1);
            String price = dishData.getString(2);

            DishCard dishCard = new DishCard(context);
            int id = this.context.getResources().getIdentifier(picName, "drawable", this.context.getPackageName());
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
            otherDishesContainerLayout.addView(dishCard.getView());

            i++;
        }

    }
}
