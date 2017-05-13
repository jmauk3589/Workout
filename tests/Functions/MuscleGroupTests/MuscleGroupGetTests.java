package Functions.MuscleGroupTests;

import Functions.Database;
import Functions.MuscleGroups;
import org.junit.*;

import java.sql.Connection;
import static org.junit.Assert.*;

/**
 * Created by Johnny on 5/12/2017.
 */
public class MuscleGroupGetTests {
    private Connection con;
    private String testGroup;
    private String testGroupIndex;


    @Before
    public void setUp() throws Exception {
        con = Database.open();
        testGroup = "GETTESTS";
        MuscleGroups.addMuscleGroup(con,testGroup);
        testGroupIndex = MuscleGroups.getGroupIndex(con,testGroup);
    }

    @After
    public void tearDown() throws Exception {
        MuscleGroups.removeMuscleGroup(con,testGroupIndex);
        Database.close(con);
    }

    @Test
    public void getIndexWorks() throws Exception {
        assertTrue(testGroupIndex.equals(MuscleGroups.getGroupIndex(con,testGroup)));
    }

    @Test
    public void getIndexFails() throws Exception {
        assertTrue("0".equals(MuscleGroups.getGroupIndex(con,"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx")));
    }

    @Test
    public void getNameWorks() throws Exception {
        assertTrue(testGroup.equals(MuscleGroups.getGroupName(con,testGroupIndex)));
    }

    @Test
    public void getNameFails() throws Exception {
        assertTrue("0".equals(MuscleGroups.getGroupName(con,"0")));
    }
}
