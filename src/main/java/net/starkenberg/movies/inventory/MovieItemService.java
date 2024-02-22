package net.starkenberg.movies.inventory;

import net.starkenberg.movies.cinema.Movie;
import net.starkenberg.movies.cinema.MovieRepository;
import net.starkenberg.movies.user.User;
import net.starkenberg.movies.user.UserService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MovieItemService {
    private final MovieItemRepository movieItemRepository;
    private final MovieRepository movieRepository;
    private final MediumRepository mediumRepository;
    private final UserService userService;

    public MovieItemService(MovieItemRepository movieItemRepository, MovieRepository movieRepository, MediumRepository mediumRepository, UserService userService) {
        this.movieItemRepository = movieItemRepository;
        this.movieRepository = movieRepository;
        this.mediumRepository = mediumRepository;
        this.userService = userService;
    }

    public MovieItem getById(Long id) {
        Optional<MovieItem> movieItem = movieItemRepository.findById(id);
        return movieItem.orElse(null);
    }

    public MovieItem addToInventory(String imdbID, MediaType mediaType, String location, User user) {
        Movie movie = getMovie(imdbID);
        if (movie != null) {
            MovieItem movieItem = getMovieItem(movie, user);
            addLocationIfMissing(movieItem, mediaType, location);
            return movieItem;
        }
        return null;
    }

    public List<MovieItem> getAll(String uid) {
        User user = userService.getUserById(uid);
        return movieItemRepository.findByOwner(user);
    }

    public List<MovieItem> getAllJoined(String uid) {
        User user = userService.getUserById(uid);
        return movieItemRepository.findByOwnerJoined(user);
    }

    public MovieItem getMovieByImdbId(String uid, String imdbId) {
        User user = userService.getUserById(uid);
        return movieItemRepository.findByOwnerAndImdbId(user, imdbId);
    }

    public MovieItem getMovieByImdbId(String imdbId, User user) {
        return movieItemRepository.findByOwnerAndImdbId(user, imdbId);
    }

    private Medium getMedium(MediaType type, String location) {
        Medium medium = mediumRepository.findByTypeAndLocation(type, location);
        if (medium == null) {
            medium = new Medium();
            medium.setType(type);
            medium.setLocation(location);
            medium = mediumRepository.save(medium);
        }
        return medium;
    }

    private Movie getMovie(String imdbID) {
        return movieRepository.findByImdbID(imdbID);
    }

    private MovieItem getMovieItem(Movie movie, User user) {
        MovieItem movieItem = movieItemRepository.findByMovieAndOwner(movie, user);
        if (movieItem == null) {
            movieItem = new MovieItem();
            movieItem.setOwner(user);
            movieItem.setMovie(movie);
            movieItem = movieItemRepository.save(movieItem);
        }
        return movieItem;
    }

    private void addLocationIfMissing(MovieItem movieItem, MediaType mediaType, String location) {
        if (movieItem.getMedia() == null) {
            Set<Medium> media = new HashSet<Medium>();
            media.add(getMedium(mediaType, location));
            movieItem.setMedia(media);
        } else {
            for (Medium medium : movieItem.getMedia()) {
                if (medium.getType() == mediaType && medium.getLocation().equalsIgnoreCase(location))
                    return;
            }
            movieItem.getMedia().add(getMedium(mediaType, location));
        }
        movieItemRepository.save(movieItem);
    }
}
