package Stare.Stare.service.user;

import Stare.Stare.domain.user.User;
import Stare.Stare.domain.user.UserRepository;
import Stare.Stare.domain.user.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceIMP implements UserDetailsService {

    @Autowired
    private final UserRepository userRepository;

    //이름이 아니라 email로 찾는 거임. 오버라이딩 하느라...
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> _user = this.userRepository.findByEmail(email);

        //가입하지 않은 이메일
        if(_user.isEmpty()){
            throw new UsernameNotFoundException("등록되지 않은 이메일입니다.");
        }

        User user = _user.get();
        return new UserDetailsIMP(user);

        /*
        List<GrantedAuthority> authority = new ArrayList<>();

        //관리자 계정의 이메일은 admin이다.
        if("admin".equals(email)){
            authority.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
        }
        else{
            authority.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));
        }


        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authority);
         */
    }
}
