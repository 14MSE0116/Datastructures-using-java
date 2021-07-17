import java.util.*;
import java.io.*;

class co2emittercalc {
    String transportation_method;
    double distance;
    String unit_distance;

    HashMap<String, Integer> co2data;

    co2emittercalc() {
        transportation_method = null;
        distance = 0;
    }

    co2emittercalc(String transportation_method, double distance, String unit_distance) {
        this.transportation_method = transportation_method;
        this.distance = distance;
        this.unit_distance = unit_distance;

        co2data = new HashMap<>();
        co2data.put("small-diesel-car", 142);
        co2data.put("small-petrol-car", 152);
        co2data.put("small-plugin-hybrid-car", 73);
        co2data.put("small-electric-car", 50);

        co2data.put("medium-diesel-car", 171);
        co2data.put("medium-petrol-car", 192);
        co2data.put("medium-plugin-hybrid-car", 110);
        co2data.put("medium-electric-car", 58);

        co2data.put("large-diesel-car", 209);
        co2data.put("large-petrol-car", 282);
        co2data.put("large-plugin-hybrid-car", 126);
        co2data.put("large-electric-car", 73);

        co2data.put("bus", 27);
        co2data.put("train", 6);

    }

    private void validate_data() {
        if (co2data.get(this.transportation_method) == null) {
            throw new RuntimeException("Specified transportation--method doesn't exist");
        }

        if (this.distance <= 0) {
            throw new RuntimeException("Distance should always be greater than 0");
        }

        if (!(this.unit_distance.equals("km") || this.unit_distance.equals("m")
                || this.unit_distance.equals("default"))) {
            throw new RuntimeException("Specified unit--of--distance doesnt exist");
        }
    }

    void Calculate_Co2_emmision() {
        validate_data();
        double res;
        int co2perunit = co2data.get(this.transportation_method);
        res = this.distance * co2perunit;
        if (this.unit_distance.equals("km") || this.unit_distance.equals("default")) {
            res = res / 1000;
        } else if (this.unit_distance.equals("m")) {
            res = res / (int) 1e6;
            System.out.format("Your trip caused " + "%.1f" + " g of CO2-equivalent.", res * 1000);
            System.out.println();
        }
        System.out.format("Your trip caused " + "%.1f" + " kg of CO2-equivalent.", res);
    }
}

public class co2emission {
    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);
        System.out.print("transportation--method:");
        String transport_method = scn.next();
        System.out.print("distance:");
        double dis = scn.nextDouble();
        System.out.print("unit--of--distance(km or m or default):");
        String unit = scn.next();
        if (unit == "default") {
            co2emittercalc obj = new co2emittercalc(transport_method, dis, "km");
            obj.Calculate_Co2_emmision();
        } else {
            co2emittercalc obj = new co2emittercalc(transport_method, dis, unit);
            obj.Calculate_Co2_emmision();
        }

    }
}
