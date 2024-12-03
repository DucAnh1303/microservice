package com.service.microservice.auth.config.security;

import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static com.service.microservice.auth.config.common.AccessRole.urlRoleMap;

@Component
public class CustomAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {


    @Override
    public AuthorizationDecision check(Supplier<Authentication> authenticationSupplier, RequestAuthorizationContext object) {
        List<String> authorities = authenticationSupplier.get().getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
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
