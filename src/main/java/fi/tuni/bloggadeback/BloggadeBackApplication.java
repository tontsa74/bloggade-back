package fi.tuni.bloggadeback;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BloggadeBackApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(BloggadeBackApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hello Bloggade");
    }
}
