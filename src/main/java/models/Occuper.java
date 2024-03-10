package models;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.time.LocalDateTime;

@Entity
@Table(name = "occuper")
public class Occuper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "codeprof")
    private Prof professeur;

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "codesalle")
    private Salle salle;

    @Column(name="date")
    private LocalDateTime date;

    // Autres propriétés, constructeurs, getters et setters

    public Occuper() {
    }

    public Occuper(Prof professeur, Salle salle, LocalDateTime date) {
        this.professeur = professeur;
        this.salle = salle;
        this.date = date;
    }

    public Occuper(Long id, Prof professeur, Salle salle, LocalDateTime date) {
        this.id = id;
        this.professeur = professeur;
        this.salle = salle;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public Prof getProfesseur() {
        return professeur;
    }

    public void setProfesseur(Prof professeur) {
        this.professeur = professeur;
    }

    public Salle getSalle() {
        return salle;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
