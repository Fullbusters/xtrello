package com.xtrello.dao;


import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Клас в якому реалізовано з'єднання з базою даних
 */
public class DataSource {
    Connection connection = null;

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL="jdbc:mysql://localhost/example?user=user&password=1122";


    public DataSource()
    {

        try{
            Class.forName(JDBC_DRIVER).newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }

    public Connection createConnection()
    {
        Connection con = null;
        try
        {

            if( connection != null )
           {
                System.out.println("Cant create a New Connection");
            }
            else
            {
                con = DriverManager.getConnection(DB_URL);

            }
        }
        catch( Exception e )
        {
            System.out.println("Error Occured " + e.toString());
        }
        return con;
    }

}
