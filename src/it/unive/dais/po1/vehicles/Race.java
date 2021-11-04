package it.unive.dais.po1.vehicles;

import it.unive.dais.po1.Printable;
import it.unive.dais.po1.vehicles.animals.AnimalCart;
import it.unive.dais.po1.vehicles.autovehicles.Car;
import it.unive.dais.po1.vehicles.autovehicles.FuelTypeCache;
import it.unive.dais.po1.vehicles.autovehicles.Truck;
import it.unive.dais.po1.vehicles.autovehicles.fuel.FuelTank;
import it.unive.dais.po1.vehicles.autovehicles.fuel.FuelType;

public class Race<T extends Vehicle> {

    private final T v1, v2;

    public Race(T v1, T v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public T race(double length) {
        v1.fullBrake();
        v2.fullBrake();
        double distanceV1 = 0, distanceV2=0;
        while(true) {
            distanceV1 += v1.getSpeed();
            distanceV2 += v2.getSpeed();
            if(distanceV1 >= length || distanceV2 >= length) {
                if(distanceV1 > distanceV2) return v1;
                else return v2;
            }
            v1.accelerate(Math.random()*10.0);
            v2.accelerate(Math.random()*10.0);
        }
    }


    /**
     *
     * @param v1 the first vehicle
     * @param v2 the second vehicle
     * @param length the length of the race
     * @return the id of the winner of the race, or -1
     */
    public static int race(Vehicle v1,  Vehicle v2, double length) {
        System.out.println("Race between two vehicles!");
        v1.fullBrake();
        v2.fullBrake();
        if(v1 instanceof Car) {
            Car c1 = (Car) v1;
            c1.refuel(10);
        }
        if(v2 instanceof Car) {
            Car c2 = (Car) v2;
            c2.refuel(10);
        }
        if(v1 instanceof LoadableUnloadable)
            ((LoadableUnloadable) v1).unload();
        if(v2 instanceof LoadableUnloadable)
            ((LoadableUnloadable) v2).unload();
        if(v1 instanceof Printable)
            ((Printable) v1).print();
        if(v2 instanceof Printable)
            ((Printable) v2).print();
        double distancev1 = 0, distancev2 = 0;
        while(distancev1 < length && distancev2 < length) {
            v1.accelerate(Math.random()*10.0);
            v2.accelerate(Math.random()*10.0);
            distancev1 += v1.getSpeed();
            distancev2 += v2.getSpeed();
        }
        if(distancev1 >= length) {
            if(distancev2 >= length)
                return -1;
            else return 1;
        }
        else return 2;
    }

    public static int race(Car v1,  Vehicle v2, double length) {
        System.out.println("Race between a car and a vehicle!");
        return -1;
    }



    public static int race(Car v1,  Car v2, double length) {
        System.out.println("Race between cars!");
        return -1;
    }

    public static int race(Vehicle v1,  Truck v2, double length) {
        System.out.println("Race between a vehicle and a truck!");
        return -1;
    }


    public static <T extends Vehicle> T new_race(T v1, T v2, double length) {
        v1.fullBrake();
        v2.fullBrake();
        double distanceV1 = 0, distanceV2=0;
        while(true) {
            distanceV1 += v1.getSpeed();
            distanceV2 += v2.getSpeed();
            if(distanceV1 >= length || distanceV2 >= length) {
                if(distanceV1 > distanceV2) return v1;
                else return v2;
            }
            v1.accelerate(Math.random()*10.0);
            v2.accelerate(Math.random()*10.0);
        }
    }


    public static void main(String[] args) {
        FuelTypeCache cache = new FuelTypeCache();

        FuelType diesel = cache.put("diesel", 1.4, 0.01);
        FuelType petrol = cache.put("petrol", 1.5, 0.015);
        Car yourCar = new Car(0, cache.getFuelTypeFromName("petrol"));
        Car myCar = new Car(0, cache.getFuelTypeFromName("petrol"));
        Bicycle myBicycle = new Bicycle(10);
        Truck myTruck = new Truck(0, diesel);
        AnimalCart myCart = new AnimalCart(0, 0);


        FuelTank two_lt = new FuelTank(diesel, 2);
        FuelTank three_lt = new FuelTank(diesel, 3);
        myTruck.refuel(three_lt);
        Vehicle v1 = myCar;
        Vehicle v2 = yourCar;

        Race recobj = new Race(v1, v2);
        ExtendedRace extended_recobj = new ExtendedRace(v1, v2);
        Race fake_raceobj = extended_recobj;

        Car winner = Race.new_race(myCar, yourCar, 100);
        Truck winner2 = Race.new_race(myTruck, myTruck, 100);

        fake_raceobj.race(v1, v2, 100.0);

        Car b = new Race<>(myCar, yourCar).race(100);
    }
}
