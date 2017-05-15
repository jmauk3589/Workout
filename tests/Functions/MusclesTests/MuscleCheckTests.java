package Functions.MusclesTests;

import Functions.Database;
import Functions.MuscleGroups;
import org.junit.*;

import java.sql.Connection;
import static org.junit.Assert.*;


/**
 * Created by Johnny on 5/14/2017.
 */
public class MuscleCheckTests {
    private Connection con;

    @Before
    public void setUp() throws Exception {
        con = Database.open();
    }

    @After
    public void tearDown() throws Exception {
        Database.close(con);
    }

    @Test
    public void checkMuscleIndex() throws Exception {

    }

    @Test
    public void checkMuscleName() throws Exception {

    }

    @Test
    public void groupNameDoesNotExist() throws Exception {

    }

    @Test
    public void groupIndexDoesNotExist() throws Exception {

    }



}
