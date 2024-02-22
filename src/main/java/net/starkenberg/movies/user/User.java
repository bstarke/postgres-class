package net.starkenberg.movies.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import net.starkenberg.movies.inventory.MovieItem;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
@JsonIgnoreProperties({"movieItems"})
public class User {
    @Id
    private String id;
    @Column(unique = true)
    public String email;
    @Column(unique = true)
    public String userId;
    public String displayName;
    @OneToMany(mappedBy = "owner")
    public List<MovieItem> movieItems;
}
