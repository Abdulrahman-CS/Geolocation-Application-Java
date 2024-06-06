


import java.io.FileNotFoundException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        Graph graph = new Graph("C:\\Users\\dehmi\\Desktop\\input_File.txt");

        // The User Interface
        display_Menu();
        Scanner scan = new Scanner(System.in);
        int User=0;
        while (true) {
            try {
                User = scan.nextInt();
                if(User >= 1 && User <=4)
                    break;
                System.out.println("\nYou should enter a number from menu !!");
                display_Menu();
            } catch (Exception e) {
                System.out.println("\nYou should enter a number from menu !!");
                display_Menu();
                scan.next();

            }
        }


        if (User == 1){
            new Category().printAllCategories();
        }


        else if (User == 2){
            System.out.print("Enter Category id: ");
            int CategoryID;
            while (true) {
                try {
                    CategoryID = scan.nextInt();
                    break;
                } catch (Exception e) {
                    System.out.println("\nYou should enter a number !!");
                    System.out.print("Enter Category id: ");
                    scan.next();


                }
            }
            //graph.printPlaces(CategoryID +"");
            findPlace(graph , CategoryID+"");
        }
        else if (User == 3){
            System.out.print("Do you want to display the reviews backwards (from newest to oldest) [y/n]: ");
            String  backward;
            while (true) {
                try {
                    backward = scan.next();
                    if (backward.equals("y" ) || backward.equals("n"))
                        break;
                    System.out.println("\nYou should enter (y/n) !!");
                    System.out.print("Do you want to display the reviews backwards (from newest to oldest) [y/n]: ");

                } catch (Exception e) {
                    System.out.println("\nYou should enter (y/n) !!");
                    System.out.print("Do you want to display the reviews backwards (from newest to oldest) [y/n]: ");
                    scan.next();


                }
            }
            if (backward.equals("y")) {
                graph.printReviews("1", true);
            }
            else {
                graph.printReviews("1", false);
            }
        }
        else if (User == 4){
            System.out.print("Enter starting node id: ");
            String Node1;

            while (true) {
            try {
                Node1 = scan.next();
                if (graph.hasNode(Node1))
                    break;

                System.out.println("\nYou should enter Node id !!");
                System.out.print("Enter starting node id: ");

            } catch (Exception e) {
                System.out.println("\nYou should enter Node id !!");
                System.out.print("Enter starting node id: ");
                scan.next();

            }
            }

            System.out.print("Enter destination node id: ");
            String Node2;
            while (true) {
                try {
                    Node2 = scan.next();
                    if (graph.hasNode(Node2))
                        break;

                    System.out.println("\nYou should enter Node id !!");
                    System.out.print("Enter destination node id: ");

                } catch (Exception e) {
                    System.out.println("\nYou should enter Node id !!");
                    System.out.print("Enter destination node id: ");
                    scan.next();

                }
            }


//            Trip trip = graph.calcTrip(Node1 , Node2);
//            trip.print();
            calcTrip(graph , Node1 , Node2);
        }
        else {
            System.out.println("You should enter a number from menu !!");
        }

    }
    private static void display_Menu(){
        System.out.println("Available choices:");
        System.out.println("\t\t(1) Display all categories");
        System.out.println("\t\t(2) Search the graph for places based on their categories");
        System.out.println("\t\t(3) Display the reviews of a place.");
        System.out.println("\t\t(4) Calculate the path between tow nodes.");
        System.out.print("Enter choice number: ");

    }
    private static void findPlace(Graph graph, String CategoryID){
        graph.printPlaces(CategoryID +"");

    }

    private void findNodeOfPlace(){

    }

    private static void calcTrip(Graph graph , String Node1 , String Node2){
        Trip trip = graph.calcTrip(Node1 , Node2);
        trip.print();
    }
}

