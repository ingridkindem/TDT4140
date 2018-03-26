package tdt4140.gr1812.app.ui.controllers;


import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXML;

import javafx.scene.control.Hyperlink;
import javafx.scene.web.WebView;
import tdt4140.gr1812.app.ui.FxApp;

public class HomeScreenController {

    @FXML
    private Hyperlink facebookB, mailB, phoneB, lierB;

    @FXML
    private WebView maps;

    @FXML
    private FxApp app;

    @FXML
    private URL location;


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

    @FXML
    public void handleFacebook() {
        maps.getEngine().load("https://www.facebook.com/lierkvinner/");

    }
    
    @FXML
    public void handleMail() {
        maps.getEngine().load("https://lieridrettslag.weborg.no/Lier-IL/Toppmeny/Kontakt-oss.html#/Hovedstyret");
    }
    
    public void handleMap() {
        maps.getEngine().load("file:///Users/bruker/tdt4180-oxygen/12/tdt4140-gr1812/app.ui/src/main/java/tdt4140/gr1812/app/ui/views/homeScreen/location.html");

    }

    @FXML
    public void handleBack() {
        app.goToLogin();
    }
}


