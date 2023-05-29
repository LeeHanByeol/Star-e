package Stare.Stare.service.user;

import Stare.Stare.domain.user.User;
import Stare.Stare.domain.user.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class UserDetailsIMP implements UserDetails {

    private final User user;
    public UserDetailsIMP(User user){
        this.user = user;
    }

    public User getUser(){
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authority = new ArrayList<>();

        //관리자 계정의 이메일은 admin이다.
        if("admin".equals(user.getEmail())){
            authority.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
        }
        else{
            authority.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));
        }

        return authority;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {           //email 반환임. 오버라이딩 땜시...
        return user.getEmail();
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
