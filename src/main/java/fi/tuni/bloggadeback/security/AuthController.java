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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * This class handles users login and registration
 */
@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {

    /**
     * AuthenticationManager instance
     */
    @Autowired
    AuthenticationManager authenticationManager;

    /**
     * ApplicationUserRepository instance
     */
    @Autowired
    ApplicationUserRepository userRepository;

    /**
     * JwtTokenizer instance
     */
    @Autowired
    JwtTokenizer jwtTokenizer;

    /**
     * PasswordEncoder instance
     */
    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * Handles login.
     * Checks if user exists and user has authorization
     *
     * @param loginRequest Object which contains username and password
     * @return result of the authentication
     */
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

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

    /**
     * Handles registration.
     * Checks that username doesn't exist and then saves user to repository
     *
     * @param registerRequest Object which contains username and password
     * @return result of the registration
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {

        if(userRepository.existsByUsername(registerRequest.getUserName())) {
            return ResponseEntity.badRequest().body("Username already taken");
        }

        ApplicationUser user = new ApplicationUser(registerRequest.getUserName(), passwordEncoder.encode(registerRequest.getPassword()), "ROLE_USER");
        userRepository.save(user);

        return ResponseEntity.ok("User created successfully!");
    }

    /**
     * Returns users authorization info
     *
     * @param authentication
     * @return Authorization info
     */
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
