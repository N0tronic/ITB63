package de.umfrage.repository;

import de.umfrage.entity.Ersteller;
import de.umfrage.entity.Umfrage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Virus on 27.05.2017.
 */
public interface UmfrageRepository extends JpaRepository<Umfrage,Integer> {
    List<Umfrage> findAllByErsteller(Ersteller ersteller);
}
