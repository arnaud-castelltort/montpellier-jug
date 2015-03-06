package org.montpellierjug.store;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by chelebithil on 06/03/15.
 */
public class DaoTestCase {

    @Test
    public void testInsertTags() {
        String userName = "jug";
        String password = "jug";
        String url = "jdbc:postgresql:jugbuild";

        // Connection is the only JDBC resource that we need
        // PreparedStatement and ResultSet are handled by jOOQ, internally
        try (Connection conn = DriverManager.getConnection(url, userName, password)) {
            DSLContext create = DSL.using(conn, SQLDialect.POSTGRES);
        }

        // For the sake of this tutorial, let's keep exception handling simple
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
