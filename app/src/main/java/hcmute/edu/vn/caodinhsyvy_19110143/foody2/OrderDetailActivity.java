package hcmute.edu.vn.caodinhsyvy_19110143.foody2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class OrderDetailActivity extends AppCompatActivity {
    ImageView imgReturn;

    private void mapping() {
        imgReturn = findViewById(R.id.orderDetailActivity_imgReturn);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        mapping();
        setEventListener();
    }

    private void setEventListener() {
        imgReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}