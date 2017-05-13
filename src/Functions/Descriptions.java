package Functions;

import java.sql.Connection;
import java.sql.Statement;

/**
 * Created by Johnny on 5/10/2017.
 */
public class Descriptions extends Database {
    private static String table ="descriptions";
    private static String col_ID = "dID";
    private static String col_Description = "Description";

    public Descriptions(){}

    public static boolean addDescription(Connection con, String description){
        boolean descriptionAdded = false;
        try{
            if(!checkDescriptionExists(con,description)){
                Statement stmt = con.createStatement();
                stmt.executeUpdate("Insert into "+table+" ("+col_Description+") Values ('"+description+"');");
                con.commit();
                descriptionAdded = checkDescriptionExists(con,description);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return  descriptionAdded;
    }

    public static boolean checkDescriptionExists(Connection con, String description){
        return checkEntityExists(con, table, col_Description,description);
    }

    public static String getDescriptionOfIndex(Connection con, String index){
        return getInstanceSpecific(con,table,col_ID,index,col_Description);
    }

    public static String getIndexOfDescription(Connection con, String description){
        return getInstanceSpecific(con,table,col_ID,description,col_ID);
    }




}
