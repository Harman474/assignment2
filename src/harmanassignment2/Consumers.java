package harmanassignment2;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *Model class for controller of both sign in and addnewConsumer
 * @author Harmandeep
 */
public class Consumers {
    private String consumerName, email, password;
    private byte[] salt;

    public Consumers(String UserName, String email, String password) throws NoSuchAlgorithmException {
        this.consumerName = UserName;
        this.email = email;
        
        salt = passwordCheck.getSalt();
        this.password = passwordCheck.getPW(password, salt);
    }

    public String getConsumerName() {
        return consumerName;
    }

    public void setUserName(String UserName) {
        this.consumerName = UserName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
         public void insertConsumersIntoDB() throws SQLException
    {
        Connection conn=null;
        PreparedStatement ps = null;
        
        try
        {
            //1. Connect to the database
            conn = DriverManager.getConnection("jdbc:mysql://aws.computerstudi.es:3306/"  + "gc200350932", "gc200350932", "otXVb12s1e");
            
            //2. Create a String to hold the SQL statement.  ? will be our
            //place holders
            String sql = "INSERT INTO consumers(consumerName, email, consumerPassword, salt) VALUES (?,?,?,?)";
            
            //3.  Prepare the query
            ps = conn.prepareStatement(sql);
            
            //4. Bind the parameters
            ps.setString(1, this.consumerName);
            ps.setString(2, this.email);
            ps.setString(3, this.password);
            ps.setBlob(4, new javax.sql.rowset.serial.SerialBlob(salt));
                       
            //5. execute the sql statement
            ps.execute();
        }
        catch (SQLException e)
        {
            System.err.println(e);
        }
        finally
        {
            if (conn != null)
                conn.close();
            if (ps != null)
                ps.close();
        }
    }
    
}
