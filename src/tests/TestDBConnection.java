package tests;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import database.DBConnection;


/**
 * This class tests whether a connection to the database can be successfully established.
 * 
 * 
 * @author Anders Have
 * @version 13/03/2025 - 16:11
 */
public class TestDBConnection 
{
    private static DBConnection dbConnection;

    
    /**
     * Sets up the database connection before running the tests.
     */
    @BeforeAll
    public static void setUp()
    {
        dbConnection = DBConnection.getInstance();
    }

    
    /**
     * Closes the database connection after all tests have been executed.
     */
    @AfterAll
    public static void tearDown()
    {
        dbConnection.disconnect();
    }

    
    /**
     * Tests whether a connection to the database is successfully established and remains open.
     */
    @Test
    public void testConnection()
    {
        Connection connection = dbConnection.getConnection();

        assertNotNull(connection, "Connection should not be null");

        try 
        {
            assertTrue(!connection.isClosed(), "Connection should be open");
        }
        
        catch (SQLException exception) 
        {
            exception.printStackTrace();
            throw new RuntimeException("Error checking the database connection status", exception);
        }
    }
}