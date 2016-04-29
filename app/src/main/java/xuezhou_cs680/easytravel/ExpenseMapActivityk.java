package xuezhou_cs680.easytravel;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ExpenseMapActivityk extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final LatLng LUKE_LOBSTER = new LatLng(42.348934, -71.079442);
    private static final LatLng TORO=new LatLng(42.336994, -71.075925);
    private static final LatLng DUMPLING=new LatLng(42.351590,-71.060671);
    private static final LatLng BONCHON=new LatLng(42.372125,-71.120882);
    private static final float zoom = 12f; //Google Mao zoom level

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_map_activityk);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        if(mMap==null){
            mMap = ((SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map)).getMap();
            if(mMap!=null){
                mMap.addMarker(new MarkerOptions()  //add marker to map
                        .position(DUMPLING)
                        .title("$40.35") //set title
                        .snippet("Gourmet Dumpling House,Chinese Restaurant") //set snippet of text
                        .icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("marker", 100, 100)))); //set image from drawable as marker
                //use Bitmap to create a definition of the image, used for marker icons and ground overlays
                mMap.addMarker(new MarkerOptions()
                        .position(TORO)
                        .title("$47.89")
                        .snippet("Toro Spanish Restaurant")
                        .icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("marker", 100, 100))));
                mMap.addMarker(new MarkerOptions()
                        .position(BONCHON)
                        .title("$30.99")
                        .snippet("BonChon Chicken, Korean Restaurant")
                        .icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("marker", 100, 100))));

                mMap.addMarker(new MarkerOptions()
                        .position(LUKE_LOBSTER)
                        .title("$34.56")
                        .snippet("Luke's Lobster,Local Best")
                        .icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("marker", 100, 100))));
            }
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true); //enable the my-location layer. need to request permission

        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LUKE_LOBSTER, zoom));//Animates the movement of the camera from the current position to the position defined in the update

    }
    public Bitmap resizeMapIcons(String iconName, int width, int height){
        Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(),getResources().getIdentifier(iconName, "drawable", getPackageName()));
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, width, height, false);
        return resizedBitmap;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}
