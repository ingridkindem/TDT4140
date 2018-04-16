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
    		URL url = this.getClass().getResource("../views/homeScreen/location.html");
        maps.getEngine().load(url.toString());
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
      // if we are going to use Lier ILs web site as contact, use first link instead
        maps.getEngine().load(this.getClass().getResource("../views/homeScreen/contact.html").toString());
    }
    
    public void handleMap() {
        maps.getEngine().load(this.getClass().getResource("../views/homeScreen/location.html").toString());
    }

    @FXML
    public void handleBack() {
        app.goToLogin();
    }
}


