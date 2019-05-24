/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Control.ControlComentario;
import Control.ControlLikeAudio;
import Control.LoginController;
import Control.MainController;
import static Modelo.Imagen.id;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaMarkerEvent;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;

/**
 *
 * @author Usuario
 */
public class Audio {

    private int id_Audio;
    public static int id;
    private String Audio;
    private String correo;
    private Timestamp fecha;
    private Button buttonA;
    private Button button1;
    private int id_categoria;
    private String nombrecat;
    private Button Delete;

    public Audio() {
    }

    public Audio(int id_Audio, Timestamp fecha, int id_categoria, String nombrecat) {
        this.id_Audio = id_Audio;
        this.nombrecat = nombrecat;
        this.fecha = fecha;
        this.id_categoria = id_categoria;
        this.Delete = new Button("Borrar");

        this.Delete.setOnAction((final ActionEvent e) -> {
            ConnectBD con = new ConnectBD();
            String sql2 = "DELETE  FROM audio WHERE id_audio =" + id_Audio;
            if (con.crearConexion()) {
                try {

                    Statement sentencia = con.getConexion().createStatement();
                    sentencia.execute(sql2);

                    ConnectBD cc = new ConnectBD();
                    String sql = "";
                    sql = ("select * from audio ORDER BY fecha DESC");
                    boolean y = false;
                    if (cc.crearConexion()) {
                        try {
                            Statement pst = cc.getConexion().createStatement();
                            ResultSet rs = pst.executeQuery(sql);
                            rs.first();
                            MainController.tableview2.getItems().clear();
                            do {
                                String sql3 = "select nombre_CategoriaAudio from categoriaaudio where id_CategoriaAudio = " + rs.getInt(5) + ";";
                                Statement pst2 = cc.getConexion().createStatement();
                                ResultSet rs2 = pst2.executeQuery(sql3);
                                rs2.first();

                                int n = rs.getInt(1);
                                Audio ad = new Audio(n, "", rs.getString(3), rs.getTimestamp(4), rs.getInt(5), rs2.getString(1));

                                MainController.tableview2.getItems().add(ad);

                            } while (rs.next());

                            y = true;

                        } catch (SQLException ex) {
                            System.out.println(ex);
                            y = false;
                        }
                    }
                    //Se llama al metodo de controlcuenta para insertar

                } catch (SQLException ex) {

                }

            }
        });
    }

    public Audio(int id_Audio, String Audio, String correo, Timestamp fecha, int id_categoria) {
        this.id_Audio = id_Audio;
        this.Audio = Audio;
        this.correo = correo;
        this.fecha = fecha;
        this.id_categoria = id_categoria;

    }

