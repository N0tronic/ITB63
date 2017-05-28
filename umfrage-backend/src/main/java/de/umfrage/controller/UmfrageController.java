package de.umfrage.controller;

import de.umfrage.entity.Ersteller;
import de.umfrage.entity.Umfrage;
import de.umfrage.repository.ErstellerRepository;
import de.umfrage.repository.UmfrageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Virus on 27.05.2017.
 */
@Controller
@RequestMapping("/online-umfrage")
@CrossOrigin(origins = "*")
public class UmfrageController {

    @Autowired
    private UmfrageRepository umfrageRepository;
    @Autowired
    private ErstellerRepository erstellerRepository;

    @GetMapping(value = "/umfragen",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUmfrageEntries(){
        List<Umfrage> entries = umfrageRepository.findAll();
        return ResponseEntity.ok(entries);
    }

    @PostMapping(value = "/umfragen",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUmfrageEntriesByErsteller(@RequestBody Ersteller ersteller){
        List<Umfrage> entries = umfrageRepository.findAllByErsteller(ersteller);
        return ResponseEntity.ok(entries);
    }

    @GetMapping(value = "/umfragenDarstellung",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> holeUmfrageInformationen(){
        List<Umfrage> entries = umfrageRepository.findAllByOrderByErstellungsdatumDesc();
        return ResponseEntity.ok(entries);
    }

    @PutMapping(value = "/erstelleUmfrage",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> erstelleUmfrage(@RequestBody Ersteller entry){
        Ersteller ersteller = erstellerRepository.findAllByEmail(entry.getEmail());
        entry.setName(ersteller.getName());
        entry.setErstellerID(ersteller.getErstellerID());
        entry = erstellerRepository.save(entry);
        return ResponseEntity.ok(entry);
    }

}
