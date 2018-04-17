package com.he.web.security;

import com.he.model.entity.sys.SysResource;
import com.he.model.entity.sys.SysUser;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 存储用户登录信息
 */
@Getter
@Setter
public class AuthUserDetails implements UserDetails,Serializable {
    private SysUser user;
    private List<String> userRoles;
    private List<SysResource> resList;

    public AuthUserDetails(SysUser user,List<String> userRoles, List<SysResource> resList) {
        this.user = user;
        this.userRoles = userRoles;
        this.resList = resList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String roles = StringUtils.collectionToCommaDelimitedString(userRoles);
        return AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getAccount();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
