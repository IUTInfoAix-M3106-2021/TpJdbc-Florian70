package fr.univ_amu.iut.DAO.JDBC;

import fr.univ_amu.iut.ConnexionUnique;
import fr.univ_amu.iut.DAO.DAOEtudiant;
import fr.univ_amu.iut.JDBC.ResultSetStreamer;
import fr.univ_amu.iut.JDBC.RowMappers.EtudiantMapper;
import fr.univ_amu.iut.beans.Etudiant;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class DAOEtudiantJDBC implements DAOEtudiant {
    private Connection connection;

    DAOEtudiantJDBC() {
        try {
            connection = ConnexionUnique.getInstance().getConnection();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        };
    }

    @Override
    public int computeNbEtudiant() {
        ArrayList<Etudiant> promo = new ArrayList<Etudiant>();
        String reqEtudiants = "SELECT NUM_ET, NOM_ET, PRENOM_ET, CP_ET, VILLE_ET, ANNEE, GROUPE  " + "FROM ETUDIANT";
        Statement statement;
        int nb = 0;
        try {
            statement = connection.createStatement();

            // Execution de la requete
            System.out.println("Execution de la requete : " + reqEtudiants);
            ResultSet resultSet = statement.executeQuery(reqEtudiants);

            // Affichage du resultat

            while (resultSet.next()) {
                nb++;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return nb;
    }


    @Override
    public List<Etudiant> findByAnnee(int annee) {
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

                if (resultSet.getInt("ANNEE") == annee) {
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


            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return promo;
    }
    @Override
    public List<Etudiant> findByGroupe(int groupe) {
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

                if (resultSet.getInt("GROUPE") == groupe) {
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


            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return promo;
    }

    @Override
    public List<Etudiant> findByNom(String nomEt) {
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

                if (resultSet.getString("NOM_ET") == nomEt) {
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


            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return promo;
    }

    @Override
    public boolean delete(Etudiant obj) {
        String reqEtudiants = "DELETE " + "FROM ETUDIANT" + "WHERE 'id' ="+ obj.getNumEt() ;
        Statement statement;

        try {
            statement = connection.createStatement();

            // Execution de la requete
            System.out.println("Execution de la requete : " + reqEtudiants);
            ResultSet resultSet = statement.executeQuery(reqEtudiants);

            // Affichage du resultat

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
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

                if (resultSet.getInt("NUM_ET") == id) {
                    Etudiant et = new Etudiant();

                    et.setNumEt(resultSet.getInt("NUM_ET"));
                    et.setNomEt(resultSet.getString("NOM_ET"));
                    et.setPrenomEt(resultSet.getString("PRENOM_ET"));
                    et.setCpEt(resultSet.getString("CP_ET"));
                    et.setVilleEt(resultSet.getString("VILLE_ET"));
                    et.setAnnee(resultSet.getInt("ANNEE"));
                    et.setGroupe(resultSet.getInt("GROUPE"));

                    return et;
                }


            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return promo.get(0);
    }

    @Override
    public Etudiant insert(Etudiant obj) {
        ArrayList<Etudiant> promo = new ArrayList<Etudiant>();
        String reqEtudiants = "INSERT INTO ETUDIANT VALUES ("+obj.getNumEt()+","+obj.getNomEt()+","+obj.getPrenomEt()+","+obj.getCpEt()+","+obj.getVilleEt()+","+obj.getAnnee()+","+obj.getGroupe()+")";
        Statement statement;
        Etudiant et = new Etudiant();
        try {
            statement = connection.createStatement();

            // Execution de la requete
            System.out.println("Execution de la requete : " + reqEtudiants);
            ResultSet resultSet = statement.executeQuery(reqEtudiants);

            // Affichage du resultat




            et.setNumEt(obj.getNumEt());
            et.setNomEt(obj.getNomEt());
            et.setPrenomEt(obj.getPrenomEt());
            et.setCpEt(obj.getCpEt());
            et.setVilleEt(obj.getVilleEt());
            et.setAnnee(obj.getAnnee());
            et.setGroupe(obj.getGroupe());
            promo.add(et);


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return et;
    }

    @Override
    public boolean update(Etudiant obj) {
        ArrayList<Etudiant> promo = new ArrayList<Etudiant>();
        String reqEtudiants = "UPDATE ETUDIANT SET (NUM_ET ="+obj.getNumEt()+", NOM_ET ="+obj.getNomEt()+", PRENOM_ET = "+obj.getPrenomEt()+", CP_ET ="+obj.getCpEt()+", VILLE_ET = "+obj.getVilleEt()+", ANNEE ="+obj.getAnnee()+", GROUPE ="+obj.getGroupe()+" WHERE NUM_ET ="+obj.getNumEt();
        Statement statement;
        Etudiant et = new Etudiant();
        try {
            statement = connection.createStatement();

            // Execution de la requete
            System.out.println("Execution de la requete : " + reqEtudiants);
            ResultSet resultSet = statement.executeQuery(reqEtudiants);

            // Affichage du resultat


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
