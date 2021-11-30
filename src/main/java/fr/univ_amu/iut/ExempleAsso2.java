package fr.univ_amu.iut;

import fr.univ_amu.iut.beans.Etudiant;
import fr.univ_amu.iut.beans.Module;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

// Importer les classes jdbc


public class ExempleAsso2 {

    public static Etudiant mkEtudiant(int num_et, Connection conn) throws SQLException {
        Statement statement = conn.createStatement();
        System.out.println("SELECT * FROM ETUDIANT WHERE NUM_ET = "+ num_et + ";");
        ResultSet resultSet = statement.executeQuery("SELECT * FROM ETUDIANT WHERE NUM_ET = "+ num_et + ";");
        while (resultSet.next()) {
            Etudiant t_et = new Etudiant();
            t_et.setNumEt(resultSet.getInt("NUM_ET"));
            t_et.setNomEt(resultSet.getString("NOM_ET"));
            t_et.setPrenomEt(resultSet.getString("PRENOM_ET"));
            t_et.setCpEt(resultSet.getString("CP_ET"));
            t_et.setVilleEt(resultSet.getString("VILLE_ET"));
            t_et.setAnnee(resultSet.getInt("ANNEE"));
            t_et.setGroupe(resultSet.getInt("GROUPE"));

            return t_et;
        }
        System.out.println("No match !");
        return null;
    }

    public static Module mkModule(String code, Connection conn) throws SQLException {
        Statement statement = conn.createStatement();
        System.out.println("SELECT * FROM MODULE WHERE CODE = '"+ code + "';");
        ResultSet resultSet = statement.executeQuery("SELECT * FROM MODULE WHERE CODE = '"+ code + "';");
        while (resultSet.next()) {
            Module t_mod = new Module();
            t_mod.setCode(resultSet.getString("CODE"));
            t_mod.setLibelle(resultSet.getString("LIBELLE"));
            t_mod.sethCoursPrev(resultSet.getInt("H_COURS_PREV"));
            t_mod.sethCoursRea(resultSet.getInt("H_COURS_REA"));
            t_mod.sethTpPrev(resultSet.getInt("H_TP_PREV"));
            t_mod.sethTpRea(resultSet.getInt("H_TP_REA"));
            t_mod.setDiscipline(resultSet.getString("DISCIPLINE"));
            t_mod.setCoefTest(resultSet.getInt("COEFF_TEST"));
            t_mod.setCoefCc(resultSet.getInt("COEFF_CC"));

            return t_mod;
        }
        System.out.println("No match !");
        return null;
    }



    public static void main(String[] args) throws SQLException {
        // La requete de test
        final String requete = "SELECT * FROM NOTATION;";

        // Connexion a la base de données
        ConnexionUnique connexion = ConnexionUnique.getInstance();

        try (Connection conn = connexion.getconnection();) {
            System.out.println("Connecte\n");

            // Creation d'une instruction SQL
            Statement statement = conn.createStatement();

            // Execution de la requete
            System.out.println("Execution de la requete : " + requete);
            ResultSet resultSet = statement.executeQuery(requete);

            AssociationNotation asso = AssociationNotation.getInstance();

            while (resultSet.next()) {

                // Creation de l'objet etudiant
                Etudiant t_et = mkEtudiant(resultSet.getInt("NUM_ET"), conn);

                // Creation de l'objet module
                Module t_mod = mkModule(resultSet.getString("CODE"), conn);

                // Creation de l'objet notation
                Notation t_not = new Notation();
                t_not.setMoyCC(resultSet.getInt("MOY_CC"));
                t_not.setMoyTest(resultSet.getInt("MOY_TEST"));

                // Creation du lien
                asso.creerLien(t_mod, t_et, t_not);
            }

            HashSet<Etudiant> acsi = asso.getEtudiants(mkModule("ACSI", conn));

            for (Etudiant et : acsi) {
                System.out.println(et);
            }

            // Fermeture de l'instruction (liberation des ressources)
            statement.close();
            System.out.println("\nOk.\n");
        } catch (SQLException e) {
            e.printStackTrace();// Arggg!!!
            System.out.println(e.getMessage() + "\n");
        }
    }
}
