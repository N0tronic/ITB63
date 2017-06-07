package de.umfrage.controller;

import de.umfrage.clientmodel.Antwortmoeglichkeit;
import de.umfrage.clientmodel.Ersteller;
import de.umfrage.clientmodel.Frage;
import de.umfrage.clientmodel.Umfrage;
import de.umfrage.feign.UmfrageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Virus on 02.06.2017.
 */
@Controller
public class UmfrageController {

    @Autowired
    UmfrageClient umfrageClient;

    @PostMapping(value = "/speichereErstellerUmfrage")
    public String speichereUmfrage(@ModelAttribute Ersteller ersteller, Boolean edit, Model model) {
        List<Antwortmoeglichkeit> leereAntworten = new ArrayList<>();
        String umfragetitel = ersteller.getUmfragen().get(0).getTitel();

        Umfrage umfrageFromDB = umfrageClient.holeUmfrageByTitel(umfragetitel).getBody();
        if(umfrageFromDB != null && edit != true){
            model.addAttribute("exists", true);
            return "umfrageBearbeitung";
        }

        for (Umfrage umfrage : ersteller.getUmfragen()){
            umfrage.setErstellungsdatum(new Date());
            for (Frage frage : umfrage.getFragen()) {
                for (Antwortmoeglichkeit antwortmoeglichkeit : frage.getAntwortmoeglichkeiten()) {
                    if(antwortmoeglichkeit.getAntworttext() == ""){
                        leereAntworten.add(antwortmoeglichkeit);
                    }
                }
                for (Antwortmoeglichkeit antwortmoeglichkeit : leereAntworten) {
                    frage.getAntwortmoeglichkeiten().remove(antwortmoeglichkeit);
                }
            }
        }
        umfrageClient.createErsteller(ersteller);
        return this.showUmfrageByTitel(umfragetitel,model);
    }

    @PostMapping(value = "/aktualisiereAntworten")
    public String aktualisiereAntwort(Model model, int antwortID, String titel) {
        umfrageClient.updateAntworthäufigkeit(antwortID);
        return this.showUmfrageStatistics(titel,model);
    }

    @PostMapping(value = "/loescheUmfrage")
    public String loescheUmfrage(Model model, String titel, int umfrageID) {
        umfrageClient.deleteUmfrage(umfrageID);
        model.addAttribute("titel",titel);
        return "delete";
    }

    @GetMapping(value = "/umfrageErstellung")
    public String bearbeiteUmfrage(Model model) {
        model.addAttribute("ersteller",new Ersteller());
        model.addAttribute("edit",false);
        return "umfrageBearbeitung";
    }

    @PostMapping(value = "/umfrageErstellung")
    public String bearbeiteVorhandeneUmfrage(String titel, Model model) {
        Umfrage umfrage = umfrageClient.holeUmfrageByTitel(titel).getBody();
        Ersteller ersteller = new Ersteller();
        ersteller.setEmail(umfrage.getEmail());
        ersteller.setName(umfrage.getName());
        ersteller.setErstellerID(umfrage.getErstellerID());
        List<Umfrage> umfragen = new ArrayList<>(1);
        umfragen.add(umfrage);
        ersteller.setUmfragen(umfragen);
        model.addAttribute("ersteller",ersteller);
        model.addAttribute("edit",true);
        return "umfrageBearbeitung";
    }

    @GetMapping("/umfragestatistik")
    public String showUmfrageStatistics(@RequestParam("umfragetitel") String umfragetitel, Model model) {
        ResponseEntity<List<Antwortmoeglichkeit>> responseEntity = umfrageClient.holeUmfrageAntwortenByTitel(umfragetitel);
        ResponseEntity<Umfrage> umfrageEntity = umfrageClient.holeUmfrageByTitel(umfragetitel);
        Umfrage umfrage = umfrageEntity.getBody();
        List<Antwortmoeglichkeit> antwortmoeglichkeiten = responseEntity.getBody();
        int anzahl = 0,gesamtanzahl = 0;
        for (Antwortmoeglichkeit antwortmoeglichkeit : antwortmoeglichkeiten) {
            if(antwortmoeglichkeit.getAntworthaeufigkeit() > 0){
                gesamtanzahl += antwortmoeglichkeit.getAntworthaeufigkeit();
                anzahl++;
            }
        }
        Object[][] stats = new Object[anzahl+1][2];
        int i = 1;
        stats[0][0] = "Antwort";
        stats[0][1] = "Antwortanzahl";
        for(Antwortmoeglichkeit antwort : antwortmoeglichkeiten){
            if(antwort.getAntworthaeufigkeit() > 0){
                stats[i][0] = antwort.getAntworttext();
                stats[i][1] = antwort.getAntworthaeufigkeit();
                i++;
            }
        }
        model.addAttribute("gesamtanzahl", gesamtanzahl);
        model.addAttribute("titel", umfragetitel);
        model.addAttribute("frage", umfrage.getFragen().get(0).getFragetext());
        model.addAttribute("stats", stats);
        return "statistic";
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
