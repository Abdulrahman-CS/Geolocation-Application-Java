
import java.util.*;

public class Review {
    private String id;
    private String text;
    private int rating;

    public Review(){

    }
    public Review(String id, String text, int rating) {
        this.id = id;
        this.text = text;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public class Place {
        private String id;
        private String name;
        private Set<Category> categories;
        private Set<Review> reviews;

        public Place(){

        }
        public Place(String id, String name) {
            this.id = id;
            this.name = name;
            this.categories = new HashSet<>();
            this.reviews = new LinkedHashSet<>();
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

        public Set<Category> getCategories() {
            return categories;
        }

        public Set<Review> getReviews() {
            return reviews;
        }

        public void addCategory(Category category){
            categories.add(category);
        }

        public boolean hasCategory(String CategoryID){
            Iterator<Category> iterator = this.categories.iterator();

            while (iterator.hasNext()) {
                Category currentCategory = iterator.next();
                if (currentCategory.getId().equals(CategoryID)) {
                    return true;
                }
            }
            return false;
        }

        public void addReview(Review review){
            reviews.add(review);
        }

        public void printReview(boolean backward){
            Iterator<Review> itr;
            if (backward) {
                List<Review> reviewList= new LinkedList<>(reviews);
                ListIterator<Review> listItr = reviewList.listIterator(reviewList.size());
                while(listItr.hasPrevious()){
                    System.out.println(listItr.previous().toString());
                }

            } else {
                itr = reviews.iterator();
                while (itr.hasNext()) {
                    System.out.println(itr.next().toString());
                }

            }
        }

        public String toString() {
            return super.toString();

        }
    }

}
