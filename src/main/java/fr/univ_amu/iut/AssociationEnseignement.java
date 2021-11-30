package fr.univ_amu.iut;

import fr.univ_amu.iut.beans.Etudiant;
import fr.univ_amu.iut.beans.Module;
import fr.univ_amu.iut.beans.Prof;

import java.util.HashSet;
import java.util.Objects;

public class AssociationEnseignement {

    private HashSet<Lien> liens;
    private static AssociationNotation instance;

    public void creerLien(Module module, Etudiant etudiant, Prof prof) {
        Lien t_lien = new Lien(module, etudiant);
        liens.add(t_lien);
    }

    public void supprimmerLien(Module module, Etudiant etudiant) {
        liens.removeIf(lien -> lien.hashCode() == Objects.hash(module, etudiant));
    }

    public void supprimmerLien(Lien lien) {
        liens.remove(lien);
    }

    public Lien getLien(Module module, Etudiant etudiant) {
        for (Lien lien : liens) {
            if (lien.hashCode() == Objects.hash(module, etudiant)) {
                return lien;
            }
        }
        return null;
    }

    public HashSet<Lien> getLiens() {
        return liens;
    }

    public HashSet<Module> getModules(Etudiant etudiant) {
        HashSet<Module> t_set = new HashSet<Module>();

        for (Lien lien : liens) {
            if (lien.getEtudiant() == etudiant) {
                t_set.add(lien.getModule());
            }
        }
        return t_set;
    }

    public HashSet<Etudiant> getEtudiants(Module module) {
        HashSet<Etudiant> t_set = new HashSet<Etudiant>();

        for (Lien lien : liens) {
            if (lien.getModule() == module) {
                t_set.add(lien.getEtudiant());
            }
        }
        return t_set;
    }

    public static AssociationNotation getInstance() {
        if (AssociationNotation.instance == null) {
            AssociationNotation.instance = new AssociationNotation();
        }
        return instance;
    }
}
