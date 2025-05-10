import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*************************************************************************************
 * Creates a Texture table from the Book DataBase created in sqlite.online
 * by connection with JDBC
 *
 * @Cynthia
 * @version CS1103
 * @date 4-8-2024
 *************************************************************************************/
public class TextureTable
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
            ResultSet rs = stmnt.executeQuery("SELECT smooth FROM Texture");
            printTable("Smooth", rs);

            rs = stmnt.executeQuery("SELECT soft FROM Texture");
            printTable("Soft", rs);

            rs = stmnt.executeQuery("SELECT hard FROM Texture");
            printTable("Hard", rs);

            rs = stmnt.executeQuery("SELECT bumpy FROM Texture");
            printTable("Bumpy", rs);

            rs = stmnt.executeQuery("SELECT rough FROM Texture");
            printTable("Rough", rs);

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
