/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Control.ControlComentario;
import Control.ControlLikeImagen;
import Control.LoginController;
import Control.MainController;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Blob;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
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
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class Imagen {

    private int id_imagen;
    public static int id;
    private String imagen;
    private String correo;
    private Timestamp fecha;
    private Button button;
    private Button button2;
    private int id_categoria;
    private String nombrecat;
    private Button Delete;

    public Imagen() {
    }

    public Imagen(int id_imagen, String Imagen, String correo, Timestamp fecha, int id_categoria) {
        this.id_imagen = id_imagen;
        this.imagen = Imagen;
        this.correo = correo;
        this.fecha = fecha;
        this.id_categoria = id_categoria;
    }

    public Imagen(int id_imagen, Timestamp fecha, int id_categoria, String nombrecat) {
        this.id_imagen = id_imagen;
        this.fecha = fecha;
        this.id_categoria = id_categoria;
        this.nombrecat = nombrecat;
        this.Delete = new Button("Borrar");

        this.Delete.setOnAction((final ActionEvent e) -> {
            ConnectBD con = new ConnectBD();
            String sql1 = "DELETE  FROM imagen WHERE id_imagen =" + id_imagen;
            if (con.crearConexion()) {
                try {

                    Statement sentencia = con.getConexion().createStatement();
                    sentencia.execute(sql1);
                    
                     ConnectBD cc = new ConnectBD();
        String sql = "";
        sql = ("select * from Imagen ORDER BY fecha DESC");
        boolean f = false;
        if (cc.crearConexion()) {
            try {
                Statement pst = cc.getConexion().createStatement();
                ResultSet rs = pst.executeQuery(sql);
                rs.first();
                MainController.tableview.getItems().clear();
                do {

                    String sql3 = "select nombre_CategoriaImagen from categoriaimagen where id_CategoriaImagen = " + rs.getInt(5) + ";";
                    Statement pst2 = cc.getConexion().createStatement();
                    ResultSet rs2 = pst2.executeQuery(sql3);
                    rs2.first();

                    Imagen ip = new Imagen(rs.getInt(1), "", rs.getString(3), rs.getTimestamp(4), rs.getInt(5), rs2.getString(1));

                    MainController.tableview.getItems().add(ip);

                } while (rs.next());

                f = true;

            } catch (SQLException ex) {
                System.out.println(ex);

                f = false;
            }
        }
                    //Se llama al metodo de controlcuenta para insertar

                } catch (SQLException ex) {
                    
                    ex.printStackTrace();

                }

            }
        });
    }

    public Imagen(int id_imagen, String Imagen, String correo, Timestamp fecha, int id_categoria, String nombrecat) {
        this.id_imagen = id_imagen;
        this.imagen = Imagen;
        this.correo = correo;
        this.fecha = fecha;
        this.id_categoria = id_categoria;
        this.nombrecat = nombrecat;

        this.button2 = new Button("Ver");
        this.button = new Button("Ver meme");

        this.button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent e) {

                openNewImageWindow(id_imagen);

            }
        });

        this.button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent e) {

                try {

                    id = id_imagen;

                    Parent parent = FXMLLoader.load(getClass().getResource("/Vista/ConsultaComTotal.fxml"));
                    

                    Scene scene = new Scene(parent);
                    //stage.setTitle("Login");
                    Stage stage = new Stage();
                    stage.setWidth(600);
                    stage.setHeight(400);
                    stage.setMaxWidth(600);
                    stage.setMaxHeight(400);
                    stage.setMinWidth(600);
                    stage.setMinHeight(400);
                    stage.setScene(scene);
                    stage.show();

                } catch (Exception ex) {
                    System.out.println(e);
                }

            }
        });

    }

    public Imagen(int id_imagen, String correo, Timestamp fecha) {
        this.id_imagen = id_imagen;
        this.correo = correo;
        this.fecha = fecha;
    }

    public int getId_imagen() {
        return id_imagen;
    }

    public void setId_imagen(int id_imagen) {
        this.id_imagen = id_imagen;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String Imagen) {
        this.imagen = Imagen;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getNombrecat() {
        return nombrecat;
    }

    public void setNombrecat(String nombrecat) {
        this.nombrecat = nombrecat;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public Button getButton2() {
        return button2;
    }

    public void setButton2(Button button2) {
        this.button2 = button2;
    }

    public Button getDelete() {
        return Delete;
    }

    public void setDelete(Button Delete) {
        this.Delete = Delete;
    }

    @Override
    public String toString() {
        return "Imagen{" + "id_imagen=" + id_imagen + ", Imagen=" + imagen + ", correo=" + correo + '}';
    }

    public boolean insertarImagen(Imagen objI, String sql) {
        System.out.println(objI.getImagen());
        System.out.println(objI.getCorreo());
        System.err.println(objI.getFecha());
        System.out.println(objI.getId_categoria());
        boolean t = false;
        FileInputStream fis = null;
        PreparedStatement ps = null;
        ConnectBD obj = new ConnectBD();
        try {
            if (obj.crearConexion()) {
                obj.getConexion().setAutoCommit(false);
                File file = new File(objI.getImagen());
                fis = new FileInputStream(file);
                ps = obj.getConexion().prepareStatement(sql);
                ps.setBinaryStream(1, fis, (int) file.length());
                ps.setString(2, objI.getCorreo());
                ps.setTimestamp(3, objI.getFecha());
                ps.setInt(4, objI.getId_categoria());
                ps.executeUpdate();
                obj.getConexion().commit();
                t = true;
            }

        } catch (Exception ex) {
            t = false;
            System.out.println("Error " + ex.toString());
            ex.printStackTrace();
        } finally {
            try {
                ps.close();
                fis.close();
            } catch (Exception ex) {
                t = false;
                System.out.println("Errro " + ex);
            }
        }
        return t;
    }

    private void openNewImageWindow(int id) {
        Stage secondStage = new Stage();

        MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu("File");
        MenuItem menuItem_Save = new MenuItem("Save Image");
        menuFile.getItems().addAll(menuItem_Save);
        menuBar.getMenus().addAll(menuFile);
        TextField txt = new TextField();
        Label name1 = new Label(id + "");
        Button name = new Button("Like");

        name.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent e) {

                ControlLikeImagen objCL = new ControlLikeImagen();
                LikeImagen objL = null;

                Date date = new Date();
                long x = date.getTime();
                Timestamp fecha = new Timestamp(x);

                boolean ins = false;

                try {
                    objL = new LikeImagen(id, LoginController.getUsuario(), fecha);

                    //Se llama al metodo de controlcuenta para insertar
                    ins = objCL.DarLike(objL);

                } catch (Exception ex) {
                    System.out.println("ERROR " + ex.toString());
                }
                ConnectBD cc = new ConnectBD();
                boolean f = false;
                String sql = "SELECT COUNT(id_LikeImagen) FROM likeimagen WHERE id_imagen =" + id + ";";
                if (cc.crearConexion()) {
                    try {
                        Statement pst = cc.getConexion().createStatement();
                        ResultSet rs = pst.executeQuery(sql);
                        rs.first();
                        do {
                            if (rs.getInt(1) == 0) {
                                name1.setText("");
                            } else {
                                name1.setText(rs.getInt(1) + "");
                            }
                        } while (rs.next());

                        f = true;

                    } catch (SQLException ex) {
                        System.out.println(ex);
                        f = false;
                    }
                }
            }
        });

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

        String sql = "SELECT i.imagen FROM imagen i WHERE i.id_Imagen = '" + id + "' ; ";

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

        sql = "SELECT COUNT(id_LikeImagen) FROM likeimagen WHERE id_imagen =" + id + ";";
        if (cc.crearConexion()) {
            try {
                Statement pst = cc.getConexion().createStatement();
                ResultSet rs = pst.executeQuery(sql);
                rs.first();
                do {
                    if (rs.getInt(1) == 0) {
                        name1.setText("");
                    } else {
                        name1.setText(rs.getInt(1) + "");
                    }
                } while (rs.next());

                f = true;

            } catch (SQLException ex) {
                System.out.println(ex);
                f = false;
            }
        }

        Button btn = new Button("Comentar");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent e) {

                ControlComentario objCI = new ControlComentario();
                Comentario objC = null;
                boolean ins = false;
                Date date = new Date();
                long x = date.getTime();
                Timestamp fecha = new Timestamp(x);

                try {
                    objC = new Comentario(id + "", LoginController.getUsuario(), txt.getText(), fecha);

                    //Se llama al metodo de controlcuenta para insertar
                    ins = objCI.ComentarImagen(objC);

                    txt.setText("");

                } catch (Exception ex) {
                    System.out.println("ERROR " + ex.toString());
                }
            }
        });

        final VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(0, 10, 0, 10));
        vbox.getChildren().addAll(name1, name, imageView, txt, btn);

        txt.setPrefSize(200, 22);
        txt.setMaxSize(200, 22);

        btn.setTranslateX(146);
        btn.setTranslateY(-35);

        name1.setTranslateY(490);
        name1.setTranslateX(-250);

        name.setTranslateX(-300);
        name.setTranslateY(450);

        URL linkLike = getClass().getResource("/Assets/like.png");

        Image imagenLike = new Image(linkLike.toString(), 24, 24, false, true);

        name.setGraphic(new ImageView(imagenLike));

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

    public static String sqlImagen() {

        String a = "CALL BuscarComentarioTot (" + id + ") ; ";

        return a;

    }

}
