package de.umfrage.controller;

import de.umfrage.clientmodel.Antwortmoeglichkeit;
import de.umfrage.clientmodel.Umfrage;
import de.umfrage.feign.UmfrageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by Virus on 02.06.2017.
 */
@Controller
public class UmfrageController {

    @Autowired
    UmfrageClient umfrageClient;

    @PostMapping(value = "/aktualisiereAntworten")
    public ResponseEntity<Antwortmoeglichkeit> aktualisiereAntwort(@RequestBody Antwortmoeglichkeit antwortmoeglichkeit) {
        ResponseEntity<Antwortmoeglichkeit> antwort = umfrageClient.updateAntworthäufigkeit(antwortmoeglichkeit);
        return ResponseEntity.ok(antwort.getBody());
    }

    @GetMapping("/alleUmfragen")
    public String showAlleUmfragen(Model model) {
        ResponseEntity<List<Umfrage>> umfragen = umfrageClient.holeUmfrageInformationen();
        model.addAttribute("umfragen", umfragen.getBody());
        return "alleUmfragen";
    }

    @GetMapping("/umfrage")
    public String showUmfrageByTitel(@RequestParam("umfragetitel") String umfragetitel, Model model) {
        String site = "error";
        ResponseEntity<Umfrage> umfrage = umfrageClient.holeUmfrageByTitel(umfragetitel);
        if (umfrage != null && umfrage.getBody() != null) {
            model.addAttribute("umfrage", umfrage.getBody());
            site = "umfrage";
        }
        return site;
    }
}
