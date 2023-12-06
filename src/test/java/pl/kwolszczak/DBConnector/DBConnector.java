package pl.kwolszczak.DBConnector;


import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBConnector {

    protected static final String DB_NAME = "actor";
    protected static final String DB_USER = "postgres";
    protected static final String DB_PASSWORD = "example";
    protected static final String DB_INIT_SQL = "init-actor.sql";
    protected static final int DB_PORT_BIND = 15432;
    protected static final String POSTGRES_IMG = "postgres:latest";

    public PGSimpleDataSource datacource() {
        PGSimpleDataSource ds = new PGSimpleDataSource();
        ds.setUrl("jdbc:postgresql://localhost:15432/actor");
        ds.setDatabaseName("actor");
        ds.setUser("postgres");
        ds.setPassword("example");

        return ds;
    }

    public void setUsername(String username) {
    }

    public void setPassword(String password) {
    }

    public void setUrl(String jdbcUrl) {

    }
}
