package io.prargra.leaning.h2learning;

import io.prargra.leaning.h2learning.doa.StudentDAO;
import io.prargra.leaning.h2learning.domain.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class H2LearningApplication {

    private StudentDAO studentDAO;

    private JdbcTemplate template;

    public H2LearningApplication(StudentDAO studentDAO, JdbcTemplate template) {
        this.studentDAO = studentDAO;
        this.template = template;
    }

    public static void main(String[] args) {
        SpringApplication.run(H2LearningApplication.class, args);
    }

    @Bean
    CommandLineRunner runner() {
        return args->{
            init();
            Student student = new Student(2, "Naveleen", "Full Stack Java");
            studentDAO.addStudent(new Student(1,"Prakash", "Full Stack Java"));
            studentDAO.addStudent(student);
            studentDAO.addStudent(new Student(3,"Joseph", "DevOps"));

            studentDAO.getAllStudent().forEach(System.out::println);

            Thread.sleep(20000);
            student.setProgram("Automation");
            studentDAO.updateStuent(student);


        };
    }

    public void init() {
        System.out.println("Creating table");
        String sql =
                "CREATE TABLE STUDENT (ID INT PRIMARY KEY , NAME VARCHAR(50), PROGRAM VARCHAR(50))";
        try {
            template.execute(sql);
        }catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Table already exists");
        }
    }
}
