package avaj;

import java.util.*;

public class Tower {
    private List<Flyable> observers = new ArrayList<>();

    public void register(Flyable p_flyable) {
    Logger.log("Tower says: " + p_flyable + " registered to weather tower.");
    observers.add(p_flyable);
    }

    public void unregister(Flyable p_flyable) {
    Logger.log("Tower says: " + p_flyable + " unregistered from weather tower.");
    observers.remove(p_flyable);
    }

    protected void conditionChanged() {
        List<Flyable> copy = new ArrayList<>(observers);
        for (Flyable f : copy)
            f.updateConditions();
    }

}
