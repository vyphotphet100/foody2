package hcmute.edu.vn.caodinhsyvy_19110143.foody2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import hcmute.edu.vn.caodinhsyvy_19110143.foody2.card.PlaceMyOrderCard;
import hcmute.edu.vn.caodinhsyvy_19110143.foody2.dialog.EditPaymentMethodDialog;

public class ConfirmOrderActivity extends AppCompatActivity {

    public FrameLayout placeMyOrderContainerFrame;
    public PlaceMyOrderCard placeMyOrderCard;
    public ImageView imgReturn;
    public TextView txtEditDeliverTo, txtEditPaymentMethod, txtDeliverTo;

    private void mapping() {
        placeMyOrderContainerFrame = findViewById(R.id.confirmOrderActivity_placeMyOrderContainer);
        placeMyOrderCard = new PlaceMyOrderCard(ConfirmOrderActivity.this);
        imgReturn = findViewById(R.id.confirmOrder_imgReturn);
        txtEditDeliverTo = findViewById(R.id.confirmOrderActivity_txtEditDeliverTo);
        txtDeliverTo = findViewById(R.id.confirmOrderActivity_txtDeliverTo);
        txtEditPaymentMethod = findViewById(R.id.confirmOrderActivity_txtEditPaymentMethod);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);
        mapping();

        setCard();
        setEvent();
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
            }
        });
//        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setBackgroundColor(Color.parseColor("#DFDEDE"));
//        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                dialog.dismiss();
//            }
//        });
        dialog.show();
    }

    private void openEditPaymentMethodDialog() {
        Dialog dialog = (new AlertDialog.Builder(this)).create();
//        LinearLayout dialogLayout = (LinearLayout) View.inflate(this, R.layout.dialog_edit_payment_method, null);
//        dialog.setView(dialogLayout);
//        dialog.show();

        EditPaymentMethodDialog editPaymentMethodDialog = new EditPaymentMethodDialog(ConfirmOrderActivity.this, (AlertDialog) dialog);

    }

}