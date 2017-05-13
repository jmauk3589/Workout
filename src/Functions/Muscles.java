package Functions;

import java.sql.Connection;
import java.sql.Statement;

/**
 * Created by Johnny on 5/10/2017.
 */
public class Muscles extends Database {

    private static String table = "muscles";
    private static String col_ID = "mID";
    private static String col_Name = "m_Name";
    private static String col_Group = "m_Group";

    public Muscles(){}

    public static boolean addMuscle(Connection con, String muscle, String muscleGroup){
        boolean muscleAdded = false;
        Statement stmt = null;
        try{
            if(!checkMuscleNameExists(con,muscle) && MuscleGroups.checkGroupIndexExists(con,muscleGroup)){
                stmt = con.createStatement();
                stmt.executeUpdate("Insert into "+table+" ("+col_Name+","+col_Group+") Value ('"+muscle+"',"+muscleGroup+");");
                con.commit();
                muscleAdded = checkMuscleNameExists(con,muscleGroup);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return muscleAdded;
    }

    public static boolean checkMuscleIndexExists(Connection con, String index){
        return checkEntityExists(con,table, col_ID, index);
    }

    public static boolean checkMuscleNameExists(Connection con, String muscle){
        return checkEntityExists(con,table,col_Name,muscle);
    }

    // not sure if i want this. checkMuscleGroupExists is also in Functions.MuscleGroups basically.
    public static boolean checkMuscleGroupExists(Connection con, String muscleGroup){
        return checkEntityExists(con,table, col_Group, muscleGroup);
    }
}
