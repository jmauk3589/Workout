package Functions.MusclesTests;

import Functions.*;
import org.junit.*;
import java.sql.Connection;
import static org.junit.Assert.*;

/**
 * Created by johnn on 5/14/2017.
 */
public class RemoveMuscleTest {
    private Connection con;
    private String testMuscleGroupIndex;
    private String testMuscleIndex;

    @Before
    public void setUp() throws Exception {
        con = Database.open();
        String testMuscle = "TESTMUSCLE";
        String testMuscleGroup = "TESTMUSCLEGROUP";
        MuscleGroups.addMuscleGroup(con, testMuscleGroup);
        testMuscleGroupIndex = MuscleGroups.getGroupIndex(con, testMuscleGroup);
        Muscles.addMuscle(con,testMuscle,testMuscleGroupIndex);
        testMuscleIndex = Muscles.getIndexFromName(con,testMuscle);
    }

    @After
    public void tearDown() throws Exception {
        MuscleGroups.removeMuscleGroup(con,testMuscleGroupIndex);
        Database.close(con);
    }

    @Test
    public void removeMuscle() throws Exception {
        assertTrue(Muscles.removeMuscle(con,testMuscleIndex));
        assertFalse(Muscles.removeMuscle(con,testMuscleIndex));
    }
}
