import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*******************************************************************************
 * Creates a Book table from the Book Database created in sqlite.online
 * by connection with JDBC
 *
 * @Cynthia
 * @version CS1103
 * @date 4-8-2024
 *******************************************************************************/
public class BookTable 
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
            ResultSet rs = stmnt.executeQuery("SELECT ISBN FROM Book");
            printTable("ISBN", rs);

            rs = stmnt.executeQuery("SELECT title FROM Book");
            printTable("Title", rs);

            rs = stmnt.executeQuery("SELECT author FROM Book");
            printTable("Author", rs);

            rs = stmnt.executeQuery("SELECT year FROM Book");
            printTable("Year", rs);
                
            rs = stmnt.executeQuery("SELECT  num_pages FROM Book");
            printTable("Number Of Pages", rs);
            
            rs = stmnt.executeQuery("SELECT  publisher FROM Book");
            printTable("Publisher", rs);
            
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
        //print the value of the first column and set to string
        while (rs.next()) 
        {
            System.out.println(rs.getString(1));
        }
        System.out.println("----------------------------------");
        System.out.println();
    }
}
