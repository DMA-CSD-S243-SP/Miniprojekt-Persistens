package database;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class testConnection 
{
    private static DBConnection dbConnection;

    @BeforeAll
    public static void setUp() 
    {
        dbConnection = DBConnection.getInstance();
    }

    @AfterAll
    public static void tearDown() 
    {
        dbConnection.disconnect();
    }

    @Test
    public void testConnection() 
    {
        Connection connection = dbConnection.getConnection();
        assertNotNull(connection, "Connection should not be null");
        
        try 
        {
            assertTrue(!connection.isClosed(), "Connection should be open");
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
            throw new RuntimeException("Error checking the database connection status", e);
        }
    }
}
