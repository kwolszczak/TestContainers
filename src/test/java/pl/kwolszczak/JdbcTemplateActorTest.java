package pl.kwolszczak;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import pl.kwolszczak.DBConnector.DBConnector;
import pl.kwolszczak.containers.PostgresContainer;

import javax.sql.DataSource;

@Testcontainers
@MicronautTest(environments = "spring")
class JdbcTemplateActorTest {

    @Container
    private static PostgreSQLContainer<?> postgres = PostgresContainer.getContainer();

    private javax.sql.DataSource postgresDataSource;
    private JdbcTemplate jdbcTemplate;


    @Inject
    public JdbcTemplateActorTest(DataSource postgresDataSource){
        this.postgresDataSource = postgresDataSource;
    }

    /*   @MockBean(DBConnector.class)
       public DBConnector postgresConnection(){
            PostgresTestContainer dbConnection = new PostgresTestContainer();
            dbConnection.setUrl(postgres.getJdbcUrl());
            dbConnection.setUsername(postgres.getUsername());
            dbConnection.setPassword(postgres.getPassword());
            dbConnection.datacource();
            return dbConnection;

            // postgresDataSource = new DBConnector().datacource();
       }*/
    @Test
    void jdbcTemplate_test() {

        jdbcTemplate = new JdbcTemplate(postgresDataSource);
        jdbcTemplate.queryForList("SELECT * FROM Actor AS act WHERE act.firstname = ?", "Brad")
                .forEach(System.out::println);
//              .map(m-> new Actor((int)m.get("id"),"d",1L))
//              .toList();
        jdbcTemplate.queryForList("SELECT * FROM Actor AS act")
                .forEach(System.out::println);
    }


}

