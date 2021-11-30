package fr.univ_amu.iut;

import fr.univ_amu.iut.Notation;
import fr.univ_amu.iut.beans.Etudiant;
import fr.univ_amu.iut.beans.Module;


import java.util.Objects;

public class Lien {
    private Module module;
    private Etudiant etudiant;
    private Notation note;

    public Lien(Module module, Etudiant etudiant) {
        this.module = module;
        this.etudiant = etudiant;
    }


    public Module getModule() {
        return module;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public Notation getNotation() {
        return note;
    }

    public void setNotation(Notation note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lien lien = (Lien) o;
        return Objects.equals(module, lien.module) && Objects.equals(etudiant, lien.etudiant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(module, etudiant);
    }
}