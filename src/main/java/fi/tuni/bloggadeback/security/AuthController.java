package fi.tuni.bloggadeback.security;

import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import fi.tuni.bloggadeback.security.jwt.JwtAuthResponse;
import fi.tuni.bloggadeback.security.jwt.JwtTokenizer;
import fi.tuni.bloggadeback.security.requests.LoginRequest;
import fi.tuni.bloggadeback.security.requests.RegisterRequest;
import fi.tuni.bloggadeback.user.ApplicationUser;
import fi.tuni.bloggadeback.user.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    ApplicationUserRepository userRepository;

    @Autowired
    JwtTokenizer jwtTokenizer;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        System.out.println("Username: " + loginRequest.getUserName());
        System.out.println("Password: " + loginRequest.getPassword());

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUserName(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenizer.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthResponse(jwt));
    }

    @PostMapping("/reqister")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {

        if(userRepository.existsByUsername(registerRequest.getUserName())) {
            return ResponseEntity.badRequest().body("Username already taken");
        }

        ApplicationUser user = new ApplicationUser(registerRequest.getUserName(), registerRequest.getPassword(), "USER");

        return ResponseEntity.ok("User created successfully!");
    }

    @GetMapping(value = "/users/details")
    public UserDetails getDetails(Authentication authentication) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } else {
            return null;
        }

    }

}
