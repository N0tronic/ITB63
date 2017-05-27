package de.umfrage.repository;

import de.umfrage.entity.Ersteller;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Virus on 27.05.2017.
 */
public interface ErstellerRepository extends JpaRepository<Ersteller,Integer> {
}
