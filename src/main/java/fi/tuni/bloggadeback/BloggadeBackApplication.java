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

    @Autowired
    CommentRepository commentRepository;

    public static void main(String[] args) {
        SpringApplication.run(BloggadeBackApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hello Bloggade!");
        System.out.println("curl -i http://localhost:8080/api/public/blogposts/");
        System.out.println("curl -v -H \"Content-type: application/json\" -X POST -d \"{\\\"userName\\\":\\\"jeppe\\\", \\\"blogTitle\\\":\\\"otsikko\\\", \\\"blogDescription\\\":\\\"kuvaus\\\", \\\"blogText\\\":\\\"teksti\\\"}\" http://localhost:8080/api/private/admin/add/");

        // Create a Posts
        BlogPost post1 = new BlogPost("admin", "Tervetuloa", "Tervetuloa, bloggademaan.", "Vain kivoja ja asiallisia tekstejä kiitos. Herjauksista bannivasara heilahtaa.");
        BlogPost post2 = new BlogPost("Jamppa", "Ruotsin alkeet", "Bloggade kuuluu kirjoittaa muotoon bloggare.", "fsfs sfadshcx shdjshd shfdusgf  sfdsgd sad sudgusdgys  behwegs7tx sgdad");

        // Create Comments
        Comment comment1 = new Comment("Great Post!");
        Comment comment2 = new Comment("Really helpful Post. Thanks a lot!");

        comment1.setBlogPost(post1);
        comment2.setBlogPost(post1);

        // Add comments in the Posts
        post1.getComments().add(comment1);
        post1.getComments().add(comment2);


        blogPostRepository.save(post1);

        commentRepository.save(comment1);
        commentRepository.save(comment2);
        // Save Posts and Comments via the Post entity
        blogPostRepository.save(post2);

        for(Comment comment : post1.getComments()) {
            System.out.println(comment.getText());
        }

    }
}
