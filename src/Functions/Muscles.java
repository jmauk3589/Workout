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

    public static boolean addMuscle(Connection con, String muscle, String muscleGroupIndex){
        boolean muscleAdded = false;
        Statement stmt = null;
        muscle = muscle.toUpperCase();
        muscleGroupIndex = muscleGroupIndex.toUpperCase();
        try{
            if(!checkMuscleNameExists(con,muscle) && MuscleGroups.checkGroupIndexExists(con,muscleGroupIndex)){
                stmt = con.createStatement();
                stmt.executeUpdate("Insert into "+table+" ("+col_Name+","+col_Group+") Value ('"+muscle+"',"+muscleGroupIndex+");");
                con.commit();
                muscleAdded = checkMuscleNameExists(con,muscle);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return muscleAdded;
    }

    public static boolean removeMuscle(Connection con, String muscleIndex){
        boolean muscleDeleted = false;
        try{
            if(checkMuscleIndexExists(con,muscleIndex)){
                con.setAutoCommit(false);
                Statement stmt = con.createStatement();
                stmt.executeUpdate("Delete from "+table+" WHERE "+col_ID+"="+muscleIndex+";");
                con.commit();
                muscleDeleted = !checkMuscleIndexExists(con,muscleIndex);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return muscleDeleted;
    }

    public static boolean checkMuscleIndexExists(Connection con, String index){
        return checkEntityExists(con,table, col_ID, index);
    }

    public static boolean checkMuscleNameExists(Connection con, String muscle){
        muscle = muscle.toUpperCase();
        return checkEntityExists(con,table,col_Name,muscle);
    }

    // not sure if i want this. checkMuscleGroupExists is also in Functions.MuscleGroups basically.
    public static boolean checkMuscleGroupExists(Connection con, String muscleGroup){
        return checkEntityExists(con,table, col_Group, muscleGroup);
    }

    public static String getIndexFromName(Connection con, String muscleName){
        muscleName = muscleName.toUpperCase();
        if(checkMuscleNameExists(con,muscleName)){
            return getInstanceSpecific(con,table,col_Name,muscleName,col_ID);
        }else{
            return "0";
        }
    }

    public static String getNameFromIndex(Connection con, String index){
        if(checkMuscleIndexExists(con,index)){
            return getInstanceSpecific(con,table,col_ID,index,col_Name);
        }else{
            return "0";
        }
    }

    public static String getGroupFromIndex(Connection con, String index){
        if(checkMuscleIndexExists(con,index)){
            return getInstanceSpecific(con,table,col_ID,index,col_Group);
        }else{
            return "0";
        }
    }
}
