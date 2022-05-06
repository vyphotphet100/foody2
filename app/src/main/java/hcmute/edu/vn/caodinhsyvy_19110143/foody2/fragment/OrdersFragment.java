package hcmute.edu.vn.caodinhsyvy_19110143.foody2.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import hcmute.edu.vn.caodinhsyvy_19110143.foody2.OrderDetailActivity;
import hcmute.edu.vn.caodinhsyvy_19110143.foody2.R;
import hcmute.edu.vn.caodinhsyvy_19110143.foody2.card.DishButtonCard;

public class OrdersFragment extends Fragment {
    private View view;

    public LinearLayout container;

    public void mapping() {
        container = view.findViewById(R.id.orderFragment_container);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_orders, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        mapping();
        setupFavorite();
    }

    private void setEvent() {

    }

    private void setupFavorite() {
        container.removeAllViews();
        for (int i=0; i<10; i++) {
            DishButtonCard dishButtonCard = new DishButtonCard(getActivity());
            dishButtonCard.txtBtn.setText("Detail");
            dishButtonCard.txtBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), OrderDetailActivity.class);
                    startActivity(intent);
                }
            });
            container.addView(dishButtonCard.getView());
        }
    }
}
