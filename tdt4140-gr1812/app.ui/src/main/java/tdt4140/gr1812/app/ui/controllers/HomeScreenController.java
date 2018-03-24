package tdt4140.gr1812.app.ui.controllers;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXML;

import javafx.scene.control.Hyperlink;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;
import tdt4140.gr1812.app.ui.FxApp;

public class HomeScreenController {

    @FXML
    private Hyperlink facebookB;

    @FXML
    private Hyperlink mailB;

    @FXML
    private Hyperlink phoneB;

    @FXML
    private Hyperlink lierB;

    @FXML
    private WebView maps;

    @FXML
    private FxApp app;



    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private StackPane root;


    @FXML
    public void initialize() {
        
       maps.getEngine().load("file:///Users/bruker/tdt4180-oxygen/12/tdt4140-gr1812/app.ui/src/main/java/tdt4140/gr1812/app/ui/views/homeScreen/location.html");
    }


    public void setApplication(FxApp app) {
        this.app = app;
    }

    public static void main(String[] args) {
        Application.launch(args);
    }


}


