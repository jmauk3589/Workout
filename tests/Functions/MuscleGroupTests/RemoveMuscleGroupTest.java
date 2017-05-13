package Functions.MuscleGroupTests;

import Functions.Database;
import Functions.MuscleGroups;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;

/**
 * Created by Johnny on 5/12/2017.
 */
public class RemoveMuscleGroupTest {
    private String testGroupIndex;
    private Connection con;

    @Before
    public void setUp() throws Exception {
        con = Database.open();
        String testGroup = "testGroup";
        MuscleGroups.addMuscleGroup(con,testGroup);
        testGroupIndex = MuscleGroups.getGroupIndex(con,testGroup);
    }

    @After
    public void tearDown() throws Exception {
        Database.close(con);
    }

    @Test
    public void removeGroupWorks() throws Exception {
        assertTrue(MuscleGroups.removeMuscleGroup(con,testGroupIndex));
    }

    @Test
    public void removeGroupFails() throws Exception {
        assertFalse(MuscleGroups.removeMuscleGroup(con,"0"));
    }

}