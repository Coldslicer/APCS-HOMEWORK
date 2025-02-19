public class P5_Phadke_Sharvil_YelpRating implements Comparable<P5_Phadke_Sharvil_YelpRating> {
    private String subject, userName, review;
    private double rating;

    public P5_Phadke_Sharvil_YelpRating(String subject, String review, double rating, String userName) {
        this.subject = subject;
        this.userName = userName;
        this.review = review;
        this.rating = rating;
    }

    @Override
    public int compareTo(P5_Phadke_Sharvil_YelpRating o) {
        return Double.compare(this.rating, o.rating);
    }

    @Override
    public String toString() {
        return String.format("Subject:\t%s\nReview:\t%s\nRating:\t%.1f\nUser:\t%s\n", subject, review, rating, userName);
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
    
}