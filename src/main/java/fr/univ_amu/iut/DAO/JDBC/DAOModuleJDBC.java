package fr.univ_amu.iut.DAO.JDBC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import fr.univ_amu.iut.ConnexionUnique;
import fr.univ_amu.iut.DAO.DAOModule;
import fr.univ_amu.iut.JDBC.ResultSetStreamer;
import fr.univ_amu.iut.JDBC.RowMappers.ModuleMapper;
import fr.univ_amu.iut.beans.Module;
import fr.univ_amu.iut.beans.Prof;

public final class DAOModuleJDBC implements DAOModule {
    private Connection connection;

    private ArrayList<Module> extractModules(ResultSet resultSet) throws SQLException {
        ArrayList<Module> modules = new ArrayList<>();

        while (resultSet.next()) {
            Module module = new Module();

            module.setCode(resultSet.getString("CODE"));
            module.setLibelle(resultSet.getString("LIBELLE"));
            module.setDiscipline(resultSet.getString("DISCIPLINE"));
            module.setResponsable(resultSet.getString("RESPONSABLE"));            module.setPere(resultSet.getString("PERE"));

            modules.add(module);
        }
        return modules;
    }

    public DAOModuleJDBC() {
        try {
            connection = ConnexionUnique.getInstance().getconnection();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public List<Module> findByLibelle(String libelle) throws SQLException {
        String query = "SELECT CODE, LIBELLE, DISCIPLINE, RESPONSABLE, PERE " +
                "FROM MODULE " +
                "WHERE LIBELLE = " + libelle + ";";

        Statement statement = connection.createStatement();

        System.out.println("Execution de la requete : " + query);
        ResultSet resultSet = statement.executeQuery(query);

        connection.close();
        return extractModules(resultSet);
    }

    public List<Module> findByDiscipline(String discipline) throws SQLException {
        String query = "SELECT CODE, LIBELLE, DISCIPLINE, RESPONSABLE, PERE " +
                "FROM MODULE " +
                "WHERE DISCIPLINE = " + discipline + ";";

        Statement statement = connection.createStatement();

        System.out.println("Execution de la requete : " + query);
        ResultSet resultSet = statement.executeQuery(query);

        connection.close();
        return extractModules(resultSet);
    }


    public List<Module> findByResponsable(Prof Responsable) throws SQLException {
        String query = "SELECT CODE, LIBELLE, DISCIPLINE, RESPONSABLE, PERE " +
                "FROM MODULE " +
                "WHERE RESPONSABLE = " + Responsable + ";";

        Statement statement = connection.createStatement();

        System.out.println("Execution de la requete : " + query);
        ResultSet resultSet = statement.executeQuery(query);

        connection.close();
        return extractModules(resultSet);
    }

    @Override
    public boolean delete(Module obj) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<Module> findAll() {
        return ResultSetStreamer.stream(connection, "SELECT * FROM MODULE", new ModuleMapper()).collect(Collectors.toList());
    }

    @Override
    public Module getById(int id) {
        try {
            connection = ConnexionUnique.getInstance().getconnection();

            String query = "SELECT CODE, LIBELLE, DISCIPLINE, RESPONSABLE, PERE " +
                    "FROM MODULE " +
                    "WHERE CODE = " + id + ";";

            Statement statement = connection.createStatement();

            System.out.println("Execution de la requete : " + query);

            ResultSet resultSet = statement.executeQuery(query);

            connection.close();

            return extractModules(resultSet).get(0);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Module insert(Module obj) {
        try {
            String query = "INSERT INTO MODULE (CODE, LIBELLE, DISCIPLINE, RESPONSABLE, PERE) VALUES('"
                    + obj.getCode() + "','"
                    + obj.getLibelle() + "','"
                    + obj.getDiscipline() + "','"
                    + obj.getResponsable() + "','"
                    + obj.getPere() + "','";

            Statement statement = connection.createStatement();

            System.out.println("Execution de la requete : " + query);
            statement.executeUpdate(query);

            query = "SELECT CODE, LIBELLE, DISCIPLINE, RESPONSABLE, PERE FROM MODULE WHERE CODE IN (SELECT MAX(CODE) FROM MODULE);";

            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(query);

            return this.extractModules(resultSet).get(0);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean update(Module obj) {
        try {
            connection = ConnexionUnique.getInstance().getconnection();

            String query = "INSERT INTO MODULE VALUES(" +
                    obj.getCode() + "','"
                    + obj.getLibelle() + "','"
                    + obj.getDiscipline() + "','"
                    + obj.getResponsable() + "','"
                    + obj.getPere() + "','";

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
}
