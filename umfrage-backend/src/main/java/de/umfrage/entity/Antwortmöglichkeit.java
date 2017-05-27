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
public class Antwortmöglichkeit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer antwortID;
    private String antwortText;
    private String antwortHäufigkeit;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "frageID")
    @JsonBackReference
    private Frage frage;

}
