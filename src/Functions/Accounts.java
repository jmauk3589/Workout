package Functions;


import java.sql.Connection;

/**
 * Created by johnn on 5/15/2017.
 */
public class Accounts extends Database {
    private static String table = "accounts";
    private static String col_ID = "aID";
    private static String col_Name = "aName";
    private static String col_Email = "aEmail";
    private static String col_Role = "aRole";
    private static String col_Username = "aUsername";
    private static String col_Password = "aPassword";

    public Accounts(){}

    public static boolean login(Connection con, String username, String password){
        if(checkEntityExists(con,table,col_Username,username)){
            return password.equals(getInstanceSpecific(con,table,col_Username,username, col_Password));
        }else{
            return false;
        }
    }

   // public static boolean createAccount(Connection con, String name, String email, String )







}
