/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import static Control.LoginController.usuario;
import Modelo.Audio;
import Modelo.ConnectBD;
import Modelo.Imagen;
import Modelo.Plantilla;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
//Looka this
public class MainController implements Initializable {

    @FXML
    MenuBar myMenuBar;

    @FXML
    AnchorPane PaneMain;

    @FXML
    Label MainLabel;

    @FXML
    TableView PublicacionesI;

    TableColumn<String, Imagen> fechaPublic;
    TableColumn<String, Imagen> ncuenta;
    TableColumn<String, Imagen> nombrecat;
    TableColumn<String, Imagen> Img;
    TableColumn<String, Imagen> ComenI;

    @FXML
    TableView PublicacionesA;
    
    TableColumn<String, Audio> fechaPublicA;
    TableColumn<String, Audio> ncuentaA;
    TableColumn<String, Audio> Aud;
    TableColumn<String, Audio> nombreCatA;
    TableColumn<String, Audio> ComenA;
    

    //Stage ya sea para cerrar sesion o para abrir archivo imagen/Audio
    Stage stage = new Stage();

    public static TableView tableview;
    public static TableView tableview2;

    //Ususario recibido por medio del Login
    public String Usuario;

    //Metodo para cerrar la sesion del usuario
    @FXML
    public void OnCerrarSesion(ActionEvent event) {

        try {
            Stage tstage = (Stage) myMenuBar.getScene().getWindow();
            tstage.hide();
            Parent parent = FXMLLoader.load(getClass().getResource("/Vista/Login.fxml"));

            stage.setHeight(400);
            stage.setWidth(500);

            stage.setMaxHeight(400);
            stage.setMaxWidth(500);

            stage.setMinHeight(400);
            stage.setMinWidth(500);

            Scene scene = new Scene(parent);
            stage.setTitle("Login");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println("ERROR " + e.toString());
        }
    }

    //No hace nada aún aunque deberia de abrir una ventana para cambiar los datos del usuario como contraseña y etc
    @FXML
    public void OnEditUser(ActionEvent event) {

        try {
            //PaneMain.setDisable(true);
            //Stage tstage = (Stage) myMenuBar.getScene().getWindow();
            //tstage.hide();
            Parent parent = FXMLLoader.load(getClass().getResource("/Vista/Actualizar.fxml"));

            Scene scene = new Scene(parent);
            //stage.setTitle("Login");
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            System.out.println(e);
        }

    }


    @FXML
    public void OnBuscarPlantilla(ActionEvent event) {
        try {
            //PaneMain.setDisable(true);
            //Stage tstage = (Stage) myMenuBar.getScene().getWindow();
            //tstage.hide();
            Parent parent = FXMLLoader.load(getClass().getResource("/Vista/BuscarPlantilla.fxml"));

            Scene scene = new Scene(parent);
            //stage.setTitle("Login");
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    public void OnSubirPlantilla(ActionEvent event) {

        ControlCuenta objCC = new ControlCuenta();

        //Lista de extensiones a usar
        LinkedList<String> a = new LinkedList<>();
        a.add("*.jpg");
        a.add("*.jpeg");
        a.add("*.png");
        a.add("*.gif");

        FileChooser fileChooser = new FileChooser();

        //Se da la extencion de la imagen
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", a)
        );

        //Se muestra la escena para abrir la imagen
        File file = fileChooser.showOpenDialog(stage);

        Date date = new Date();
        long x = date.getTime();

        Timestamp fecha = new Timestamp(x);

        Plantilla objP = new Plantilla(Integer.numberOfLeadingZeros(0), file.toString(), Usuario, fecha);

        boolean ins = false;

        ins = objCC.insertarPlantilla(objP);
    }

    //Metodo para Cargar imagen (Por ahora solo la carga no la almacena en sql)
    @FXML
    public void OnCreateImage(ActionEvent event) {

        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/Vista/ElegirCategoria.fxml"));

            Scene scene = new Scene(parent);
            //stage.setTitle("Login");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
        }

        ConnectBD cc = new ConnectBD();
        String sql = "";
        sql = ("select * from Imagen ORDER BY fecha DESC");
        boolean f = false;
        if (cc.crearConexion()) {
            try {
                Statement pst = cc.getConexion().createStatement();
                ResultSet rs = pst.executeQuery(sql);
                rs.first();
                PublicacionesI.getItems().clear();
                do {

                    String sql2 = "select nombre_CategoriaImagen from categoriaimagen where id_CategoriaImagen = " + rs.getInt(5) + ";";
                    Statement pst2 = cc.getConexion().createStatement();
                    ResultSet rs2 = pst2.executeQuery(sql2);
                    rs2.first();

                    Imagen ip = new Imagen(rs.getInt(1), "", rs.getString(3), rs.getTimestamp(4), rs.getInt(5), rs2.getString(1));

                    System.out.println(ip.getNombrecat());

                    PublicacionesI.getItems().add(ip);

                } while (rs.next());

                f = true;

            } catch (SQLException ex) {
                System.out.println(ex);

                f = false;
            }
        }

    }

