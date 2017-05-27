package de.umfrage.entity;

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
public class Ersteller {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer erstellerID;
    private String name;
    private String email;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ersteller")
    private List<Umfrage> umfragen;
}
