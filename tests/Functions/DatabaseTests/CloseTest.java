package Functions.DatabaseTests;

import Functions.Database;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


import javax.xml.crypto.Data;
import java.sql.Connection;

/**
 * Created by Johnny on 5/12/2017.
 */
public class CloseTest {
    private Connection con;
    @Before
    public void setUp() throws Exception {
        con = Database.open();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void close() throws Exception {
        Database.close(con);
        assertTrue(!con.isValid(100));
    }
}
