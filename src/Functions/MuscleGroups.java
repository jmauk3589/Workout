package Functions;

import java.sql.Connection;
import java.sql.Statement;
import java.util.regex.Pattern;

/**
 * Created by Johnny on 5/10/2017.
 */
public class MuscleGroups extends Database{

    private static String table = "muscle_groups";
    private static String col_ID = "mgID";
    private static String col_Group = "mg_Group";

    public MuscleGroups(){}

    public static boolean addMuscleGroup(Connection con, String muscleGroup){
        boolean groupAdded = false;
        muscleGroup = muscleGroup.toUpperCase();
        try{
            if(checkCriteria(muscleGroup) && !checkGroupNameExists(con,muscleGroup)){
                con.setAutoCommit(false);
                Statement stmt = con.createStatement();
                stmt.executeUpdate("Insert into "+table+" ("+col_Group+") Value ('"+muscleGroup+"');");
                con.commit();
                groupAdded = checkGroupNameExists(con,muscleGroup);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return groupAdded;
    }

    public static boolean removeMuscleGroup(Connection con, String muscleGroupIndex){
        boolean groupDeleted = false;
        try{
            if(checkGroupIndexExists(con,muscleGroupIndex)){
                con.setAutoCommit(false);
                Statement stmt = con.createStatement();
                stmt.executeUpdate("Delete from "+table+" WHERE "+col_ID+"="+muscleGroupIndex+";");
                con.commit();
                groupDeleted = !checkGroupIndexExists(con,muscleGroupIndex);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return groupDeleted;
    }


    public static boolean checkGroupNameExists(Connection con, String groupName){
        groupName = groupName.toUpperCase();
        return checkEntityExists(con,table,col_Group,groupName);
    }

    public static boolean checkGroupIndexExists(Connection con, String index){
        return checkEntityExists(con,table,col_ID,index);
    }

    public static String getGroupIndex(Connection con, String groupName){
        groupName = groupName.toUpperCase();
        if(checkGroupNameExists(con,groupName)){
            return getInstanceSpecific(con,table,col_Group,groupName,col_ID);
        }else{
            return "0";
        }
    }

    public static String getGroupName(Connection con, String index){
        if(checkGroupIndexExists(con,index)){
            return getInstanceSpecific(con,table,col_ID,index,col_Group);
        }else{
            return "0";
        }
    }

    public static boolean checkCriteria(String name){
        Pattern compile = Pattern.compile("^[\\p{Upper}][\\p{Upper}\\s]{2,20}$");
        return compile.matcher(name).find();
    }

}
