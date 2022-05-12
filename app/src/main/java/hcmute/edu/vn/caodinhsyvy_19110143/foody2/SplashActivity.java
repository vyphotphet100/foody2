package hcmute.edu.vn.caodinhsyvy_19110143.foody2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import hcmute.edu.vn.caodinhsyvy_19110143.foody2.database.DBManager;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//        DBManager dbManager = new DBManager(this, "N4_Foody.sqlite", null, 1);
//
//        Cursor dataBaiHoc = dbManager.GetData("SELECT * FROM [User]");
//        while(dataBaiHoc.moveToNext()) {
//            String ten = dataBaiHoc.getString(0);
//            Toast.makeText(this, ten, Toast.LENGTH_SHORT).show();
//        }

        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashActivity.this, SignInActivity.class);
                startActivity(intent);
                finish();
            }
        },2000);
    }
}