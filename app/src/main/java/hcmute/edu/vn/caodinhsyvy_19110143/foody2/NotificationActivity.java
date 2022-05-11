package hcmute.edu.vn.caodinhsyvy_19110143.foody2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import hcmute.edu.vn.caodinhsyvy_19110143.foody2.base.BaseData;
import hcmute.edu.vn.caodinhsyvy_19110143.foody2.card.NotificationCard;
import hcmute.edu.vn.caodinhsyvy_19110143.foody2.database.DBManager;
import hcmute.edu.vn.caodinhsyvy_19110143.foody2.entity.NotificationEntity;
import hcmute.edu.vn.caodinhsyvy_19110143.foody2.utils.Utils;

public class NotificationActivity extends AppCompatActivity {

    public ImageView imgReturn;
    public LinearLayout container;
    private DBManager dbManager;

    private void mapping() {
        imgReturn = findViewById(R.id.notificationActivity_imgReturn);
        container = findViewById(R.id.notificationActivity_container);
        dbManager = new DBManager(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        mapping();
        setEventListener();
        setUpInitData();
    }

    private void setEventListener() {
        imgReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setUpInitData() {
        container.removeAllViews();
        Cursor notiCursor = dbManager.GetData("SELECT * FROM [Notification] " +
                "WHERE [email] = '"+ BaseData.userEntity.getEmail() +"'");

        if (!notiCursor.moveToNext()) {
            TextView txtNoti = new TextView(this);
            txtNoti.setText("You do not have any notification!");
            txtNoti.setPadding(20, 20, 20, 20);
            container.addView(txtNoti);
        }
        else {
            do {
                NotificationEntity notificationEntity = Utils.notificationMapping(notiCursor);
                NotificationCard notificationCard = new NotificationCard(this);
                notificationCard.txtTitle.setText(notificationEntity.getTitle());
                notificationCard.txtContent.setText(notificationEntity.getContent());

                container.addView(notificationCard.getView(), 0);
            } while (notiCursor.moveToNext());
        }
    }
}