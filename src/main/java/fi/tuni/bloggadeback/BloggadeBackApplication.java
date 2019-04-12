package fi.tuni.bloggadeback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class BloggadeBackApplication implements CommandLineRunner {

    @Autowired
    BlogPostRepository blogPostRepository;

    public static void main(String[] args) {
        SpringApplication.run(BloggadeBackApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hello Bloggade!");
        System.out.println("curl -i http://localhost:8080/api/public/blogposts/");
        System.out.println("curl -v -H \"Content-type: application/json\" -X POST -d \"{\\\"userName\\\":\\\"jeppe\\\", \\\"blogTitle\\\":\\\"otsikko\\\", \\\"blogDescription\\\":\\\"kuvaus\\\", \\\"blogText\\\":\\\"teksti\\\"}\" http://localhost:8080/api/private/admin/add/");
        BlogPost test = new BlogPost("admin", "Tervetuloa", "Tervetuloa, bloggademaan.", "Vain kivoja ja asiallisia tekstejä kiitos. Herjauksista bannivasara heilahtaa.");
        BlogPost test2 = new BlogPost("Jamppa", "Ruotsin alkeet", "Bloggade kuuluu kirjoittaa muotoon bloggare.", "fsfs sfadshcx shdjshd shfdusgf  sfdsgd sad sudgusdgys  behwegs7tx sgdad");
        // test.setComment("HyväHyvä!!");
        List<Comment> comments = new ArrayList<>();
        comments.add(new Comment("HyväHyvä!!!"));
        comments.add(new Comment("HyväHyvä!!!"));
        comments.add(new Comment("HyväHyvä!!!"));
        comments.add(new Comment("HyväHyvä!!!"));
        test.setComments(comments);
        List<BlogPost> blogPosts = new ArrayList<>();
        blogPosts.add(test);
        blogPosts.add(test2);
        blogPostRepository.saveAll(blogPosts);
    }
}
