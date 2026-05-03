package com.example.grading.dto;

public class NoteDTO {

    private Long id;
    private Long studentId;
    private String matiere;
    private Double valeur;

    public NoteDTO() {}

    public NoteDTO(Long id, Long studentId, String matiere, Double valeur) {
        this.id = id;
        this.studentId = studentId;
        this.matiere = matiere;
        this.valeur = valeur;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }

    public String getMatiere() { return matiere; }
    public void setMatiere(String matiere) { this.matiere = matiere; }

    public Double getValeur() { return valeur; }
    public void setValeur(Double valeur) { this.valeur = valeur; }
}