

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Graph {
    private Map<String, Node> nodes = new HashMap<>();
    private Map<Node, TreeSet<Node>> adjacentNodes = new HashMap<>();

    public Graph(String filePath) throws FileNotFoundException {
        // 2 lists to save the objects
        List<Review.Place> list_of_Place = new ArrayList<>();
        List<Category> list_of_Category= new ArrayList<>();

        // The source file and data from it
        File Source_input_File = new File(filePath);
        Scanner scanner = new Scanner(Source_input_File);
        String command = scanner.nextLine();

        // Start reading from the file and create objects
        while (scanner.hasNext()){
            if (command.equals("//Category Definitions")){
                while (true){
                    command = scanner.nextLine();

                    if(command.contains("//")){
                        break;
                    }

                    String split [] = command.split(",");
                    Category categoryObject = new Category(split[0] , split[1]);
                    list_of_Category.add(categoryObject);

                }
            }
            else if (command.equals("//Node Definitions")){
                while (true){
                    command = scanner.nextLine();

                    if(command.contains("//")){
                        break;
                    }
                    String split [] = command.split(",");
                    Node nodeObject = new Node(split[0] , Double.parseDouble(split[1]) , Double.parseDouble(split[2]));
                    nodes.put(split[0], nodeObject);
                }
            }
            else if (command.equals("//Adjacent Nodes")){
                while (true){
                    command = scanner.nextLine();

                    if(command.contains("//")){
                        //printAdjacentNodes();
                        //sortAdjacentNodes();
                        //System.out.println("\n------------------\n");
                        //printAdjacentNodes();


                        break;
                    }
                    String split [] = command.split("->");
                    String str_Node1 = split[0].trim();
                    String str_node2 = split[1].trim();
                    Node node1 = AdjacentNodeMethodTORelevantNode(str_Node1);
                    Node node2 = AdjacentNodeMethodTORelevantNode(str_node2);
                    addAdjacentNode(node1, node2);

                }
            }
            else if (command.equals("//Places at nodes")) {
                while (true){
                    command = scanner.nextLine();

                    if(command.contains("//")){
                        break;
                    }

                    String split [] = command.split("\\s*(->|,|;)\\s*");
                    Review review = new Review();
                    Review.Place place = review.new Place(split[1], split[2]);
                    Node node = nodes.get(split[0]);
                    node.addPlace(place);
                    list_of_Place.add(place);

                    for (int i = 3; i < split.length ; i++) {
                        String cateid = split[i];
                        for (int j = 0; j < list_of_Category.size(); j++) {
                            if(cateid.equals(list_of_Category.get(j).getId())){
                                place.addCategory(list_of_Category.get(j));
                            }

                        }

                    }


                }

            }
            else if (command.equals("//Place Reviews")) {
                while (true){

                    if(!scanner.hasNext()){
                        break;
                    }
                    command = scanner.nextLine();

                    String split [] = command.split("\\s*(,|->)\\s*");
                    Review review;

                    if (Integer.parseInt(split[3]) >=1 && Integer.parseInt(split[3]) <= 5) {
                        review = new Review(split[1], split[2], Integer.parseInt(split[3]));
                    }
                    else {
                        System.out.println("sorry, You can't have Review with Rate > 5 and < 1");
                        continue;
                    }

                    String placeid = split[0];

                    for (int j = 0; j < list_of_Place.size(); j++) {
                        if(placeid.equals(list_of_Place.get(j).getId())){
                            list_of_Place.get(j).addReview(review);
                        }
                    }

                }

            }
        }

    }
    //----------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------

    public Boolean hasNode(String node){
        Node hasNode = nodes.get(node);
        //System.out.println(hasNode);
        if (hasNode == null){
            return false;
        }
        return true;
    }
    public Trip calcTrip(String start, String end) {
        Node startNode = nodes.get(start);
        Node endNode = nodes.get(end);
        Set<Node> visited = new HashSet<>();
        Map<Node, Node> parentMap = new HashMap<>();
        List<Node> path = bfs(startNode, endNode, visited, parentMap);
        Trip trip = new Trip();
        Iterator<Node> nodeIterator = path.iterator();
        while (nodeIterator.hasNext()){
            trip.addNode(nodeIterator.next());
        }
        return trip;
    }

    private List<Node> bfs(Node start, Node target, Set<Node> visited, Map<Node, Node> parentMap) {
        Queue<Node> queue = new LinkedList<>();
        List<Node> path = new ArrayList<>();

        queue.add(start);
        visited.add(start);
        parentMap.put(start, null);

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current.equals(target)) {
                // Reconstruct path
                Node node = target;
                while (node != null) {
                    path.add(node);
                    node = parentMap.get(node);
                }
                Collections.reverse(path);
                return path;
            }

            for (Node neighbor : adjacentNodes.getOrDefault(current, new TreeSet<>())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                    parentMap.put(neighbor, current);
                }
            }
        }

        return path; // Return an empty path if no path found
    }


    private void printPath(List<Node> path) {
        System.out.println("DFS Path:");
        for (Node node : path) {
            System.out.println("<" + node.getId() + ", " + node.getLatitude() + ", " + node.getLongitude() + ">");
        }
    }

    public void printPlaces(String categoryId) {
        Iterator<Node> nodeIterator = nodes.values().iterator();
        while (nodeIterator.hasNext()) {
            Node currentNode = nodeIterator.next();
            currentNode.printPlaces(categoryId);
        }
    }
    public void printReviews(String placeid , boolean backword) {
        Iterator<Node> nodeIterator = nodes.values().iterator();
        while (nodeIterator.hasNext()) {
            Node currentNode = nodeIterator.next();
            currentNode.printReviews(placeid, backword);

        }
    }

    // for adjacentNodes
    private Node AdjacentNodeMethodTORelevantNode(String nodeId) {
        return nodes.computeIfAbsent(nodeId, k -> new Node(nodeId, 0, 0));
    }
    private void addAdjacentNode(Node node, Node adjacentNode) {
        adjacentNodes.computeIfAbsent(node, k -> new TreeSet<>(Comparator.comparingDouble(node::calcDistance))).add(adjacentNode);
    }
}

