package hcmute.edu.vn.caodinhsyvy_19110143.foody2.card;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import hcmute.edu.vn.caodinhsyvy_19110143.foody2.R;

public class RestaurantCard extends BaseCard{
    private int layout = R.layout.card_restaurant;
    private View view;
    private Context context;

    public ImageView img;
    public TextView txtEstimatedTime, txtName;

    @Override
    protected void mapping() {
        img = view.findViewById(R.id.restaurantCard_img);
        txtName = view.findViewById(R.id.restaurantCard_txtName);
        txtEstimatedTime = view.findViewById(R.id.restaurantCard_txtEstimatedTime);
    }

    @Override
    public View getView() {
        return this.view;
    }

    @Override
    protected void setListenerEvent() {

    }

    public RestaurantCard(Context context) {
        this.context = context;
        view = View.inflate(this.context, layout, null);
        onCreate();
    }

    public void setOnClickListener(View.OnClickListener listener) {

    }
}
