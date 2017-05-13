package Functions;

/**
 * Created by Johnny on 5/10/2017.
 */

import java.sql.*;
import java.util.ArrayList;

public class Database {

    public Database(){}

    public static Connection open(){
        Connection con = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fitness?autoReconnect=true&useSSL=false","Johnny","Chemeng13");
        }catch(Exception e){
            System.err.println(e.getClass().getName() + ": " +e.getMessage());
            System.exit(0);
        }
        return con;
    }

    public static void close(Connection con){
        try {
            con.close();
        }catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    public static boolean checkEntityExists(Connection con, String table, String column, String value){
        boolean entityExists = false;
        try{
            Statement stmt = null;
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("Select count(*) from "+table+" where "+column+"='"+value+"';");
            rs.next();
            entityExists = rs.getInt("count(*)")>0;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return entityExists;
    }

    public static String getInstanceSpecific(Connection con, String table, String column, String value, String desired){
        String info = "";
        try{
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from "+table+" where "+column+"='"+value+"';");
            if(rs.next()){
                info = rs.getString(desired);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return info;
    }

    public static ArrayList<ArrayList<String>> getSpecifics(Connection con, String table, ArrayList<String[]> pairs , ArrayList<String> desiredInfo){
        ArrayList<ArrayList<String>> entities = new ArrayList<>();
        String criteria = "";
        for(int pair=0; pair<pairs.size(); pair++){
            if(pair!=0){
                criteria += " AND ";
            }
            criteria += pairs.get(pair)[0] + "='" + pairs.get(pair)[1] +"' ";
        }

        try{
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from "+table+" where "+criteria+";");
            while(rs.next()){
                entities.add( compileSpecifics(rs,desiredInfo));
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return entities;
    }

    private static ArrayList<String> compileSpecifics(ResultSet result, ArrayList<String> desired) throws Exception{
        ArrayList<String> info = new ArrayList<>();
        for(int i=0; i<desired.size(); i++){
            info.add(result.getString(desired.get(i)));
        }
        return info;
    }
    
}
