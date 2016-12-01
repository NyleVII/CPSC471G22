/*
Manmeet Dhaliwal
471 Demo Project to show how to connect to MS SQL database
 */

package com.example.ark.myapplication;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    Connection conn;
    String un, pass, db, ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //NOTE: Fill these attributes before you execute this program
        //NOTE: For ip address, if you are provided with a port number, the format will be "ipaddress:portnumber"
        ip = "136.159.7.84:50001";    //enter ip address here
        db = "CPSC471_Fall2016_G22";    //enter database name here
        un = "CPSC471_Fall2016_G22";    //enter username here
        pass = "6VXVM_0~rq1F-$W";  //enter password here
    }

    //Make sure to link the method to the button using the activity_main.xml file, click on the button, go to properties
    //and add this method under "onClick" property
    public void onButtonClickConnect(View v){
        conn = connectionclass(un, pass, db, ip);

        TextView t1 = (TextView)findViewById(R.id.result);
        if(conn == null){
            t1.setText("No");
        }else{
            t1.setText("Yes");

            try {
                //Inserting into the database with db = database name
                //dbo.names is the table name
                //standard format for accessing the sql server provided through Tamer is the following
                String query = "SELECT Name " + db + "FROM Runs as r WHERE r.Hill_ID=1";
                Statement stmt = conn.createStatement();
                ResultSet rs =  stmt.executeQuery(query);

                int i = 0;
                String[] runs;

                while (rs.next()){
                    String name = rs.getString("Name");

                    runs[++i] = name;

                }


            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //connection class
    @SuppressLint("NewApi")
    public Connection connectionclass (String user, String pass, String db, String server){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String connectionURL = null;

        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            connectionURL = "jdbc:jtds:sqlserver://" + server + ";" + "databseName=" + db + ";user=" + user + ";password=" + pass + ";";
            connection = DriverManager.getConnection(connectionURL);

        }catch(Exception e){
            Log.e("Error: ", e.getMessage());
        }

        return connection;
    }
}
