package ru.mirea.stulovad.mireaproject.ui.map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ru.mirea.stulovad.mireaproject.databinding.FragmentMapBinding;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.compass.CompassOverlay;
import org.osmdroid.views.overlay.compass.InternalCompassOrientationProvider;

public class MapFragment extends Fragment {
    private FragmentMapBinding binding;
    private MapView mapView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Configuration.getInstance().setUserAgentValue(requireContext().getPackageName());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMapBinding.inflate(inflater, container, false);
        mapView = binding.mapView;
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        mapView.setMultiTouchControls(true);

        IMapController mapController = mapView.getController();
        mapController.setZoom(12.0);
        GeoPoint startPoint = new GeoPoint(55.7289, 37.6003);
        mapController.setCenter(startPoint);

        CompassOverlay compassOverlay = new CompassOverlay(getContext(), new InternalCompassOrientationProvider(getContext()), mapView);
        compassOverlay.enableCompass();
        mapView.getOverlays().add(compassOverlay);
        addMarker(55.7289, 37.6003, "Парк Горького", "Главный столичный парк с набережной, музеями и зонами отдыха.");
        addMarker(55.8653, 37.5607, "ВДНХ", "Выставка достижений народного хозяйства с павильонами, фонтанами и океанариумом.");
        addMarker(55.7558, 37.6173, "Зарядье", "Современный ландшафтный парк с парящим мостом над Москвой-рекой.");
        addMarker(55.7039, 37.5303, "Воробьёвы горы", "Смотровая площадка с панорамным видом на город напротив главного здания МГУ.");
        return binding.getRoot();
    }

    private void addMarker(double latitude, double longitude, String title, String description) {
        Marker marker = new Marker(mapView);
        marker.setPosition(new GeoPoint(latitude, longitude));
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        marker.setTitle(title);
        marker.setSnippet(description);
        mapView.getOverlays().add(marker);
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }
}