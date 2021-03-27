package io.prargra.leaning.h2learning.doa;

import io.prargra.leaning.h2learning.domain.Student;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class StudentDAO {

    private NamedParameterJdbcTemplate jdbcTemplate;

    public StudentDAO(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



    public int addStudent(Student student) {
        String sql =
                "INSERT INTO STUDENT VALUES (:id, :name, :program)";
        return jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(student));
    }

    public List<Student> getAllStudent() {
        return jdbcTemplate.query("SELECT * FROM STUDENT ", new BeanPropertyRowMapper<>(Student.class));
    }

    public int updateStuent(Student student) {
        System.out.println("Updating STUDENT");
        String sql = "UPDATE STUDENT SET PROGRAM = :program  where ID = :id";
        return jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(student));
    }
}
