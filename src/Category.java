
import java.util.HashSet;
import java.util.Set;


public class Category {
    private String id;
    private String name;
    private static Set<Category> allCategories = new HashSet<>();


    // Constructor method
    public Category() {
    }
    public Category(String id , String name){
        this.id = id;
        this.name = name;
        allCategories.add(this);

    }
        public void printAllCategories(){
        for (Category category : allCategories) {
            System.out.println(category.getId() + "-" + category.getName());
        }
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
