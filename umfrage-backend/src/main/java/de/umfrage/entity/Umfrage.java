package de.umfrage.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Virus on 27.05.2017.
 */
@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Umfrage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer umfrageID;
    @JsonFormat(pattern = "dd.MM.yyyy, HH:mm:ss")
    private Date erstellungsdatum;
    @JsonFormat(pattern = "dd.MM.yyyy, HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startdatum;
    @JsonFormat(pattern = "dd.MM.yyyy, HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endedatum;
    private String titel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "erstellerID")
    @JsonBackReference
    private Ersteller ersteller;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "umfrage")
    @JsonManagedReference
    private List<Frage> fragen;

    public String getEmail() {
        String erstellerMail = "";
        if (ersteller != null) erstellerMail = ersteller.getEmail();
        return erstellerMail;
    }

    public String getName(){
        String erstellerName = "";
        if (ersteller != null) erstellerName = ersteller.getName();
        return erstellerName;
    }

    public Integer getErstellerID(){
        Integer erstellerID = 0;
        if (ersteller != null) erstellerID = ersteller.getErstellerID();
        return erstellerID;
    }
}