    //Metodo para Cargar Audio (Por ahora solo la carga no la almacena en sql)
    @FXML
    public void OnCreateAudio(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/Vista/ElegircategoriaA.fxml"));

            Scene scene = new Scene(parent);
            //stage.setTitle("Login");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }

        ConnectBD cc = new ConnectBD();
        String sql = "";
        sql = ("select * from audio ORDER BY fecha DESC");
        boolean y = false;
        if (cc.crearConexion()) {
            try {
                Statement pst = cc.getConexion().createStatement();
                ResultSet rs = pst.executeQuery(sql);
                rs.first();
                PublicacionesA.getItems().clear();
                do {
                    String sql2 = "select nombre_CategoriaAudio from categoriaaudio where id_CategoriaAudio = " + rs.getInt(5) + ";";
                    Statement pst2 = cc.getConexion().createStatement();
                    ResultSet rs2 = pst2.executeQuery(sql2);
                    rs2.first();

                    int a = rs.getInt(1);
                    Audio ad = new Audio(a, "", rs.getString(3), rs.getTimestamp(4), rs.getInt(5),rs2.getString(1));

                    PublicacionesA.getItems().add(ad);

                } while (rs.next());

                y = true;

            } catch (SQLException ex) {
                System.out.println(ex);
                y = false;
            }
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // IMAGEN
        Usuario = LoginController.getUsuario();

        MainLabel.setText("Bienvenido " + Usuario);

        fechaPublic = new TableColumn<>("Fecha Publicacion");
        ncuenta = new TableColumn<>("Usuario");
        nombrecat = new TableColumn<>("Categoria");
        Img = new TableColumn<>("img");
        ComenI = new TableColumn<>("Comentarios");

        fechaPublic.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        ncuenta.setCellValueFactory(new PropertyValueFactory<>("correo"));
        nombrecat.setCellValueFactory(new PropertyValueFactory<>("nombrecat"));
        Img.setCellValueFactory(new PropertyValueFactory<>("button"));
        ComenI.setCellValueFactory(new PropertyValueFactory<>("button2"));

        PublicacionesI.getColumns().addAll(fechaPublic, ncuenta, Img, nombrecat,ComenI);

        ConnectBD cc = new ConnectBD();
        String sql = "";
        sql = ("select * from Imagen ORDER BY fecha DESC");
        boolean f = false;
        if (cc.crearConexion()) {
            try {
                Statement pst = cc.getConexion().createStatement();
                ResultSet rs = pst.executeQuery(sql);
                rs.first();
                PublicacionesI.getItems().clear();
                do {

                    String sql2 = "select nombre_CategoriaImagen from categoriaimagen where id_CategoriaImagen = " + rs.getInt(5) + ";";
                    Statement pst2 = cc.getConexion().createStatement();
                    ResultSet rs2 = pst2.executeQuery(sql2);
                    rs2.first();

                    Imagen ip = new Imagen(rs.getInt(1), "", rs.getString(3), rs.getTimestamp(4), rs.getInt(5), rs2.getString(1));

                    System.out.println(ip.getNombrecat());

                    PublicacionesI.getItems().add(ip);

                } while (rs.next());

                f = true;

            } catch (SQLException ex) {
                System.out.println(ex);

                f = false;
            }
        }

        //Audio
        fechaPublicA = new TableColumn<>("Fecha Publicacion");
        ncuentaA = new TableColumn<>("Usuario");
        nombreCatA = new TableColumn<>("Categoria");
        ComenA = new TableColumn<>("Comentario");
        Aud = new TableColumn<>("Audio");

        fechaPublicA.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        ncuentaA.setCellValueFactory(new PropertyValueFactory<>("correo"));
        nombreCatA.setCellValueFactory(new PropertyValueFactory<>("nombrecat"));
        Aud.setCellValueFactory(new PropertyValueFactory<>("button"));
        ComenA.setCellValueFactory(new PropertyValueFactory<>("button1"));

        PublicacionesA.getColumns().addAll(fechaPublicA, ncuentaA, Aud,nombreCatA,ComenA);

        sql = ("select * from audio ORDER BY fecha DESC");
        boolean y = false;
        if (cc.crearConexion()) {
            try {
                Statement pst = cc.getConexion().createStatement();
                ResultSet rs = pst.executeQuery(sql);
                rs.first();
                PublicacionesA.getItems().clear();
                do {
                    String sql2 = "select nombre_CategoriaAudio from categoriaaudio where id_CategoriaAudio = " + rs.getInt(5) + ";";
                    Statement pst2 = cc.getConexion().createStatement();
                    ResultSet rs2 = pst2.executeQuery(sql2);
                    rs2.first();

                    int a = rs.getInt(1);
                    Audio ad = new Audio(a, "", rs.getString(3), rs.getTimestamp(4), rs.getInt(5),rs2.getString(1));

                    PublicacionesA.getItems().add(ad);

                } while (rs.next());

                y = true;

            } catch (SQLException ex) {
                System.out.println(ex);
                y = false;
            }
        }

        tableview = PublicacionesI;
        tableview2 = PublicacionesA;
    }

    public void OnConsultar(ActionEvent event) {

        try {
            //PaneMain.setDisable(true);
            //Stage tstage = (Stage) myMenuBar.getScene().getWindow();
            //tstage.hide();
            Parent parent = FXMLLoader.load(getClass().getResource("/Vista/Consulta.fxml"));

            Scene scene = new Scene(parent);
            //stage.setTitle("Login");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void OnConsultarComen(ActionEvent event) {

        try {
            //PaneMain.setDisable(true);
            //Stage tstage = (Stage) myMenuBar.getScene().getWindow();
            //tstage.hide();
            Parent parent = FXMLLoader.load(getClass().getResource("/Vista/ConsultaCom.fxml"));

            Scene scene = new Scene(parent);
            //stage.setTitle("Login");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void setTableI(TableView tableview) {
        MainController.tableview = tableview;
    }

    public static void setTable2(TableView tableview) {
        MainController.tableview2 = tableview;
    }
}
