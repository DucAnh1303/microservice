package com.service.microservice.auth.config.security;

import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@Component
public class CustomAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {

    private static final String ROLE_ADMIN = "ADMIN";
    private static final String ROLE_MANAGE = "MANAGE";
    private static final String ROLE_USER = "USER";

    private final Map<String, List<String>> urlRoleMap = Map.of(
            "/auth/search", List.of(ROLE_ADMIN, ROLE_MANAGE)
    );


    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext object) {
        List<String> authorities = authentication.get().getAuthorities().stream()
                .map(grantedAuthority -> grantedAuthority.getAuthority())
                .toList();

        String requestUrl = object.getRequest().getRequestURI();

        for (Map.Entry<String, List<String>> entry : urlRoleMap.entrySet()) {
            if (requestUrl.matches(entry.getKey())) {
                List<String> allowedRoles = entry.getValue();
                boolean hasAccess = authorities.stream().anyMatch(allowedRoles::contains);
                return new AuthorizationDecision(hasAccess);
            }
        }

        return new AuthorizationDecision(false);
    }
}
