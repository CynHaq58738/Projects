import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;

/*************************************************************************************
 * Creates separate tables for each category from the Book DataBase created in sqlite.online
 * by connection with JDBC
 *
 * @Cynthia
 * @version CS1103
 * @date 4-8-2024
 *************************************************************************************/
public class TypeTable 
{
    public static void main(String[] args)
    {
        Connection conn = null;
        try 
        {
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Connect to the database
            conn = DriverManager.getConnection("jdbc:sqlite:sqlite.db");

            // Create a statement
            Statement stmnt = conn.createStatement();

            // Execute a SELECT query for each category
            ResultSet rs = stmnt.executeQuery("SELECT graphic_novel FROM TYPE");
            printTable("Graphic Novel", rs);

            rs = stmnt.executeQuery("SELECT action FROM TYPE");
            printTable("Action", rs);

            rs = stmnt.executeQuery("SELECT fiction FROM TYPE");
            printTable("Fiction", rs);

            rs = stmnt.executeQuery("SELECT non_fiction FROM Type");
            printTable("Non-Fiction", rs);

            rs = stmnt.executeQuery("SELECT sci_fi FROM TYPE");
            printTable("Sci-Fi", rs);

            rs.close(); // Close the result set
        } catch (SQLException | ClassNotFoundException e) 
        {
            e.printStackTrace();
        } finally
        {
            try 
            {
                if (conn != null)
                {
                    conn.close();
                }
            } catch (SQLException e) 
            {
                e.printStackTrace();
            }
        }
    }

    // Method to print a table for a specific category
    private static void printTable(String category, ResultSet rs) throws SQLException 
    {
        System.out.println("Column for: " + category);
        System.out.println("----------------------------------");
        while (rs.next()) 
        {
            System.out.println(rs.getString(1));
        }
        System.out.println("----------------------------------");
        System.out.println();
    }
}
