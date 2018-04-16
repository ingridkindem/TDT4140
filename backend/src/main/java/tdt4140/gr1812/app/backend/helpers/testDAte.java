package tdt4140.gr1812.app.backend.helpers;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by LarsErik on 15/04/2018.
 */
public class testDAte {
    public static void main(String[] args){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try{
            Date d = sdf.parse("21/12/2012");
            System.out.print(d.toGMTString());
        }
        catch (Exception e){

        }


    }
}
