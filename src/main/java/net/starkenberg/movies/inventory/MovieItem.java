package net.starkenberg.movies.inventory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;
import net.starkenberg.movies.cinema.Movie;
import net.starkenberg.movies.user.User;

import java.util.Set;

@Getter
@Setter
@Entity
@JsonIgnoreProperties({"owner"})
@Table(name = "movie_inventory", uniqueConstraints = { @UniqueConstraint(columnNames = { "movie_id" }) } )
public class MovieItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movie_inventory_sequence")
    @SequenceGenerator(name = "movie_inventory_sequence", sequenceName = "movie_inventory_seq", allocationSize = 5)
    private Long id;
    @ManyToOne
    private Movie movie;
    @ManyToMany
    private Set<Medium> media;
    @ManyToOne
    private User owner;
}
