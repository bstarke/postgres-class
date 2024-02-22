package net.starkenberg.movies.inventory;

import net.starkenberg.movies.cinema.Movie;
import net.starkenberg.movies.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieItemRepository extends JpaRepository<MovieItem, Long> {

    List<MovieItem> findByOwner(User owner);
    /*  133.91 milliseconds spent acquiring 1 JDBC connections;
        150.44 milliseconds spent preparing 4018 JDBC statements;
        4526.18 milliseconds spent executing 4018 JDBC statements; */

    @Query(value = "SELECT DISTINCT mi FROM MovieItem mi LEFT JOIN FETCH mi.movie LEFT JOIN FETCH mi.media WHERE mi.owner = :owner ORDER BY mi.movie.title")
    List<MovieItem> findByOwnerJoined(User owner);
    /*  1.98 milliseconds spent acquiring 1 JDBC connections;
        2.15 milliseconds spent preparing 2 JDBC statements;
        150.23 milliseconds spent executing 2 JDBC statements; */

    @Override
    @Query(value = "SELECT DISTINCT mi FROM MovieItem mi LEFT JOIN FETCH mi.movie LEFT JOIN FETCH mi.media ORDER BY mi.movie.title")
    List<MovieItem> findAll();

    @Query(value = "SELECT mi FROM MovieItem mi LEFT JOIN FETCH mi.movie LEFT JOIN FETCH mi.media WHERE mi.owner = :owner AND mi.movie.imdbID = :imdbId")
    MovieItem findByOwnerAndImdbId(User owner, String imdbId);

    @Query(value = "SELECT mi FROM MovieItem mi LEFT JOIN FETCH mi.movie LEFT JOIN FETCH mi.media WHERE mi.owner = :owner AND mi.movie = :movie")
    MovieItem findByMovieAndOwner(Movie movie, User owner);
}
