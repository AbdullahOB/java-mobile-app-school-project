package com.example.mobil_projesi;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map,container,false);

        SupportMapFragment map = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);

        map.getMapAsync(new OnMapReadyCallback() {
            public void onMapReady(GoogleMap maps){
                maps.setOnMapClickListener(new GoogleMap.OnMapClickListener(){
                    @Override
                    public void onMapClick(LatLng latng){
                        MarkerOptions mark = new MarkerOptions();
                        mark.position(latng);
                        mark.title(latng.latitude + " : " + latng.longitude);
                        maps.clear();
                        maps.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                latng, 10

                        ));
                        maps.addMarker(mark);
                    }
                });
            }
        });

        return view;
    }
}