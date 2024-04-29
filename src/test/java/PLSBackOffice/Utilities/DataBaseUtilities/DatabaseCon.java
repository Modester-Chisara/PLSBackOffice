package PLSBackOffice.Utilities.DataBaseUtilities;

import java.sql.*;

import static PLSBackOffice.Utilities.ConfigReader.readProperty;
import static PLSBackOffice.Utilities.Credentials.db_environment;


public class DatabaseCon {
    static Connection connection;
    static ResultSet resultSet;
    static Statement statement;
    static ResultSetMetaData resultSetMetaData;

    /**
     * This method used for creating database connection.
     * Connection credentials in .properties file
     */
    public static void createDataBaseConnection() {
        String databaseURL = "";

        if (System.getProperty("db-env") == null) {
            databaseURL = readProperty("database_uat2");
        } else {
            databaseURL = db_environment(System.getProperty("env"));
        }

        String databaseUn = readProperty("databaseUsername");
        String databasePw = readProperty("databasePassword");

        try {
            connection = DriverManager.getConnection(databaseURL, databaseUn, databasePw);
            System.out.println("DATABASE CONNECTION SUCCESFULL");
        } catch (SQLException e) {
            System.err.println("DATABASE CONNECTION FAILED " + e.getMessage());

        }
    }

    /**
     * @param databaseURL argument for databaseURL credential
     * @param databaseUn  argument for databaseUn credential
     * @param databasePw  argument for databasePw credential
     *                    <p>
     *                    This method used for creating database connection.
     */
    public static void createDataBaseConnection(String databaseURL, String databaseUn, String databasePw) {
        try {
            connection = DriverManager.getConnection(databaseURL, databaseUn, databasePw);
            System.out.println("DATABASE CONNECTION SUCCESFULL");
        } catch (SQLException e) {
            System.err.println("DATABASE CONNECTION FAILED " + e.getMessage());

        }
    }

    /**
     * This method used for closing the database connection.
     */
    public static void closeDataBaseConnection() {
        try {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        } catch (SQLException exception) {
            System.err.println("ERROR OCCURED WHILE CLOSING DATABASE " + exception.getMessage());
        }
    }

    /**
     * This method used for run the dedicated query
     * Created statement by the connection.
     * Used statement to execute query and assign to resultset
     *
     * @param query
     * @return
     */
    public static ResultSet runQuery(String query) {
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(query);
            resultSetMetaData = resultSet.getMetaData();
        } catch (SQLException exception) {
            System.err.println("ERROR OCCURED WHILE RUNNING THE QUERY '" + query + "' " + exception);
        }
        return resultSet;
    }

}
