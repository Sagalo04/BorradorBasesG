/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
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
    private String Audio;
    private String correo;
    private Timestamp fecha;
    private Button button;

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public Audio() {
    }

    public Audio(int id_Audio, String Audio, String correo, Timestamp fecha) {
        this.id_Audio = id_Audio;
        this.Audio = Audio;
        this.correo = correo;
        this.fecha = fecha;
        this.button = new Button("Ver meme");

        this.button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent e) {

                openNewAudioWindow(id_Audio);

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

    @Override
    public String toString() {
        return "Audio{" + "id_Audio=" + id_Audio + ", Audio=" + Audio + ", correo=" + correo + '}';
    }

    public boolean insertarAudio(Audio objA, String sql) {
        System.out.println(objA.getAudio());
        System.out.println(objA.getCorreo());
        System.out.println(objA.getFecha());
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
                    System.out.println(someFile.toString());
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

        Stage stage = new Stage();
        Media media = new Media("file:///"+ab);
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
        Button stop = new Button("Detener");

        play.setOnAction(e -> player.play());
        pause.setOnAction(e -> player.pause());
        stop.setOnAction(e -> player.stop());

//        Label cur_rate = new Label("1x");
//        cur_rate.textProperty().bind(player.rateProperty().asString("%.1fx"));
//
//        Button inc_rate = new Button(">>");
//        Button dec_rate = new Button("<<");
//
//        // los valores validos para setRate van de 0 a 8
//        inc_rate.setOnAction(e -> player.setRate(player.getRate() + 1));
//        dec_rate.setOnAction(e -> player.setRate(player.getRate() - 1));

        HBox.setHgrow(volumen, Priority.ALWAYS);
        HBox panel = new HBox(
                play, pause, stop,
                //dec_rate, cur_rate, inc_rate,
                lbl_volumen, volumen, actual_volumen);

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
        stage.show();
    }

}