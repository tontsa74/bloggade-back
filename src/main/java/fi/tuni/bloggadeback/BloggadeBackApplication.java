package fi.tuni.bloggadeback;

import fi.tuni.bloggadeback.user.ApplicationUser;
import fi.tuni.bloggadeback.user.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class BloggadeBackApplication implements CommandLineRunner {

    @Autowired
    BlogPostRepository blogPostRepository;

    @Autowired
    ApplicationUserRepository userRepository;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(11);
    }

    public static void main(String[] args) {
        SpringApplication.run(BloggadeBackApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hello Bloggade!");
        System.out.println("curl -i http://localhost:8080/api/public/blogposts/");
        System.out.println("curl -v -H \"Content-type: application/json\" -X POST -d \"{\\\"userName\\\":\\\"jeppe\\\", \\\"blogTitle\\\":\\\"otsikko\\\", \\\"blogDescription\\\":\\\"kuvaus\\\", \\\"blogText\\\":\\\"teksti\\\"}\" http://localhost:8080/api/private/admin/add/");

        userRepository.save(new ApplicationUser("jussi", bCryptPasswordEncoder().encode("password"), "ROLE_ADMIN"));

        List<BlogPost> blogPosts = new ArrayList<>();
        blogPosts.add(new BlogPost("admin", "Tervetuloa", "Tervetuloa, bloggademaan.", "Vain kivoja ja asiallisia tekstejä kiitos. Herjauksista bannivasara heilahtaa."));
        blogPosts.add(new BlogPost("Jamppa", "Ruotsin alkeet", "Bloggade kuuluu kirjoittaa muotoon bloggare.", "fsfs sfadshcx shdjshd shfdusgf  sfdsgd sad sudgusdgys  behwegs7tx sgdad"));
        blogPostRepository.saveAll(blogPosts);
    }
}
