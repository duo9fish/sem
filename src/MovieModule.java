//handle the logic for displaying available movies and selecting one based on user input.

public class MovieModule {
    private Movie[] movies;

    public MovieModule() {
        // Initialize the movie array here
        this.movies = new Movie[] {
            new Movie("Star Wars", "English", "Sci-Fi", "3:00PM", "13+", 2),
            new Movie("Your Name", "Japanese", "Fiction", "5:00PM", "13+", 5),
            new Movie("Deadpool", "English", "Comics", "5:00PM", "18+", 1),
            new Movie("Among Us", "English", "Sci-Fi", "8:00PM", "13+", 3),
            new Movie("Imposter", "English", "History", "9:00PM", "18+", 4)
        };
    }

    public void displayMovies() {
        System.out.println("\nAvailable Movies for today: ");
        Movie movie = new Movie();
        movie.movieTableHeader();

        for (int i = 0; i < this.movies.length; i++) {
            System.out.printf("|%-3d", i + 1);
            this.movies[i].printMovieDetails();
        }
        PaymentUtil.printLine();
    }

    public int selectMovie() {
        Movie movie = new Movie();
        return movie.inputValidation(this.movies);
    }

    public Movie getSelectedMovie(int index) {
        return this.movies[index];
    }
}
