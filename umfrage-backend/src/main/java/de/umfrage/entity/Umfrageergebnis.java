package de.umfrage.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by Virus on 27.05.2017.
 */
@Entity
@Getter @Setter @EqualsAndHashCode
public class Umfrageergebnis {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer ergebnisID;
    private Integer teilnehmerzahl;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "umfrageID")
    @JsonBackReference
    private Umfrage umfrage;
}
