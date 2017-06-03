package de.umfrage.repository;

import de.umfrage.entity.Antwortmöglichkeit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Virus on 02.06.2017.
 */
@Repository
public interface AntwortRepository extends JpaRepository<Antwortmöglichkeit,Integer> {
    Antwortmöglichkeit findByAntwortID(Integer antwortId);
}
