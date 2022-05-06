package hcmute.edu.vn.caodinhsyvy_19110143.foody2.card;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import hcmute.edu.vn.caodinhsyvy_19110143.foody2.DishDetailActivity;
import hcmute.edu.vn.caodinhsyvy_19110143.foody2.OrderSuccessActivity;
import hcmute.edu.vn.caodinhsyvy_19110143.foody2.R;

public class PlaceMyOrderCard extends BaseCard{
    private int layout = R.layout.card_place_my_order;
    private View view;
    private Context context;

    TextView txtPlaceMyOrder;

    @Override
    protected void mapping() {
        txtPlaceMyOrder = view.findViewById(R.id.placeMyOrderCard_txtPlaceMyOrder);
    }

    public PlaceMyOrderCard(Context context) {
        this.context = context;
        view = View.inflate(this.context, layout, null);
        onCreate();
    }

    @Override
    public View getView() {
        return this.view;
    }

    @Override
    protected void setListenerEvent() {

        txtPlaceMyOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, OrderSuccessActivity.class);
                context.startActivity(intent);
                ((Activity)context).finish();
            }
        });

    }
}
