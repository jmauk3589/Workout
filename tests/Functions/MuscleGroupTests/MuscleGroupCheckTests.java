package Functions.MuscleGroupTests;

import Functions.Database;
import Functions.MuscleGroups;
import org.junit.*;

import java.sql.Connection;
import static org.junit.Assert.*;


/**
 * Created by Johnny on 5/12/2017.
 */
public class MuscleGroupCheckTests {
    private Connection con;
    private String trueName;
    private String trueIndex;

    @Before
    public void setUp() throws Exception {
        con = Database.open();
        trueName = "checkGroup";
        MuscleGroups.addMuscleGroup(con,trueName);
        trueIndex = MuscleGroups.getGroupIndex(con,trueName);
    }

    @After
    public void tearDown() throws Exception {
        MuscleGroups.removeMuscleGroup(con,trueIndex);
        Database.close(con);
    }

    @Test
    public void groupNameExists() throws Exception {

        assertTrue(MuscleGroups.checkGroupNameExists(con,trueName));
    }

    @Test
    public void groupIndexExists() throws Exception {
        assertTrue(MuscleGroups.checkGroupIndexExists(con,trueIndex));
    }

    @Test
    public void groupNameDoesNotExist() throws Exception {
        String falseName = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx123";
        assertFalse(MuscleGroups.checkGroupNameExists(con,falseName));
    }

    @Test
    public void groupIndexDoesNotExist() throws Exception {
        String falseIndex = "0";
        assertFalse(MuscleGroups.checkGroupIndexExists(con,falseIndex));
    }

    @Test
    public void criteriaWorks() throws Exception {
        String[] goodNames = {"CHEST","BIS AND TRIS"};
        String[] badNames = {"leg","ab", " CHEST","ASDADSAKJDAJKDJKADJKASDJKAJSDADASD","12345"};

        for(int good=0; good<goodNames.length; good++){
            assertTrue(MuscleGroups.checkCriteria(goodNames[good]));
        }

        for(int bad=0; bad<badNames.length;bad++){
            assertFalse(MuscleGroups.checkCriteria(badNames[bad]));
        }
    }

}
