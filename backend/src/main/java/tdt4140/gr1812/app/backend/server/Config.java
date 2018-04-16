package tdt4140.gr1812.app.backend.server;

/**
 * Created by LarsErik on 12/04/2018.
 */

// Setting valid access-information to database for queries.
public class Config {
    static String dbUser = "pu";
    static String dbPass = "ZAmrUsPWD3vd";
    static String dbHost = "larserikfagernaes.com";
    static String dbName = "PU";
    static int dbPort = 3306;
    static String serverSalt = "someRandomStringToPreventRainbows"; //Encryption String
}
