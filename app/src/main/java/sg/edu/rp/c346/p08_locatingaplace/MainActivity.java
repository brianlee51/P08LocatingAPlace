package sg.edu.rp.c346.p08_locatingaplace;

import android.Manifest;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {

//    Button btn1, btn2, btn3;
    Spinner spnLocation;
    private GoogleMap map;
    private Marker orchard;
    private Marker north;
    private Marker east;
    private LatLng poi_northHQ;
    private LatLng poi_Central;
    private LatLng poi_East;
    private LatLng poi_Singapore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        btn1 = findViewById(R.id.btn1);
//        btn2 = findViewById(R.id.btn2);
//        btn3 = findViewById(R.id.btn3);
        spnLocation = findViewById(R.id.spinner);

        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;
                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION);

                if (permissionCheck == PermissionChecker.PERMISSION_GRANTED) {
                    map.setMyLocationEnabled(true);
                } else {
                    Log.e("GMap - Permission", "GPS access has not been granted");
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                }
                poi_Singapore = new LatLng(1.290270, 103.851959);

                poi_Central = new LatLng(1.3016794, 103.8358879);
                orchard = map.addMarker(new
                        MarkerOptions()
                        .position(poi_Central)
                        .title("Central")
                        .snippet("Block 3A, Orchard Ave 3, 134542")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                poi_East = new LatLng(1.3497355, 103.9322365);
                east = map.addMarker(new
                        MarkerOptions()
                        .position(poi_East)
                        .title("East")
                        .snippet("Tampines Ave 3, 287788")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));


                poi_northHQ = new LatLng(1.451208, 103.7784246);
                north = map.addMarker(new
                        MarkerOptions()
                        .position(poi_northHQ)
                        .title("North HQ")
                        .snippet("Block 333, Admiralty Ave 3, 765654")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.stars)));

                UiSettings ui = map.getUiSettings();
                ui.setCompassEnabled(true);
                ui.setZoomControlsEnabled(true);

                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        Toast.makeText(getBaseContext(), marker.getTitle(), Toast.LENGTH_LONG).show();
                       return false;
                    }
                });
            }
        });

        spnLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        if (map != null) {
                            map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_Singapore, 11));
                        }
                        break;
                    case 1:
                        if (map != null) {
                            map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_northHQ,
                                    16));
                        }
                        break;
                    case 2:
                        if (map != null) {
                            map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_Central,
                                    16));
                        }
                        break;
                    case 3:
                        if (map != null) {
                            map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_East,
                                    16));
                        }
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (map != null) {
//                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_northHQ,
//                            16));
//                }
//            }
//        });

//        btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (map != null) {
//                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_Central,
//                            16));
//                }
//            }
//        });

//        btn3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (map != null) {
//                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_East,
//                            16));
//                }
//            }
//        });
    }

}
