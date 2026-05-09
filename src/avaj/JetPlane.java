package avaj;

public class JetPlane extends Aircraft {

    public JetPlane(long p_id, String p_name, Coordinates p_coordinate) {
        super(p_id, p_name, p_coordinate);
    }

    @Override
    public void updateConditions() {
        String weather = weatherTower.getWeather(coordinates);
        int lon = coordinates.getLongitude();
        int lat = coordinates.getLatitude();
        int h   = coordinates.getHeight();

        switch (weather) {
            case "SUN":
                Logger.log("JetPlane#" + name + "(" + id + "): Clear skies, full throttle!");
                lat += 10;
                h   += 2;
                break;
            case "RAIN":
                Logger.log("JetPlane#" + name + "(" + id + "): It's raining. Better watch out for lightings.");
                lat += 5;
                break;
            case "FOG":
                Logger.log("JetPlane#" + name + "(" + id + "): Fog ahead, slowing down.");
                lat += 1;
                break;
            case "SNOW":
                Logger.log("JetPlane#" + name + "(" + id + "): OMG! Winter is coming!");
                h -= 7;
                break;
        }

        if (h <= 0) {
            Logger.log("JetPlane#" + name + "(" + id + ") landing.");
            weatherTower.unregister(this);
            return;
        }
        coordinates = new Coordinates(lon, lat, Math.min(h, 100));
    }
}