    public Audio(int id_Audio, String Audio, String correo, Timestamp fecha, int id_categoria, String nombrecat) {
        this.id_Audio = id_Audio;
        this.Audio = Audio;
        this.fecha = fecha;
        this.correo = correo;
        this.id_categoria = id_categoria;
        this.nombrecat = nombrecat;

        this.buttonA = new Button("Ver meme");
        this.button1 = new Button("Ver");

        this.buttonA.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent e) {

                openNewAudioWindow(id_Audio);

            }

        });

        this.button1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent e) {

                try {
                    id = id_Audio;
                    Parent parent = FXMLLoader.load(getClass().getResource("/Vista/ConsultaComTotalA.fxml"));

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

    public String getAudio() {
        return Audio;
    }

    public void setAudio(String Audio) {
        this.Audio = Audio;
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

    public Button getButtonA() {
        return buttonA;
    }

    public void setButtonA(Button button) {
        this.buttonA = button;
    }

    public Button getButton1() {
        return button1;
    }

    public void setButton1(Button button1) {
        this.button1 = button1;
    }

    public Button getDelete() {
        return Delete;
    }

    public void setDelete(Button Delete) {
        this.Delete = Delete;
    }

    @Override
    public String toString() {
        return "Audio{" + "id_Audio=" + id_Audio + ", Audio=" + Audio + ", correo=" + correo + '}';
    }

    public boolean insertarAudio(Audio objA, String sql) {
        boolean t = false;
        FileInputStream fis = null;
        PreparedStatement ps = null;
        ConnectBD obj = new ConnectBD();
        try {
            if (obj.crearConexion()) {
                obj.getConexion().setAutoCommit(false);
                File file = new File(objA.getAudio());
                fis = new FileInputStream(file);
                ps = obj.getConexion().prepareStatement(sql);
                ps.setBinaryStream(1, fis, (int) file.length());
                ps.setString(2, objA.getCorreo());
                ps.setTimestamp(3, objA.getFecha());
                ps.setInt(4, objA.getId_categoria());
                ps.executeUpdate();
                obj.getConexion().commit();
                t = true;
            }

        } catch (Exception ex) {
            t = false;
            System.out.println("Error " + ex.toString());
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

    private void openNewAudioWindow(int id_Audio) {

        /* Buscar audio según la id */
        ConnectBD cc = new ConnectBD();
        boolean f = false;
        String ab = "";
        String sql = "SELECT * FROM audio a WHERE a.id_Audio = '" + id_Audio + "' ; ";
        if (cc.crearConexion()) {
            try {
                Statement pst = cc.getConexion().createStatement();
                ResultSet rs = pst.executeQuery(sql);
                rs.first();
                do {
                    byte byteImage[] = null;
                    Blob blob = rs.getBlob(2);
                    String bob = rs.getString(2);
                    byteImage = blob.getBytes(1, (int) blob.length());

                    File someFile = File.createTempFile("something-", ".mp3");
                    InputStream in = blob.getBinaryStream();
                    OutputStream out = new FileOutputStream(someFile);
                    byte[] buff = blob.getBytes(1, (int) blob.length());
                    out.write(buff);
                    out.close();

                    String split[];
                    split = someFile.toString().split("\\\\");
                    for (String split1 : split) {
                        ab += split1 + "/";
                    }

                } while (rs.next());

                f = true;

            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println(ex);
                f = false;
            }
        }

        //---------------------------------------------------------------------//
        Stage stage = new Stage();
        Media media = new Media("file:///" + ab);
        media.setOnError(() -> System.out.println("Media: " + media.getError().getMessage()));

        MediaPlayer player = new MediaPlayer(media);
        player.setOnError(() -> System.out.println("MediaPlayer: " + player.getError().getMessage()));
        player.statusProperty().addListener((prop, oldStatus, newStatus) -> {
            // se ha producido un cambio de estado
            System.out.println("Status changed from " + oldStatus + " to " + newStatus);
        });

        // crear marcadores, son indicadores que se disparan al alcanzar el timepo definido 
        ObservableMap<String, Duration> markers = media.getMarkers();
        markers.put("START", Duration.ZERO);
        markers.put("INTERVAL", Duration.minutes(0.8));
        markers.put("END", media.getDuration());

        // manejar marcadores
        player.setOnMarker((MediaMarkerEvent e) -> {
            Pair<String, Duration> marker = e.getMarker();
            String markerText = marker.getKey();
            Duration markerTime = marker.getValue();
            System.out.println("Reached the marker " + markerText + " at " + markerTime);
        });

        MediaView view = new MediaView(player);
        view.setPreserveRatio(false);
        view.setFitHeight(360);
        view.setFitWidth(640);

        //----------------------------------------------------------------------//
        Slider slider_time = new Slider();
        Label actual_time = new Label("0.00");
        Label total_time = new Label("0.00");
        Label laud = new Label();

        sql = "SELECT COUNT(id_LikeAudio) FROM likeaudio WHERE id_audio =" + id_Audio + ";";
        if (cc.crearConexion()) {
            try {
                Statement pst = cc.getConexion().createStatement();
                ResultSet rs = pst.executeQuery(sql);
                rs.first();
                do {
                    if (rs.getInt(1) == 0) {
                        laud.setText("");
                    } else {
                        laud.setText(rs.getInt(1) + "");
                    }
                } while (rs.next());

                f = true;

            } catch (SQLException ex) {
                System.out.println(ex);
                f = false;
            }
        }
        //---------------------------------------------------------------------//

        player.setOnReady(() -> {

            // obtener metadatos, si existen
            media.getMetadata().forEach((k, v) -> System.out.println(k + ", " + v));

            total_time.setText(String.format("%.2f", player.getTotalDuration().toMinutes()));
            slider_time.setMax(player.getTotalDuration().toSeconds());

            slider_time.valueProperty().addListener((p, o, value) -> {
                if (slider_time.isPressed()) {
                    player.seek(Duration.seconds(value.doubleValue()));
                }
            });

            player.currentTimeProperty().addListener((p, o, value) -> {
                slider_time.setValue(value.toSeconds());
                actual_time.setText(String.format("%.2f", value.toMinutes()));
            });
        });
//---------------------------------------------------------------------//
        HBox.setHgrow(slider_time, Priority.ALWAYS);
        HBox time_bar = new HBox(actual_time, slider_time, total_time);
        time_bar.setSpacing(10.0);

        //----------------------------------------------------------------------// 
        Label lbl_volumen = new Label("Volumen");

        Slider volumen = new Slider(0, 1, 0.8);
        player.volumeProperty().bind(volumen.valueProperty());

        Label actual_volumen = new Label("80%");
        actual_volumen.textProperty().bind(player.volumeProperty().multiply(100.0).asString("%.2f %%"));

        Button play = new Button("Reproducir");
        Button pause = new Button("Pausar");
        Button stop = new Button("Like");
        TextField txt = new TextField();
        txt.setPrefSize(200, 22);
        txt.setMaxSize(200, 22);
        Button Coment = new Button("Comentar");

        Coment.setOnAction((final ActionEvent e) -> {
            ControlComentario objCA = new ControlComentario();
            Comentario objC = null;
            boolean ins = false;
            Date dat = new Date();

            try {
                objC = new Comentario(id_Audio + "", LoginController.getUsuario(), txt.getText(), dat);

                txt.setText("");
                //Se llama al metodo de controlcuenta para insertar
                ins = objCA.ComentarAudio(objC);

            } catch (Exception ex) {
                System.out.println("ERROR " + ex.toString());
            }

        });

        URL linkLike = getClass().getResource("/Assets/like.png");

        Image imagenLike = new Image(linkLike.toString(), 24, 24, false, true);

        stop.setGraphic(new ImageView(imagenLike));

        play.setOnAction(e -> player.play());
        pause.setOnAction(e -> player.pause());

        stop.setOnAction((final ActionEvent e) -> {
            ControlLikeAudio objCA = new ControlLikeAudio();
            LikeAudio objL = null;
            Date date = new Date();
            long x = date.getTime();
            Timestamp fecha1 = new Timestamp(x);
            boolean ins = false;
            try {
                objL = new LikeAudio(id_Audio, LoginController.getUsuario(), fecha1);
                ins = objCA.DarLike(objL);
            } catch (Exception ex) {
                System.out.println("ERROR " + ex.toString());
            }
            boolean f2;
            String sql2 = "SELECT COUNT(id_LikeAudio) FROM likeaudio WHERE id_audio =" + id_Audio + ";";
            if (cc.crearConexion()) {
                try {
                    Statement pst = cc.getConexion().createStatement();
                    ResultSet rs = pst.executeQuery(sql2);
                    rs.first();
                    do {
                        if (rs.getInt(1) == 0) {
                            laud.setText("");
                        } else {
                            laud.setText(rs.getInt(1) + "");
                        }
                    } while (rs.next());

                    f2 = true;

                } catch (SQLException ex) {
                    System.out.println(ex);
                    f2 = false;
                }
            }

        });

        HBox.setHgrow(volumen, Priority.ALWAYS);
        HBox panel = new HBox(
                play, pause, stop, laud,
                //dec_rate, cur_rate, inc_rate,
                lbl_volumen, volumen, actual_volumen,
                txt, Coment);

        panel.setSpacing(10.0);
        panel.setAlignment(Pos.CENTER);

        //---------------------------------------------------------------------//
        VBox root = new VBox(view, time_bar, panel);
        root.setPadding(new Insets(10.0));
        root.setSpacing(10.0);

        Scene scene = new Scene(root);

        stage.setTitle("JavaFX Media API");
        stage.setScene(scene);
        stage.sizeToScene();

        stage.setWidth(810);
        stage.setHeight(488);
        stage.setMaxWidth(810);
        stage.setMaxHeight(488);
        stage.setMinWidth(810);
        stage.setMinHeight(488);

        stage.show();
        ////
    }

    public static String sqlAud() {
        String a = "CALL BuscarComentarioTotA (" + id + ") ; ";

        return a;
    }

}
