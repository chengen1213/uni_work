public class Main {

    public static void main(String[] args) {

        int number = 10;
        int random = (int) (Math.random() * number);
        Movie movie = new Movie();
        Movie[] movies = new Movie[number];
        for (int i = 0; i < number; i++) {
            movies[i] = new Movie(Tools.nameGenerator(), Tools.yearGenerator());
            if (i == random) {
                movie = movies[i];
            }
        }
        for (Movie m : movies) {
            System.out.println(m.getName() + " " + m.getYear());
        }
        System.out.println("-------------------------");
        Sort sort = new Sort();
        sort.sort(movies);
        int i = 0;
        for (Movie m : movies) {
            System.out.println( i + ": " + m.getName() + " " + m.getYear());
            i++;
        }
        System.out.println("-------------------------");
        BinarySearch binarySearch = new BinarySearch();
        System.out.println("Movie produced in " + movie.getYear() + " is at index " + binarySearch.search(movies, movie, 0, movies.length-1) + ".");
    }

}
