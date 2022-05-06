package hcmute.edu.vn.caodinhsyvy_19110143.foody2.fragment;

import android.annotation.SuppressLint;
//import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import hcmute.edu.vn.caodinhsyvy_19110143.foody2.R;
import hcmute.edu.vn.caodinhsyvy_19110143.foody2.card.DishButtonCard;

public class AccountFragment extends Fragment {
    private View view;

    public LinearLayout favoriteContainerLayout;

    public void mapping() {
        favoriteContainerLayout = view.findViewById(R.id.accountFragment_favoriteContainerLayout);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_account, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mapping();
        setEvent();

        setupFavorite();
    }

    private void setEvent() {

    }

    private void setupFavorite() {
        favoriteContainerLayout.removeAllViews();
        for (int i=0; i<5; i++) {
            DishButtonCard dishButtonCard = new DishButtonCard(getActivity());
            favoriteContainerLayout.addView(dishButtonCard.getView());
        }
    }
}
