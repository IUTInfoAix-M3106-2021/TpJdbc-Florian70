package fr.univ_amu.iut;

// Importer les classes jdbc
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

import fr.univ_amu.iut.beans.Prof;
import fr.univ_amu.iut.beans.Module;

public class ExempleAsso1 {
    // La requete de test
    private static final String reqEtudiantsAixois =
            "SELECT NUM_PROF, NOM_PROF, PRENOM_PROF, CP_PROF, VILLE_PROF, ADR_PROF, MAT_SPEC  " +
                    "FROM PROF " +
                    "WHERE VILLE_PROF = 'AIX-EN-PROVENCE'";

    public static void main(String[] args) throws SQLException {
        // Connexion a la base de données
        System.out.println("Connexion");
        try (Connection conn = ConnexionUnique.getInstance().getConnection()) {
            System.out.println("Connecte\n");
            
            // Creation d'une instruction SQL
            Statement statement = conn.createStatement();
            
            // Execution de la requete
            System.out.println("Execution de la requete : " + reqEtudiantsAixois);
            ResultSet resultSet = statement.executeQuery(reqEtudiantsAixois);
            
            // Affichage du resultat
            ArrayList<Prof> listProf = new ArrayList<Prof>();
            while (resultSet.next()) {

                Prof prof = new Prof(); 
                prof.setNumProf(resultSet.getInt("NUM_PROF"));
                prof.setNomProf(resultSet.getString("NOM_PROF"));
                prof.setPrenomProf(resultSet.getString("PRENOM_PROF"));
                prof.setCpProf(resultSet.getString("CP_PROF"));
                prof.setVilleProf(resultSet.getString("VILLE_PROF"));
                prof.setAdrProf(resultSet.getString("ADR_PROF"));

            
                Module specialite = new Module();
                specialite.setCode(resultSet.getString("MAT_SPEC"));
                prof.setMatSpec(specialite);

                listProf.add(prof);
                System.out.println(prof.toString()+" "+specialite.toString());
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
