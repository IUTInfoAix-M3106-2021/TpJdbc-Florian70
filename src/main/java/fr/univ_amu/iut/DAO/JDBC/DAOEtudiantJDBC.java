package fr.univ_amu.iut.DAO.JDBC;

import fr.univ_amu.iut.ConnexionUnique;
import fr.univ_amu.iut.DAO.DAOEtudiant;
import fr.univ_amu.iut.JDBC.ResultSetStreamer;
import fr.univ_amu.iut.JDBC.RowMappers.EtudiantMapper;
import fr.univ_amu.iut.beans.Etudiant;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public final class DAOEtudiantJDBC implements DAOEtudiant {
    private Connection connection;

    DAOEtudiantJDBC() {
        try {
            connection = ConnexionUnique.getInstance().getConnection()
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        };
    }

    @Override
    public int computeNbEtudiant() {
        return 0;
    }

    @Override
    public List<Etudiant> findByAnnee(int annee) {
        return null;
    }

    @Override
    public List<Etudiant> findByGroupe(int groupe) {
        return null;
    }

    @Override
    public List<Etudiant> findByNom(String nomEt) {
        return null;
    }

    @Override
    public boolean delete(Etudiant obj) {
        return false;
    }

    @Override
    public List<Etudiant> findAll() {
        ArrayList<Etudiant> promo = new ArrayList<Etudiant>();
        String reqEtudiants = "SELECT NUM_ET, NOM_ET, PRENOM_ET, CP_ET, VILLE_ET, ANNEE, GROUPE  " + "FROM ETUDIANT";
        Statement statement;
        try {
            statement = connection.createStatement();

            // Execution de la requete
            System.out.println("Execution de la requete : " + reqEtudiants);
            ResultSet resultSet = statement.executeQuery(reqEtudiants);

            // Affichage du resultat

            while (resultSet.next()) {

                Etudiant et = new Etudiant();

                et.setNumEt(resultSet.getInt("NUM_ET"));
                et.setNomEt(resultSet.getString("NOM_ET"));
                et.setPrenomEt(resultSet.getString("PRENOM_ET"));
                et.setCpEt(resultSet.getString("CP_ET"));
                et.setVilleEt(resultSet.getString("VILLE_ET"));
                et.setAnnee(resultSet.getInt("ANNEE"));
                et.setGroupe(resultSet.getInt("GROUPE"));

                promo.add(et);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return promo;
    }

    @Override
    public Etudiant getById(int id) {
        return null;
    }

    @Override
    public Etudiant insert(Etudiant obj) {
        return null;
    }

    @Override
    public boolean update(Etudiant obj) {
        return false;
    }
}
