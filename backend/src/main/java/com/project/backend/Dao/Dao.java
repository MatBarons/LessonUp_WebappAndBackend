package com.project.backend.Dao;
import com.project.backend.Exceptions.DaoExceptions;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.*;

public class Dao {
    //Encryption?
    static{
        registerDriver();
    }

    public Dao() {}

    private static void registerDriver() {
        try { // registrazione del driver JDBC per MySQL
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    private static Connection startConnection() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project?useSSL=false", "root",""); //cambiare!!!! useSSL=false
            if(con==null)
                throw new Error("Connection.connection.getConnection() : connessione non riuscita");
            else
                return con;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    private static void closeCon(Connection con) {
        try {
            con.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method executes query that returns data
     * @param query the query with or without placeholders
     * @param optionalArgs arguments to change the placeholder with (variable number of arguments allowed)
     * @return the data that was requested, in a CachedRowSet, null if the number of optional arguments was different from
     * the number of placeholders in the query
     * @throws DaoExceptions threw by if the number of placeholder doesn't match the number of arguments
     */
    public CachedRowSet launchQuery(String query,Object... optionalArgs) throws DaoExceptions {
        Connection con = null;
        CachedRowSet rowSet = null;
        PreparedStatement pst = null;

        if(!checkNumPlaceholder(query,optionalArgs.length)){
            throw new DaoExceptions("Number of optional arguments isn't the same as the number of placeholders in the query");
        }
        try{
            int i=1;
            con = startConnection();
            assert con != null;
            pst = con.prepareStatement(query);
            for(Object o : optionalArgs){
                pst.setObject(i,o);
                i++;
            }
            ResultSet rs = pst.executeQuery();
            rowSet = RowSetProvider.newFactory().createCachedRowSet();
            rs.beforeFirst();
            rowSet.populate(rs);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if(con!=null)
                closeCon(con);
            if(pst != null){
                try{
                    pst.close();
                }catch (SQLException e){
                    System.out.println(e.getMessage());
                }
            }
        }
        return rowSet;
    }

    /**
     *
     * This method executes query that has no returning data, such as Insert,Modify or Delete
     * @param query the query with or without placeholders
     * @param optionalArgs arguments to change the placeholder with (variabile number of arguments allowed)
     * @throws DaoExceptions threw by if the number of placeholder doesn't match the number of arguments
     */
    public void launchUpdate(String query,Object... optionalArgs) throws DaoExceptions{
        Connection con = null;
        PreparedStatement pst = null;
        if(!checkNumPlaceholder(query,optionalArgs.length)){
            throw new DaoExceptions("Number of optional arguments isn't the same as the number of placeholders in the query");
        }
        try {
            con = startConnection();
            assert con != null;
            pst = con.prepareStatement(query);
            int i=1;
            for(Object o : optionalArgs){
                pst.setObject(i,o);
                i++;
            }
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if(con!=null)
                closeCon(con);
            if(pst != null){
                try{
                    pst.close();
                }catch (SQLException e){
                    System.out.println(e.getMessage());
                }
            }
        }
        //return true;
    }

    /**
     * This method checks if the number of optional arguments passed to launchUpdate and launchQuery is the same as the number of placeholders '?'ù
     * in the query
     * @param query this is the query with placeholders
     * @param n this is the number of optionalArgs passed to the method this function is called from
     */
    private boolean checkNumPlaceholder(String query,int n){
        int num = 0;
        for(int i=0;i<query.length();i++){
            if(query.charAt(i) == '?')
                num++;
        }
        return n == num;
    }
}
