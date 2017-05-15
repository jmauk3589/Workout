package Functions.MusclesTests;

import Functions.*;
import org.junit.*;
import java.sql.Connection;
import static org.junit.Assert.*;

/**
 * Created by johnn on 5/14/2017.
 */
public class AddMuscleTest {
    private Connection con;
    private String testMuscle;
    private String testMuscleGroupIndex;

    @Before
    public void setUp() throws Exception {
        con = Database.open();
        testMuscle = "TESTMUSCLE";
        String testMuscleGroup = "TESTMUSCLEGROUP";
        MuscleGroups.addMuscleGroup(con, testMuscleGroup);
        testMuscleGroupIndex = MuscleGroups.getGroupIndex(con, testMuscleGroup);
    }

    @After
    public void tearDown() throws Exception {
        String index = Muscles.getIndexFromName(con,testMuscle);
        Muscles.removeMuscle(con,index);
        MuscleGroups.removeMuscleGroup(con,testMuscleGroupIndex);
        Database.close(con);
    }

    @Test
    public void addMuscle() throws Exception {
        assertTrue(Muscles.addMuscle(con,testMuscle,testMuscleGroupIndex));
    }
}
