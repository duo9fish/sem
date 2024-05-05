import java.util.Scanner;

public class Movie {

    private String movieName;
    private String movieLang;
    private String movieGenre;
    private String movieTime;
    private String moviePGRating;
    public int movieHallNumber;

    public Movie() {
    }

    public Movie(String name, String language, String genre, String time, String PGRating, int movieHallNumber) {
        this.movieName = name;
        this.movieLang = language;
        this.movieGenre = genre;
        this.movieTime = time;
        this.moviePGRating = PGRating;
        this.movieHallNumber = movieHallNumber;
    }

    public String getMovieName() {
        return movieName;
    }
    
    public int getMovieHallNumber() {
        return movieHallNumber;
    }

    public String getMoviePGRating() {
        return moviePGRating;
    }

    public void printMovieDetails() {
        System.out.printf("|%-20s|%-10s|%-10s|%-10s|%-10s|%-18s|\n", movieName, movieLang, movieGenre, movieTime,
                moviePGRating, movieHallNumber);
    }

    public void movieTableHeader() {
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.printf("|%-3s|%-20s|%-10s|%-10s|%-10s|%-10s|%18s|\n",
                "No.", "Movie Name", "Language", "Genre", "Show Time", "PG Rating", "Movie Hall Number");
        System.out.println("|---------------------------------------------------------------------------------------|");
    }

    public int inputValidation(Movie[] movies) {
        int movieNum;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("\nChoose your movie: ");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input! Please enter NUMBERS ONLY!");
                System.out.print("\nChoose your movie: ");
                sc.next();
            }
            movieNum = sc.nextInt();
            if (movieNum < 1 || movieNum > movies.length) {
                System.out.println("Movie No." + movieNum + " does not exist! Please enter number 1-" + movies.length);
            } else {
                break;
            }
        } while (movieNum < 1 || movieNum > movies.length);
        return movieNum;
    }

}
