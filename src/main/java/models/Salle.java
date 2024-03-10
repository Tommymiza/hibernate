package models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "salle")
public class Salle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codesalle")
    private Long codesalle;

    @Column(name = "designation")
    private String designation;

    @OneToMany(mappedBy = "salle", cascade = CascadeType.ALL)
    private List<Occuper> occupations;

    public Long getCodesalle() {
        return codesalle;
    }

    public void setCodesalle(Long codesalle) {
        this.codesalle = codesalle;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Salle(String designation) {
        this.designation = designation;
    }
    public Salle(Long codesalle, String designation) {
        this.codesalle = codesalle;
        this.designation = designation;
    }

    public Salle() {}
}
