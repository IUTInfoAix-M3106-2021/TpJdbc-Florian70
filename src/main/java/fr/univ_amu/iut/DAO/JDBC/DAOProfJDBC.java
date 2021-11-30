package fr.univ_amu.iut.DAO.JDBC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import fr.univ_amu.iut.ConnexionUnique;
import fr.univ_amu.iut.DAO.DAOProf;
import fr.univ_amu.iut.JDBC.ResultSetStreamer;
import fr.univ_amu.iut.JDBC.RowMappers.ProfMapper;
import fr.univ_amu.iut.beans.Prof;

public final class DAOProfJDBC implements DAOProf {
    private Connection connection;

    private ArrayList<Prof> extractProfs(ResultSet resultSet) throws SQLException {
        ArrayList<Prof> profs = new ArrayList<>();

        while (resultSet.next()) {
            Prof prof = new Prof();

            prof.setNumProf(resultSet.getInt("NUM_PROF"));
            prof.setNomProf(resultSet.getString("NOM_PROF"));
            prof.setPrenomProf(resultSet.getString("PRENOM_PROF"));
            prof.setAdrProf(resultSet.getString("ADR_PROF"));
            prof.setCpProf(resultSet.getString("CP_PROF"));
            prof.setVilleProf(resultSet.getString("VILLE_PROF"));
            prof.setMatSpec(resultSet.getString("MATSPEC"));


            profs.add(prof);
        }


        return profs;
    }

    public DAOProfJDBC() {
        try {
            connection = ConnexionUnique.getInstance().getconnection();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public int computeNbProf() throws SQLException {
        String query = "SELECT COUNT(*) TOTAL FROM PROF";

        Statement statement = connection.createStatement();

        System.out.println("Execution de la requete : " + query);
        ResultSet resultSet = statement.executeQuery(query);

        connection.close();
        return resultSet.getInt("TOTAL");
    }

    public List<Prof> findByNom(String nomProf) throws SQLException {
        String query = "SELECT NUM_PROF, NOM_PROF, PRENOM_PROF, CP_PROF, ADR_PROF, VILLE_PROF, MATSPEC " +
                "FROM PROF " +
                "WHERE NOM_PROF = " + nomProf + ";";

        Statement statement = connection.createStatement();

        System.out.println("Execution de la requete : " + query);
        ResultSet resultSet = statement.executeQuery(query);

        connection.close();

        return extractProfs(resultSet);
    }

    public List<Prof> findMatSpec(Module matSpec) throws SQLException {
        String query = "SELECT NUM_PROF, NOM_PROF, PRENOM_PROF, CP_PROF, ADR_PROF, VILLE_PROF, MATSPEC " +
                "FROM PROF " +
                "WHERE MATSPEC = " + matSpec + ";";

        Statement statement = connection.createStatement();

        System.out.println("Execution de la requete : " + query);
        ResultSet resultSet = statement.executeQuery(query);

        connection.close();

        return extractProfs(resultSet);
    }

    @Override
    public boolean delete(Prof obj) {
        try {
            String query = "DELETE FROM PROF WHERE NUM_PROF = " + obj.getNumProf();

            Statement statement = connection.createStatement();

            System.out.println("Execution de la requete : " + query);

            statement.executeUpdate(query);

            connection.close();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Prof> findAll() {
        return ResultSetStreamer.stream(connection, "SELECT * FROM PROF", new ProfMapper()).collect(Collectors.toList());
    }

    @Override
    public Prof getById(int id) {
        try {
            connection = ConnexionUnique.getInstance().getconnection();

            String query = "SELECT NUM_PROF, NOM_PROF, PRENOM_PROF, CP_PROF, ADR_PROF, VILLE_PROF, MATSPEC " +
                    "FROM PROF " +
                    "WHERE NOM_PROF = " + id + ";";

            Statement statement = connection.createStatement();

            System.out.println("Execution de la requete : " + query);

            ResultSet resultSet = statement.executeQuery(query);

            connection.close();

            return extractProfs(resultSet).get(0);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Prof insert(Prof obj) {
        try {
            String query = "INSERT INTO PROF (NUM_PROF, NOM_PROF, PRENOM_PROF, CP_PROF, ADR_PROF, VILLE_PROF, MATSPEC) VALUES('"
                    + obj.getNomProf() + "','"
                    + obj.getPrenomProf() + "','"
                    + obj.getCpProf() + "','"
                    + obj.getAdrProf() + "','"
                    + obj.getVilleProf() + "','"
                    + obj.getMatSpec() + "','";

            Statement statement = connection.createStatement();

            System.out.println("Execution de la requete : " + query);
            statement.executeUpdate(query);

            query = "SELECT NUM_PROF, NOM_PROF, PRENOM_PROF, CP_PROF, ADR_PROF, VILLE_PROF, MATSPEC FROM PROF WHERE NUM_PROF IN (SELECT MAX(NUM_PROF) FROM PROF);";

            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(query);

            return this.extractProfs(resultSet).get(0);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean update(Prof obj) {
        try {
            connection = ConnexionUnique.getInstance().getconnection();

            String query = "INSERT INTO PROF VALUES(" +
                    obj.getNomProf() + "','"
                    + obj.getPrenomProf() + "','"
                    + obj.getCpProf() + "','"
                    + obj.getAdrProf() + "','"
                    + obj.getVilleProf() + "','"
                    + obj.getMatSpec() + "','";

            Statement statement = connection.createStatement();

            System.out.println("Execution de la requete : " + query);
            statement.executeUpdate(query);

            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Prof> findMatSpec(fr.univ_amu.iut.beans.Module matSpec) {
        // TODO Auto-generated method stub
        return null;
    }
}