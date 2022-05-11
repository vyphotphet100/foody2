package hcmute.edu.vn.caodinhsyvy_19110143.foody2.fragment;

import android.annotation.SuppressLint;
//import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import hcmute.edu.vn.caodinhsyvy_19110143.foody2.ConfirmOrderActivity;
import hcmute.edu.vn.caodinhsyvy_19110143.foody2.DishDetailActivity;
import hcmute.edu.vn.caodinhsyvy_19110143.foody2.R;
import hcmute.edu.vn.caodinhsyvy_19110143.foody2.SignInActivity;
import hcmute.edu.vn.caodinhsyvy_19110143.foody2.base.BaseData;
import hcmute.edu.vn.caodinhsyvy_19110143.foody2.card.DishButtonCard;
import hcmute.edu.vn.caodinhsyvy_19110143.foody2.database.DBManager;
import hcmute.edu.vn.caodinhsyvy_19110143.foody2.entity.DishEntity;
import hcmute.edu.vn.caodinhsyvy_19110143.foody2.utils.Utils;

public class AccountFragment extends Fragment {
    private View view;
    private DBManager dbManager;
    private Context context;

    public LinearLayout favoriteContainerLayout;
    public ImageView imgAvatar, imgEditName, imgLogout;
    public TextView txtName, txtEmail;

    public void mapping() {
        favoriteContainerLayout = view.findViewById(R.id.accountFragment_favoriteContainerLayout);
        imgAvatar = view.findViewById(R.id.accountFragment_imgAvatar);
        txtEmail = view.findViewById(R.id.accountFragment_txtEmail);
        txtName = view.findViewById(R.id.accountFragment_txtName);
        dbManager = new DBManager(getActivity());
        context = getActivity();
        imgEditName = view.findViewById(R.id.accountFragment_imgEditName);
        imgLogout = view.findViewById(R.id.accountFragment_imgLogout);
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

        setUpInfo();
        setupFavorite();
    }

    private void setEvent() {

        imgEditName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEditAccountName();
            }
        });

        imgLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BaseData.userEntity = null;
                Intent intent = new Intent(getActivity(), SignInActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

    }

    private void setUpInfo() {
        Integer id = null;
        try {
            id = getActivity().getResources().getIdentifier(BaseData.userEntity.getAvatar(), "drawable", getActivity().getPackageName());
        } catch (Exception ex) {
        }

        if (id != null)
            imgAvatar.setImageResource(id);
        txtName.setText(BaseData.userEntity.getFullname());
        txtEmail.setText(BaseData.userEntity.getEmail());
    }

    private void setupFavorite() {
        favoriteContainerLayout.removeAllViews();
        Cursor dishCursor = dbManager.GetData("SELECT * " +
                "FROM [Dish] JOIN " +
                "    (SELECT [dish_id] FROM [Favorite] WHERE [email] = '" + BaseData.userEntity.getEmail() + "') F " +
                "    ON  F.dish_id = [Dish].id;");

        if (!dishCursor.moveToNext()) {
            TextView txtNoti = new TextView(getActivity());
            txtNoti.setText("You didn't add any dish to your favorite list");
            favoriteContainerLayout.addView(txtNoti);
        } else {
            do {
                DishEntity dishEntity = Utils.dishMapping(dishCursor);
                DishButtonCard dishButtonCard = new DishButtonCard(getActivity());
                int id = getActivity().getResources().getIdentifier(dishEntity.getPicture(), "drawable", getActivity().getPackageName());
                dishButtonCard.img.setImageResource(id);
                dishButtonCard.txtDishName.setText(dishEntity.getName());
                dishButtonCard.txtDishPrice.setText("$" + dishEntity.getPrice());
                dishButtonCard.txtBtn.setText("Buy");
                dishButtonCard.txtBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, DishDetailActivity.class);
                        intent.putExtra("dishEntity", dishEntity);
                        context.startActivity(intent);
                    }
                });

                favoriteContainerLayout.addView(dishButtonCard.getView(), 0);
            } while (dishCursor.moveToNext());
        }


    }

    private void openEditAccountName() {
        AlertDialog dialog = (new AlertDialog.Builder(getActivity())).create();
        ConstraintLayout dialogLayout = (ConstraintLayout) View.inflate(getActivity(), R.layout.dialog_edit_account_name, null);
        EditText edtTxtAccountName = dialogLayout.findViewById(R.id.dialogEditAccountName_edtTxtAccountName);
        edtTxtAccountName.setText(txtName.getText());
        dialog.setView(dialogLayout);
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                txtName.setText(edtTxtAccountName.getText());
                dialog.dismiss();

                dbManager.QueryData("UPDATE [User] SET [fullname] = '" + txtName.getText().toString() + "' " +
                        "WHERE [email] = '" + BaseData.userEntity.getEmail() + "'");

                Cursor userCursor = dbManager.GetData("SELECT * FROM [User] " +
                        "WHERE [email] = '" + BaseData.userEntity.getEmail() + "'");

                if (userCursor.moveToNext())
                    BaseData.userEntity = Utils.userMapping(userCursor);
            }
        });
        dialog.show();
    }
}
