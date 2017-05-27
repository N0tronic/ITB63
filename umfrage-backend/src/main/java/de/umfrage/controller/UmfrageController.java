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

    @PutMapping(value = "/umfragen",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUmfrageEntry(@RequestBody Umfrage entry){
        entry = umfrageRepository.save(entry);
        return ResponseEntity.ok(entry);
    }

    @GetMapping(value = "/ersteller",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getErstellerEntries(){
        List<Ersteller> entries = erstellerRepository.findAll();
        return ResponseEntity.ok(entries);
    }

    @PutMapping(value = "/ersteller",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createErstellerEntry(@RequestBody Ersteller entry){
        entry = erstellerRepository.save(entry);
        return ResponseEntity.ok(entry);
    }

}
