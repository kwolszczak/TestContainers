package pl.kwolszczak;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import pl.kwolszczak.DBConnector.DBConnector;
import pl.kwolszczak.containers.PostgresContainer;

@Testcontainers
@MicronautTest(environments = "spring", transactional = false) //transactiona -false and you can write to db after every signle test
class JdbcTemplateActorTest {

    @Container
    private static PostgreSQLContainer<?> postgres = PostgresContainer.getContainer();

    private javax.sql.DataSource postgresDataSource;
    private JdbcTemplate jdbcTemplate;


/*    @Inject
    public JdbcTemplateActorTest(DataSource postgresDataSource){
        this.postgresDataSource = postgresDataSource;
    }*/

    /*   @MockBean(DBConnector.class)
       public DBConnector postgresConnection(){
            PostgresTestContainer dbConnection = new PostgresTestContainer();
            dbConnection.setUrl(postgres.getJdbcUrl());
            dbConnection.setUsername(postgres.getUsername());
            dbConnection.setPassword(postgres.getPassword());
            dbConnection.datacource();
            return dbConnection;
       }*/
    @Test
    void jdbcTemplate_test() {
        System.out.println("start test");
        //    this.jdbcTemplate = new JdbcTemplate(DelegatingDataSource.unwrapDataSource(dataSource));
        postgresDataSource = new DBConnector().datacource();
        jdbcTemplate = new JdbcTemplate(postgresDataSource);
        jdbcTemplate.queryForList("SELECT * FROM Actor AS act WHERE act.firstname = ?", "Brad")
                .forEach(System.out::println);
//              .map(m-> new Actor((int)m.get("id"),"d",1L))
//              .toList();
        jdbcTemplate.queryForList("SELECT * FROM Actor AS act")
                .forEach(System.out::println);
    }


}

