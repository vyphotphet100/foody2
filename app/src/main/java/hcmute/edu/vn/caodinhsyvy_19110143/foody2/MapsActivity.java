package hcmute.edu.vn.caodinhsyvy_19110143.foody2;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.caodinhsyvy_19110143.foody2.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback{

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    SupportMapFragment mapFragment;

    EditText txtSearch;
    Button btnSave, btnFind;

    private void mapping() {
        txtSearch = findViewById(R.id.mapsActivity_edtSearchLocation);
        btnFind = findViewById(R.id.mapsActivity_btnFind);
        btnSave = findViewById(R.id.mapsActivity_btnSave);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapping();

        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                geoLocate();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("deliverTo", txtSearch.getText().toString());
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });

        String location = (String) getIntent().getSerializableExtra("location");
        txtSearch.setText(location);
        btnFind.callOnClick();
    }

    private void geoLocate(){
        Log.d("Notification", "geoLocate: geolocating");

        String searchString = txtSearch.getText().toString();

        Geocoder geocoder = new Geocoder(MapsActivity.this);
        List<Address> list = new ArrayList<>();
        try{
            list = geocoder.getFromLocationName(searchString, 1);
        }catch (IOException e){
            Log.e("Notification", "geoLocate: IOException: " + e.getMessage() );
        }

        if(list.size() > 0){
            Address address = list.get(0);

            Log.d("Notification", "geoLocate: found a location: " + address.toString());
            mapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(@NonNull GoogleMap googleMap) {
                    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());

                    MarkerOptions options = new MarkerOptions().position(latLng)
                            .title("I am here");

                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                    googleMap.addMarker(options);
                }
            });

        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
    }
}