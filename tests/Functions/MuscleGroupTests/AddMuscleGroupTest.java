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
public class AddMuscleGroupTest {
    private String testGroup;
    private Connection con;

    @Before
    public void setUp() throws Exception {
        con = Database.open();
        testGroup = "TESTGROUP";
    }

    @After
    public void tearDown() throws Exception {
        String index = MuscleGroups.getGroupIndex(con,testGroup);
        MuscleGroups.removeMuscleGroup(con,index);
        Database.close(con);
    }

    @Test
    public void addGroup() throws Exception {
        assertTrue(MuscleGroups.addMuscleGroup(con,testGroup));
    }

    @Test
    public void addDuplicateGroup() throws Exception {
        MuscleGroups.addMuscleGroup(con,testGroup);
        assertFalse(MuscleGroups.addMuscleGroup(con,testGroup));
    }



}