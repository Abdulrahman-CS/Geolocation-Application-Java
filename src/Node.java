
import java.util.*;

public class Node {
    private String id;
    private double latitude;
    private double longitude;
    private Set<Review.Place> places;
    public Node(){

    }

    public Node(String id, double latitude, double longitude) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.places = new LinkedHashSet<>();
    }



    public String getId() {
        return id;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void addPlace(Review.Place place) {
        places.add(place);
    }

    public void printReviews(String placeID, boolean backward) {
        Iterator<Review.Place> itr = this.places.iterator();
        while (itr.hasNext()) {
            Review.Place place = itr.next();
            if(placeID.equals(place.getId())){
                place.printReview(backward);
            }
        }
    }

    public void printPlaces(String categoryId) {
        Iterator<Review.Place> itr = this.places.iterator();
        Boolean hascategory = true;
        while (itr.hasNext()) {
            Review.Place place = itr.next();
            if (place.hasCategory(categoryId)) {
                hascategory = false;
                System.out.println(this.id + " has " + place.getName() + "(PlaceId=" + place.getId() + ")");
                break;
            }
        }


    }



    public double calcDistance(Node otherNode) {
        return calcDistance(this, otherNode);
    }

    public static double calcDistance(Node start, Node end) {
        final int R = 6371; // Radius of the earth
        double latDistance = Math.toRadians(end.latitude - start.latitude);
        double lonDistance = Math.toRadians(end.longitude - end.longitude);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(start.latitude)) *
                Math.cos(Math.toRadians(end.latitude))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters
        distance = Math.pow(distance, 2);
        return Math.sqrt(distance);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Node node = (Node) o;
        return Double.compare(node.latitude, latitude) == 0 &&
                Double.compare(node.longitude, longitude) == 0 &&
                Objects.equals(id, node.id);
    }
}
