/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Usuario
 */
public class BorradorBases extends Application {

    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Vista/Login.fxml"));
        
        Scene scene = new Scene(root);
        stage.setTitle("Login");
        
        stage.setScene(scene);
        
        stage.setHeight(400);
        stage.setWidth(500);
        
        stage.setMaxHeight(400);
        stage.setMaxWidth(500);
        
        stage.setMinHeight(400);
        stage.setMinWidth(500);
        
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
