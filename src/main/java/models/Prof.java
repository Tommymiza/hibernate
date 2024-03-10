package models;

import jakarta.persistence.*;
import type.Grade;

import java.util.List;

@Entity
@Table(name = "prof")
public class Prof {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codeprof")
    private Long codeprof;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Enumerated(EnumType.STRING)
    @Column(name = "grade")
    private Grade grade;

    @OneToMany(mappedBy = "professeur", cascade = CascadeType.ALL)
    private List<Occuper> occupations;

    public Long getCodeprof() {
        return codeprof;
    }

    public void setCodeprof(Long codeprof) {
        this.codeprof = codeprof;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public Prof(String nom, String prenom, Grade grade) {
        this.nom = nom;
        this.prenom = prenom;
        this.grade = grade;
    }
    public Prof(Long codeprof, String nom, String prenom, Grade grade) {
        this.codeprof = codeprof;
        this.nom = nom;
        this.prenom = prenom;
        this.grade = grade;
    }

    public Prof() {}
}

