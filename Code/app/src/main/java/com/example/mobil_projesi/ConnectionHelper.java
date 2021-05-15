package com.example.mobil_projesi;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelper {
    @SuppressLint("NewApi")
    public static Connection connectionClass(){

        StrictMode.ThreadPolicy pol = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(pol);
        Connection conn = null;
        String ConnectionUrl = null;
        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionUrl ="jdbc:sqlserver://serverdbyzm.database.windows.net:1433;database=YZMYapimiDB1;user=admin1234@serverdbyzm;password=aboD192955;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
            conn = DriverManager.getConnection(ConnectionUrl);
        } catch(SQLException se){
            Log.e("Error Here 1: ", se.getMessage());
        }
        catch(ClassNotFoundException e ){
            Log.e("Error Here 2 ", e.getMessage());
        }catch(Exception e){
            Log.e("error Here 3" , e.getMessage());
        }
        return conn;

    }
}
