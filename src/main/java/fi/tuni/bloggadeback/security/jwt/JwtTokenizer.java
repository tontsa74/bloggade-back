package fi.tuni.bloggadeback.security.jwt;

import fi.tuni.bloggadeback.user.UserPrincipalImpl;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * This class parses information from the token
 */
@Component
public class JwtTokenizer {

    /**
     * Logger instance
     */
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenizer.class);

    /**
     * Token secret salt
     */
    private String jwtSecret = "Secret";

    /**
     * Token expiration time
     */
    private int jwtExpirationInMs = 860_000_000;

    /**
     * Generates token using authentication
     *
     * @param authentication Object which contains info about user authentication
     * @return generated token
     */
    public String generateToken(Authentication authentication) {

        UserPrincipalImpl userPrincipal = (UserPrincipalImpl) authentication.getPrincipal();

        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setSubject(Long.toString(userPrincipal.getId()))
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();

    }

    /**
     * Parses user's id from the token
     *
     * @param token token to be parsed
     * @return returns user's id
     */
    public Long getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

    /**
     * Checks if token is valid
     *
     * @param authToken token to be validated
     * @return boolean which tells if token is valid
     */
    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            logger.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty.");
        }
        return false;
    }

}
