package springbootLogin.springbootapp.config.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.util.CollectionUtils;
import java.io.IOException;

public class CustomHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final GrantedAuthority adminAuthority = new SimpleGrantedAuthority("ADMIN");
    private RequestCache requestCache = new HttpSessionRequestCache();

    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
                                        throws IOException, ServletException {

        String targetUrl;
        if (isAdminAuthority(authentication)){
            targetUrl = "/admin.html";
        }
        else {
            targetUrl = "/user.html";
        }
        clearAuthenticationAttributes(request);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    protected boolean isAdminAuthority(final Authentication authentication){
        return !CollectionUtils.isEmpty(authentication.getAuthorities()) && authentication.getAuthorities().contains(adminAuthority);
    }

}



