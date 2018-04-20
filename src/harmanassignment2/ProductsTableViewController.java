package harmanassignment2;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.util.stream.Collectors.toList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 * Controller Class to view all the products
 * @author harmandeep
 */
public class ProductsTableViewController implements Initializable {
    @FXML    private TableView<products> tableView;
    @FXML    private TableColumn<products, String> productMakeColumn;
    @FXML    private TableColumn<products, String> productColumn;
    @FXML    private TableColumn<products, String> productType;
    @FXML    private TableColumn<products, Integer> productPrice;
    ObservableList<products> allProducts = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.productMakeColumn.setCellValueFactory(new PropertyValueFactory<products, String>("make"));
        this.productColumn.setCellValueFactory(new PropertyValueFactory<products, String>("productName"));
        this.productType.setCellValueFactory(new PropertyValueFactory<products, String>("productType"));
        this.productPrice.setCellValueFactory(new PropertyValueFactory<products, Integer>("price"));
        
        try {
            fillTable();
        } catch (SQLException ex) {
            Logger.getLogger(ProductsTableViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    
    public void fillTable() throws SQLException{
        Connection conn=null;
        PreparedStatement ps = null;
        
         try
        {
            //1. Connect to the database
            conn = DriverManager.getConnection("jdbc:mysql://aws.computerstudi.es:3306/"  + "gc200350932", "gc200350932", "otXVb12s1e");
            
            //2.  Prepare the query
            ps = conn.prepareStatement("select * from products");
            ResultSet resultset = ps.executeQuery();
            
            
        
            //3 add the values in the scene
            
            while(resultset.next()){
               allProducts.add(new products(resultset.getString("make"), resultset.getString("productName"), resultset.getString("productType"), resultset.getInt("price")));
               }
         
            
           //4.Add the items to the table
           tableView.getItems().addAll(allProducts);
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
    public void sortTable(){
        System.out.println("----------------------------------------------------------------");
        System.out.println("My stream Showcase Which shows one lambda");
        System.out.println("----------------------------------------------------------------");
        allProducts.stream().filter(e -> e.getPrice() > 1).forEach(e -> System.out.println(e.getMake()));
        System.out.println("----------------------------------------------------------------");
        
        //it is my third consumer lambda it takes a value and doesn't return anyhting
        Consumer<String> thirdLambda = (e)->System.out.println(e.toUpperCase());
        thirdLambda.accept("Here is my third lambda");
    }
    

}
