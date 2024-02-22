package net.starkenberg.movies.inventory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediumRepository extends JpaRepository<Medium, Long> {
    Medium findByTypeAndLocation(MediaType type, String location);
}
