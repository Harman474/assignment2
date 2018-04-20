package harmanassignment2;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 * controller to add a new COnsumer or user
 * @author Harmandeep
 */
public class AddNewConsumerController implements Initializable {
    @FXML    private TextField consumerNameFeild;
    @FXML    private TextField emailFeild;
    @FXML    private PasswordField passwordFeild;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }  
    
    
    public void registerButton(ActionEvent event) throws NoSuchAlgorithmException, SQLException, IOException{
        Consumers consumer = new Consumers (this.consumerNameFeild.getText(),
                                            this.emailFeild.getText(), 
                                            this.passwordFeild.getText());
        
        consumer.insertConsumersIntoDB();
        
      Parent signInView = FXMLLoader.load(getClass().getResource("consumerSignIn.fxml"));
      Scene scene = new Scene(signInView);
      
      //this line will get the stage Information
      Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
      
      //this will set the scene
      window.setScene(scene);
      
      window.setTitle("Sign In");
      
      window.show();
    }
    public void alreadyRegistered(ActionEvent event) throws IOException{
      Parent signInView = FXMLLoader.load(getClass().getResource("consumerSignIn.fxml"));
      Scene scene = new Scene(signInView);
      
      //this line will get the stage Information
      Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
      
      //this will set the scene
      window.setScene(scene);
      
      window.setTitle("Sign In");
      
      window.show();
    }
    
    
}
