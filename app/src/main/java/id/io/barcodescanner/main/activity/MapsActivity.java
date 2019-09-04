package id.io.barcodescanner.main.activity;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

import com.example.barcodescanner.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import id.io.barcodescanner.main.request.SendAssetRequest;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private double latMarker, lngMarker;
    private GoogleMap mMap;
    private SendAssetRequest assetModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(latLng));
            }
        });
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                getAssetModel();
                latMarker = marker.getPosition().latitude;
                lngMarker = marker.getPosition().longitude;
                assetModel.setGeoLocation(latMarker + ", " + lngMarker);
                Intent intent = new Intent(MapsActivity.this, DetailInputActivity.class);
                intent.putExtra("newModel", assetModel);
                intent.putExtra("lat", latMarker);
                intent.putExtra("lng", lngMarker);
                intent.putExtra("flag", "fromMapsActivity");
                startActivity(intent);
                return true;
            }
        });
    }

    private void getAssetModel() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            assetModel = (SendAssetRequest) bundle.getSerializable("model");
        }
    }
}
