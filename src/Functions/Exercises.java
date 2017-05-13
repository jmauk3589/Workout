package Functions;

import java.sql.Connection;
import java.sql.Statement;

/**
 * Created by Johnny on 5/10/2017.
 */
public class Exercises extends Database{
    private static String table ="exercises";
    private static String col_ID = "ExID";
    private static String col_Name = "Ex_Name";
    private static String col_Group = "Ex_Muscle_Group";
    private static String col_Muscle = "Ex_Muscle";
    private static String col_Description = "Ex_Description";
    private static String col_Primary = "Ex_Primary_Focus";

    public Exercises(){}

    public static boolean addExercise(Connection con, String name, String muscleGroup, String muscle, String description, String primary){
        boolean isAdded = false;
        try{
            if(!checkExerciseNameExists(con,name) && Muscles.checkMuscleIndexExists(con,muscle) && MuscleGroups.checkGroupIndexExists(con,muscleGroup) && !Descriptions.checkDescriptionExists(con,description)){
                Descriptions.addDescription(con,description);
                String index = Descriptions.getIndexOfDescription(con,description);

                Statement stmt = con.createStatement();
                stmt.executeUpdate("Insert into exercies ("+col_Name+","+col_Group+","+col_Muscle+","+col_Description+","+col_Primary+") Values ("+name+","+muscleGroup+","+muscle+","+index+","+primary+");");
                con.commit();
                isAdded = checkExerciseNameExists(con,name);
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
        }

        close(con);

        return isAdded;
    }
    
    public static boolean checkExerciseIndexExists(Connection con, String index){
        return checkEntityExists(con, table, col_ID,index);
    }

    public static boolean checkExerciseNameExists(Connection con, String name){
        return checkEntityExists(con, table, col_Name,name);
    }

    public static boolean checkExercisePrimaryFocus(Connection con, String exerciseID){
        return getInstanceSpecific(con,table,col_ID,exerciseID,col_Primary).equals("1");
    }










}
