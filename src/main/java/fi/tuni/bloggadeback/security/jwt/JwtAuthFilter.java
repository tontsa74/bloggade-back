package fi.tuni.bloggadeback.security.jwt;

import fi.tuni.bloggadeback.user.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This class handles the Token validation
 */
public class JwtAuthFilter extends OncePerRequestFilter {

    /**
     * JwtTokenizer instance
     */
    @Autowired
    private JwtTokenizer jwtTokenizer;

    /**
     * UserDetailsServiceImpl instance
     */
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    /**
     * Checks if the token from the httpRequest is valid
     *
     * @param request Request from the page
     * @param response Response from the server
     * @param chain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        try {
            String jwt = getJwtFromRequest(request);

            if(StringUtils.hasText(jwt) && jwtTokenizer.validateToken(jwt)) {
                long userId = jwtTokenizer.getUserIdFromJWT(jwt);

                UserDetails userDetails = userDetailsService.loadByUserId(userId);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        } catch (Exception ex) {
            logger.error("Could not set user authentication in security context", ex);
        }

        chain.doFilter(request, response);
    }

    /**
     * Tries to get Token from the request
     *
     * @param request http request
     * @return Token if it exists
     */
    private String getJwtFromRequest(HttpServletRequest request) {

        String bearerToken = request.getHeader("Authorization");
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }

        return null;
    }
}
