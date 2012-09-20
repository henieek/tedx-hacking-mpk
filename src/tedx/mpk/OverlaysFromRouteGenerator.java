package tedx.mpk;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import com.google.android.maps.*;

import java.util.LinkedList;
import java.util.List;

public class OverlaysFromRouteGenerator {

    public List<Overlay> getOverlaysForRoute(Route route) {
        GeoPoint lastPoint = null;
        List<Overlay> overlays = new LinkedList<Overlay>();

        for(Line line : route.getListOfLines()) {
            for(Station station : line.getStations()) {
                GeoPoint currStationGeo = new GeoPoint(station.getLatitude(),
                        station.getLongitude());
                if(lastPoint != null) {
                    overlays.add(0, new DirectionPathOverlay(lastPoint, currStationGeo, Color.RED));
                }
                lastPoint = currStationGeo;
            }
        }

        return overlays;
    }


    public static class DirectionPathOverlay extends Overlay {

        private GeoPoint from;
        private GeoPoint to;
        private int color;

        public DirectionPathOverlay(GeoPoint from, GeoPoint to, int color) {
            this.from = from;
            this.to = to;
            this.color = color;
        }

        @Override
        public void draw(Canvas canvas, MapView mapView, boolean shadow) {
            Projection projection = mapView.getProjection();
            if (shadow == false) {
                Paint paint = new Paint();
                paint.setAntiAlias(true);
                Point point = new Point();
                projection.toPixels(from, point);
                paint.setColor(color);
                Point point2 = new Point();
                projection.toPixels(to, point2);
                paint.setStrokeWidth(5);
                canvas.drawLine((float) point.x, (float) point.y, (float) point2.x,
                        (float) point2.y, paint);
            }
        }
    }

    public class MarkersItemizedOverlay extends ItemizedOverlay<OverlayItem> {

        private List<GeoPoint> geoPoints;
        private Drawable drawable;

        public MarkersItemizedOverlay(List<GeoPoint> geoPoints, Drawable drawable) {
            super(boundCenterBottom(drawable));
            this.geoPoints = geoPoints;
            this.drawable = drawable;
            populate();
        }

        @Override
        protected OverlayItem createItem(int i) {
            GeoPoint geoPoint = geoPoints.get(i);
            OverlayItem item = new OverlayItem(geoPoint, "", "");
            item.setMarker(drawable);
            return item;
        }

        @Override
        public int size() {
            return geoPoints.size();
        }

    }
}

