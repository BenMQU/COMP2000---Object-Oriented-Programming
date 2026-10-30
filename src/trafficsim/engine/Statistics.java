package trafficsim.engine;

import trafficsim.model.vehicle.Vehicle;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class Statistics {

    private Statistics() {}

    public static double averageSpeed(List<Vehicle> vehicles) {
        return vehicles.stream()
                .mapToDouble(Vehicle::getSpeed)
                .average()
                .orElse(0.0);
    }

    public static long stoppedCount(List<Vehicle> vehicles) {
        return vehicles.stream()
                .filter(v -> v.getSpeed() == 0.0)
                .count();
    }

    /** A per-vehicle-type traffic report, calculated without mutating the simulation. */
    public static Map<String, DoubleSummaryStatistics> speedByVehicleType(List<Vehicle> vehicles) {
        return vehicles.stream().collect(Collectors.groupingBy(
                vehicle -> vehicle.getClass().getSimpleName(),
                Collectors.summarizingDouble(Vehicle::getSpeed)));
    }
}
