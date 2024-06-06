
import java.util.Iterator;
import java.util.LinkedHashSet;

import java.util.Set;

public class Trip {
    private Set<Node> path ;

    public Trip(){
        this.path = new LinkedHashSet<>();
    }


    public void print(){
        Iterator <Node>iterator = this.path.iterator();
        System.out.println();
        while (iterator.hasNext()){
            Node node = iterator.next();
            System.out.println("<" + node.getId() +", "+node.getLatitude() + ", "  +node.getLongitude()+">");

        }

    }
    public void addNode(Node node){
        this.path.add(node);
    }
}
