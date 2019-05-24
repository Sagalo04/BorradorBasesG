/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Control.ControlComentario;
import Control.ControlLikeImagen;
import Control.LoginController;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 *
 * @author User
 */
public class ViewPlantilla {

    private int Id_Plantilla;
    private Timestamp Fecha;
    private String NombreCuenta;

    private Button button;

    public ViewPlantilla(int Id_Plantilla, Timestamp Fecha) {
        this.Id_Plantilla = Id_Plantilla;
        this.Fecha = Fecha;
        
        this.button = new Button("Ver");

        this.button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent e) {

                openNewImageWindow(Id_Plantilla);

            }
        });
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }
    
    

    public int getId_Plantilla() {
        return Id_Plantilla;
    }

    public void setId_Plantilla(int Id_Plantilla) {
        this.Id_Plantilla = Id_Plantilla;
    }

    public Timestamp getFecha() {
        return Fecha;
    }

    public void setFecha(Timestamp Fecha) {
        this.Fecha = Fecha;
    }

    public String getNombreCuenta() {
        return NombreCuenta;
    }

    public void setNombreCuenta(String NombreCuenta) {
        this.NombreCuenta = NombreCuenta;
    }


    @Override
    public String toString() {
        return "ViewPlantilla{" + "Id_Plantilla=" + Id_Plantilla + ", Fecha=" + Fecha + ", NombreCuenta=" + NombreCuenta + '}';
    }
    
        private void openNewImageWindow(int id) {
        Stage secondStage = new Stage();

        MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu("File");
        MenuItem menuItem_Save = new MenuItem("Save Image");
        menuFile.getItems().addAll(menuItem_Save);
        menuBar.getMenus().addAll(menuFile);

        ImageView imageView = new ImageView();

        menuItem_Save.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Save Image");

                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PNG", "*.png");
                fileChooser.getExtensionFilters().add(extFilter);
                //setExtFilters(fileChooser);

                File file = fileChooser.showSaveDialog(secondStage);
                if (file != null) {
                    try {
                        ImageIO.write(SwingFXUtils.fromFXImage(imageView.getImage(),
                                null), "png", file);
                    } catch (IOException ex) {
                        Logger.getLogger(
                                Imagen.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        
        ConnectBD cc = new ConnectBD();
        boolean f = false;

        String sql = "SELECT p.plantilla FROM plantilla p WHERE p.id_Plantilla = '" + id + "' ; ";
        Image img = null;
        if (cc.crearConexion()) {
            try {
                Statement pst = cc.getConexion().createStatement();
                ResultSet rs = pst.executeQuery(sql);
                rs.first();
                do {
                    byte byteImage[] = null;
                    Blob blob = rs.getBlob(1);
                    byteImage = blob.getBytes(1, (int) blob.length());
                    img = new Image(new ByteArrayInputStream(byteImage));
                    imageView.setImage(img);
                } while (rs.next());

                f = true;

            } catch (SQLException ex) {
                System.out.println(ex);
                f = false;
            }
        }

        final VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(0, 10, 0, 10));
        vbox.getChildren().addAll(imageView);

        URL linkLike = getClass().getResource("/Assets/like.png");

        Image imagenLike = new Image(linkLike.toString(), 24, 24, false, true);

        imageView.setFitHeight(400);
        imageView.setPreserveRatio(true);
        imageView.setImage(img);
        imageView.setSmooth(true);
        imageView.setCache(true);

        //Scene scene = new Scene(new VBox(), 600, 615);
        Scene scene = new Scene(new VBox(), 600, 615);

        ((VBox) scene.getRoot()).getChildren().addAll(menuBar, vbox);

        secondStage.setTitle("Title");
        secondStage.setScene(scene);

        secondStage.setHeight(600);
        secondStage.setWidth(750);

        secondStage.setMaxHeight(600);
        secondStage.setMaxWidth(750);

        secondStage.setMinHeight(600);
        secondStage.setMinWidth(750);
        secondStage.show();

    }

    private static void setExtFilters(FileChooser chooser) {
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
    }

}
