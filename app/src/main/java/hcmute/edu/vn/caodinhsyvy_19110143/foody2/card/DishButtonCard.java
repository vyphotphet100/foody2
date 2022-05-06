package hcmute.edu.vn.caodinhsyvy_19110143.foody2.card;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import hcmute.edu.vn.caodinhsyvy_19110143.foody2.R;

public class DishButtonCard extends BaseCard{
    private int layout = R.layout.card_dish_button;
    private View view;
    private Context context;

    public TextView txtBtn;
    public ConstraintLayout container;

    @Override
    protected void mapping() {
        txtBtn = view.findViewById(R.id.dishButtonCard_txtBtn);
        container = view.findViewById(R.id.dishButtonCard_container);
    }

    public DishButtonCard(Context context) {
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

    }

    public void setOnClickListener(View.OnClickListener listener) {
        container.setOnClickListener(listener);
    }
}
