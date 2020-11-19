import plane.ExperimentalPlane;
import model.ClassificationLevel;
import model.MilitaryType;
import plane.MilitaryPlane;
import plane.PassengerPlane;
import plane.Plane;

import java.util.*;
import java.util.stream.Collectors;

public class Airport {
    private List<? extends Plane> planes;

    public Airport(List<? extends Plane> planes) {
        this.planes = planes;
    }

    public List<PassengerPlane> getPassengerPlanes() {
        return planes.stream().filter(p -> p instanceof PassengerPlane).map(p -> (PassengerPlane)p).collect(Collectors.toList());
    }

    public List<MilitaryPlane> getMilitaryPlanes() {
        return planes.stream().filter(p -> p instanceof  MilitaryPlane).map(p -> (MilitaryPlane)p).collect(Collectors.toList());
    }

    public List<ExperimentalPlane> getExperimentalPlanes() {
        return planes.stream().filter(p -> p instanceof  ExperimentalPlane).map(p -> (ExperimentalPlane)p).collect(Collectors.toList());
    }

    public List<ExperimentalPlane> getUnclassifiedExperimentalPlanes() {
        return planes.stream().filter(p -> p instanceof ExperimentalPlane).map(p -> (ExperimentalPlane)p)
                .filter(p -> p.getClassificationLevel() == ClassificationLevel.UNCLASSIFIED).collect(Collectors.toList());
    }

    public PassengerPlane getPassengerPlaneWithMaxPassengersCapacity() {
        return getPassengerPlanes().stream().sorted(Comparator.comparingInt(PassengerPlane::getPassengersCapacity).reversed())
                .findFirst().get();
    }

    public List<MilitaryPlane> getTransportMilitaryPlanes() {
        return planes.stream().filter(p -> p instanceof MilitaryPlane).map(p -> (MilitaryPlane)p)
                .filter(p -> p.getType() == MilitaryType.TRANSPORT).collect(Collectors.toList());
    }

    public List<MilitaryPlane> getBomberMilitaryPlanes() {
        return planes.stream().filter(p -> p instanceof MilitaryPlane).map(p -> (MilitaryPlane)p)
                .filter(p->p.getType() == MilitaryType.BOMBER).collect(Collectors.toList());
    }

    public List<MilitaryPlane> getFighterMilitaryPlanes() {
        return planes.stream().filter(p -> p instanceof MilitaryPlane).map(p -> (MilitaryPlane)p)
                .filter(p->p.getType() == MilitaryType.FIGHTER).collect(Collectors.toList());
    }

    public Airport sortByMaxDistance() {
        Collections.sort(planes, new Comparator<Plane>() {
            public int compare(Plane o1, Plane o2) {
                return o1.getMaxFlightDistance()- o2.getMaxFlightDistance();
            }
        });
        return this;
    }

    public Airport sortByMaxSpeed() {
        Collections.sort(planes, new Comparator<Plane>() {
            public int compare(Plane o1, Plane o2) {
                return o1.getMaxSpeed() - o2.getMaxSpeed();
            }
        });
        return this;
    }

    public List<Plane> sortByMaxLoadCapacity() {
        return planes.stream().sorted(Comparator.comparingInt(Plane::getMaxLoadCapacity)).collect(Collectors.toList());
    }

    public List<? extends Plane> getPlanes() {
        return planes;
    }

    public void setPlanes(List<? extends Plane> planes) {
        this.planes=planes;
    }

    private void print(Collection<? extends Plane> collection) {
        planes.stream().forEachOrdered(p -> System.out.println(p.toString()));
    }

    @Override
    public String toString() {
        return "Airport{" +
                "Planes=" + planes.toString() +
                '}';
    }
}
