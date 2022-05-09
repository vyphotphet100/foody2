package hcmute.edu.vn.caodinhsyvy_19110143.foody2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import hcmute.edu.vn.caodinhsyvy_19110143.foody2.base.BaseData;
import hcmute.edu.vn.caodinhsyvy_19110143.foody2.card.PlaceMyOrderCard;
import hcmute.edu.vn.caodinhsyvy_19110143.foody2.database.DBManager;
import hcmute.edu.vn.caodinhsyvy_19110143.foody2.dialog.EditPaymentMethodDialog;
import hcmute.edu.vn.caodinhsyvy_19110143.foody2.entity.DishEntity;
import hcmute.edu.vn.caodinhsyvy_19110143.foody2.utils.Utils;

public class ConfirmOrderActivity extends AppCompatActivity {

    public FrameLayout placeMyOrderContainerFrame;
    public PlaceMyOrderCard placeMyOrderCard;
    public ImageView imgReturn;
    public TextView txtEditDeliverTo, txtEditPaymentMethod, txtDeliverTo;
    private DishEntity dishEntity;
    private DBManager dbManager;

    private void mapping() {
        placeMyOrderContainerFrame = findViewById(R.id.confirmOrderActivity_placeMyOrderContainer);
        placeMyOrderCard = new PlaceMyOrderCard(ConfirmOrderActivity.this);
        imgReturn = findViewById(R.id.confirmOrder_imgReturn);
        txtEditDeliverTo = findViewById(R.id.confirmOrderActivity_txtEditDeliverTo);
        txtDeliverTo = findViewById(R.id.confirmOrderActivity_txtDeliverTo);
        txtEditPaymentMethod = findViewById(R.id.confirmOrderActivity_txtEditPaymentMethod);
        dishEntity = (DishEntity) getIntent().getSerializableExtra("dishEntity");
        dbManager = new DBManager(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);
        mapping();

        setCard();
        setEvent();
        setUpInitData();
    }

    private void setCard() {
        placeMyOrderContainerFrame.removeAllViews();
        placeMyOrderContainerFrame.addView(placeMyOrderCard.getView());
    }

    private void setEvent() {
        imgReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        txtEditDeliverTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEditDeliverToDialog();
            }
        });

        txtEditPaymentMethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEditPaymentMethodDialog();
            }
        });

        placeMyOrderCard.txtPlaceMyOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Integer quantity = Integer.valueOf(placeMyOrderCard.txtQuantity.getText().toString());
                    placeMyOrderCard.txtQuantity.setText(quantity.toString());

                    Float subPrice = quantity*dishEntity.getPrice();
                    Float total = subPrice+2;
                    dbManager.QueryData("INSERT INTO [Order] VALUES " +
                            "(null, '"+txtDeliverTo.getText().toString()+"', 'COD', "+dishEntity.getId()+", '"+BaseData.userEntity.getEmail()+"', "+total.toString()+")");

                    Intent intent = new Intent(ConfirmOrderActivity.this, OrderSuccessActivity.class);
                    startActivity(intent);
                    finish();

                    // check if dish exists already in favorite dish list of user
                    Cursor checkExistingDishCursor = dbManager.GetData("SELECT * FROM [Favorite] " +
                            "WHERE [email] = '" + BaseData.userEntity.getEmail() + "' AND [dish_id] = " + dishEntity.getId());

                    if (!checkExistingDishCursor.moveToNext()) {
                        String query = "INSERT INTO [Favorite] VALUES ('" + BaseData.userEntity.getEmail() + "', " + dishEntity.getId() + ")";
                        dbManager.QueryData(query);
                    }

                    String query = "INSERT INTO [Notification] " +
                            "VALUES (null, '"+BaseData.userEntity.getEmail()+"', 'Success for "+dishEntity.getName()+"!', 'Your booking has been confirmed', 1)";
                    dbManager.QueryData(query);

                } catch (Exception ex) {
                    ex.printStackTrace();
                    Toast.makeText(ConfirmOrderActivity.this, "Something went wrong. Please check again!", Toast.LENGTH_LONG).show();
                }

            }
        });

        placeMyOrderCard.txtQuantityInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer quantity = Integer.valueOf(placeMyOrderCard.txtQuantity.getText().toString());
                quantity++;
                placeMyOrderCard.txtQuantity.setText(quantity.toString());

                Float subPrice = quantity*dishEntity.getPrice();
                placeMyOrderCard.txtSubTotalPrice.setText(subPrice + " $");
                placeMyOrderCard.txtTotalPrice.setText((subPrice + 2) + " $");
            }
        });

        placeMyOrderCard.txtQuantityDes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer quantity = Integer.valueOf(placeMyOrderCard.txtQuantity.getText().toString());
                if (quantity > 1)
                    quantity--;
                placeMyOrderCard.txtQuantity.setText(quantity.toString());

                Float subPrice = quantity*dishEntity.getPrice();
                placeMyOrderCard.txtSubTotalPrice.setText(subPrice + " $");
                placeMyOrderCard.txtTotalPrice.setText((subPrice + 2) + " $");
            }
        });
    }

    private void openEditDeliverToDialog() {
        AlertDialog dialog = (new AlertDialog.Builder(this)).create();
        ConstraintLayout dialogLayout = (ConstraintLayout) View.inflate(this, R.layout.dialog_edit_delivery_to, null);
        EditText edtTxtDeliverTo = dialogLayout.findViewById(R.id.dialogEditDeliveryTo_edtTxtDeliveryTo);
        edtTxtDeliverTo.setText(txtDeliverTo.getText());
        dialog.setView(dialogLayout);
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                txtDeliverTo.setText(edtTxtDeliverTo.getText());
                dialog.dismiss();

                dbManager.QueryData("UPDATE [User] SET [address] = '" + txtDeliverTo.getText().toString() + "' " +
                        "WHERE [email] = '" + BaseData.userEntity.getEmail() + "'");

                Cursor userCursor = dbManager.GetData("SELECT * FROM [User] " +
                        "WHERE [email] = '" + BaseData.userEntity.getEmail() + "'");

                if (userCursor.moveToNext())
                    BaseData.userEntity = Utils.userMapping(userCursor);
            }
        });
        dialog.show();
    }

    private void openEditPaymentMethodDialog() {
        Dialog dialog = (new AlertDialog.Builder(this)).create();
//        LinearLayout dialogLayout = (LinearLayout) View.inflate(this, R.layout.dialog_edit_payment_method, null);
//        dialog.setView(dialogLayout);
//        dialog.show();

        EditPaymentMethodDialog editPaymentMethodDialog = new EditPaymentMethodDialog(ConfirmOrderActivity.this, (AlertDialog) dialog);

    }

    private void setUpInitData() {
        txtDeliverTo.setText(BaseData.userEntity.getAddress());
        placeMyOrderCard.txtSubTotalPrice.setText(dishEntity.getPrice() + " $");
        placeMyOrderCard.txtTotalPrice.setText((dishEntity.getPrice() + 2) + " $");
    }
}