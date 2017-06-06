package de.umfrage.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @OneToMany(mappedBy = "ersteller",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Umfrage> umfragen;
}
