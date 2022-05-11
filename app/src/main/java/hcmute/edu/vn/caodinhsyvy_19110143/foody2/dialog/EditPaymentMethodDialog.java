package hcmute.edu.vn.caodinhsyvy_19110143.foody2.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import hcmute.edu.vn.caodinhsyvy_19110143.foody2.R;

public class EditPaymentMethodDialog {
    private int layout = R.layout.dialog_edit_payment_method;
    private View view;
    private Context context;
    private AlertDialog dialog;

    public CardView crdCOD, crdPaypal;


    protected void mapping() {
        crdCOD = view.findViewById(R.id.editPaymentMethodDialog_crdCOD);
//        crdPaypal = view.findViewById(R.id.editPaymentMethodDialog_crdPaypal);
    }

    public EditPaymentMethodDialog(Context context, AlertDialog dialog) {
        this.context = context;
        this.dialog = dialog;
        view = View.inflate(this.context, layout, null);
        dialog.setView(view);
        onCreate();
    }

    private void onCreate() {
        mapping();
        setListenerEvent();
        dialog.show();
    }

    public View getView() {
        return this.view;
    }

    protected void setListenerEvent() {

    }
}
