package p.hh.smvc.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.util.StringUtils;

public class DomainUsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    private TokenService tokenService;

    public DomainUsernamePasswordAuthenticationProvider(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = String.valueOf(authentication.getPrincipal());
        String password = String.valueOf(authentication.getCredentials());


        if ("user".equals(username) && "pass".equals(password)) {
            AuthenticationWithToken resultOfAuthentication =  new AuthenticationWithToken(username, password);
            String newToken = tokenService.generateNewToken();
            resultOfAuthentication.setToken(newToken);
            resultOfAuthentication.setAuthenticated(true);
            tokenService.store(newToken, resultOfAuthentication);
            return resultOfAuthentication;

        } else {
            throw new BadCredentialsException("Invalid Domain User Credentials");
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
