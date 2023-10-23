package com.anhnm.demo.dynamicsecurity.config;

import com.anhnm.demo.dynamicsecurity.entity.Permission;
import com.anhnm.demo.dynamicsecurity.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;

import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

/**
 * Used for dynamic authorization
 *
 * Logic is when setting endpoint with permission from database
 * Everytime user send request
 * Will check if user have permission with endpoint setting
 */
@Controller
public class MyAccessDecisionManager implements AccessDecisionVoter<Object> {

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    public int vote(Authentication authentication, Object object, Collection collection) {
        // Get the endpoint from object
        String endpoint = (String) object;

        // Get the permissions required for the endpoint
        List<String> permissions = getPermissionForEndpoint(endpoint);

        // Check if user has any of required permissions
        for (String permission : permissions) {
            if (authentication.getAuthorities().contains(new SimpleGrantedAuthority(permission))) {
                return AccessDecisionVoter.ACCESS_GRANTED;
            }
        }

        return AccessDecisionVoter.ACCESS_DENIED;
    }

    private List<String> getPermissionForEndpoint(String endpoint) {
        return permissionRepository.findByEndpoint(endpoint).stream().map(Permission::getPermission).toList();
    }
}
