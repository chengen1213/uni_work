public class Movie implements Comparable {

    private String name;
    private int year;

    public Movie() {
        super();
    }

    public Movie(String name, int year) {

        this.name = name;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public int compareTo(Object o) {
        Movie m = (Movie)o;
        return this.year - m.getYear();
    }

    public int compareByString(Movie m) {
        return Tools.stringComparator(this.getName(),m.name);
    }
}
