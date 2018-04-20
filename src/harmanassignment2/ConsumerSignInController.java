package harmanassignment2;

import java.io.IOException;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *Controller for sign in a consumer
 * @author Harmandeep
 */
public class ConsumerSignInController implements Initializable {
    @FXML    private PasswordField signinPWD;
    @FXML    private TextField signinEmail;
    @FXML    private Label statusLabel;
    @FXML    private Button sceneChanger;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        statusLabel.setText("");
        sceneChanger.setDisable(true);
    } 
    
     public void shopNowButton() throws SQLException{
         String consumerEmail = signinEmail.getText();
        
         String encryptedDBpwd = null;
         byte[] DBsalt = null; 
         int saltLength;
         
         
        Connection conn=null;
        PreparedStatement ps = null;
        ResultSet resultset = null;
        
           try
        {
            //1. Connect to the database
            conn = DriverManager.getConnection("jdbc:mysql://aws.computerstudi.es:3306/"  + "gc200350932", "gc200350932", "otXVb12s1e");
                    
            //2.  Prepare the query
            ps = conn.prepareStatement("select * from consumers where email = '" + consumerEmail + "'");
            
            resultset = ps.executeQuery();
            //4 get the password and the salt
            while(resultset.next()){
            encryptedDBpwd = resultset.getString("consumerPassword");
            
            Blob salt = resultset.getBlob("salt");
            saltLength = (int) salt.length();
            
            DBsalt = salt.getBytes(1, saltLength);           
        }
            
          String newEncryptedPassword = passwordCheck.getPW(signinPWD.getText(), DBsalt); 
          
          //second lambda
          UnaryOperator<String> LoginStatus = x -> "Login Status : ".concat(x);
          
           if (newEncryptedPassword.equals(encryptedDBpwd)){
                statusLabel.setText(LoginStatus.apply("Successful"));
                sceneChanger.setDisable(false);
           }
           else{
               statusLabel.setText(LoginStatus.apply("unsuccessful"));
               sceneChanger.setDisable(true);
           }
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
     
     public void showInventory(ActionEvent event) throws IOException{
         
         //to change the scenes
      
      Parent products = FXMLLoader.load(getClass().getResource("productsTableView.fxml"));
      Scene scene = new Scene(products);
      
      //this line will get the stage Information
      Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
      
      //this will set the scene
      window.setScene(scene);
      
      window.setTitle("Products");
      
      window.show();
         
     }
}
