package de.umfrage.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Virus on 27.05.2017.
 */
@Entity
@Getter @Setter @EqualsAndHashCode
public class Frage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer frageID;
    private String fragetext;
    private String erläuterung;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "umfrageID")
    @JsonBackReference
    private Umfrage umfrage;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "frage")
    private List<Antwortmöglichkeit> antwortmöglichkeiten;

}
