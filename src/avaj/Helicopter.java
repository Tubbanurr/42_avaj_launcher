package avaj;

public class Helicopter extends Aircraft {

    public Helicopter(long p_id, String p_name, Coordinates p_coordinate) {
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
                Logger.log("Helicopter#" + name + "(" + id + "): This is hot.");
                lon += 10;
                h   += 2;
                break;
            case "RAIN":
                Logger.log("Helicopter#" + name + "(" + id + "): It's raining. Visibility is low!");
                lon += 5;
                break;
            case "FOG":
                Logger.log("Helicopter#" + name + "(" + id + "): Fog everywhere, flying blind...");
                lon += 1;
                break;
            case "SNOW":
                Logger.log("Helicopter#" + name + "(" + id + "): My rotor is going to freeze!");
                h -= 12;
                break;
        }

        if (h <= 0) {
            Logger.log("Helicopter#" + name + "(" + id + ") landing.");
            weatherTower.unregister(this);
            return;
        }
        coordinates = new Coordinates(lon, lat, Math.min(h, 100));
    }
}
