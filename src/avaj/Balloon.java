package avaj;

public class Balloon extends Aircraft {

    public Balloon(long p_id, String p_name, Coordinates p_coordinate) {
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
                Logger.log("Balloon#" + name + "(" + id + "): Let's enjoy the good weather and take some pics.");
                lon += 2;
                h   += 4;
                break;
            case "RAIN":
                Logger.log("Balloon#" + name + "(" + id + "): Damn you rain! You messed up my balloon.");
                h -= 5;
                break;
            case "FOG":
                Logger.log("Balloon#" + name + "(" + id + "): I can't see anything!");
                h -= 3;
                break;
            case "SNOW":
                Logger.log("Balloon#" + name + "(" + id + "): It's snowing. We're gonna crash.");
                h -= 15;
                break;
        }

        if (h <= 0) {
            Logger.log("Balloon#" + name + "(" + id + ") landing.");
            weatherTower.unregister(this);
            return;
        }
        coordinates = new Coordinates(lon, lat, Math.min(h, 100));
    }
}
