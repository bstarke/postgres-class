package net.starkenberg.movies.inventory;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"type","location"}))
public class Medium {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "medium_sequence")
    @SequenceGenerator(name = "medium_sequence", sequenceName = "medium_seq", allocationSize = 1)
    private Long id;
    private MediaType type;
    private String location;
}
