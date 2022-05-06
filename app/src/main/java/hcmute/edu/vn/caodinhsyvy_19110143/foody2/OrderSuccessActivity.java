package hcmute.edu.vn.caodinhsyvy_19110143.foody2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class OrderSuccessActivity extends AppCompatActivity {

    TextView txtOk;

    private void mapping() {
        txtOk = findViewById(R.id.orderSuccessActivity_txtOk);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_success);
        mapping();

        txtOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}