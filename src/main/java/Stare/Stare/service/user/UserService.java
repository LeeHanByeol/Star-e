package Stare.Stare.service.user;

import Stare.Stare.domain.user.User;
import Stare.Stare.domain.user.UserRepository;
import Stare.Stare.web.dto.users.MyInfoLoadResponseDto;
import Stare.Stare.web.dto.users.MyInfoUpdateRequestDto;
import Stare.Stare.web.dto.users.MyInfoUpdateResponseDto;
import Stare.Stare.web.dto.users.SignUpRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    //회원가입
    @Transactional
    public void register(SignUpRequestDto signUpRequestDto){

        //email
        String email = signUpRequestDto.getEmail();
        //email 중복 체크
        if(userRepository.findByEmail(email).isPresent()){
            throw new IllegalArgumentException("이미 가입된 email 입니다.");
        }

        //CONCERN 이거 누출 위험 없나?
        //암호화
        String password = passwordEncoder.encode(signUpRequestDto.getPassword());

        //실명
        String name = signUpRequestDto.getName();

        //닉네임
        String nickname = signUpRequestDto.getNickname();

        User user = new User(email, password, name, nickname);
        userRepository.save(user);

    }


    //내 개인 정보 조회 = 수정 페이지 로딩
    public MyInfoLoadResponseDto loadMyInfo(UserDetailsIMP userDetails){
        Optional<User> _user = userRepository.findByEmail(userDetails.getUsername());

        if(_user.isEmpty()){
            throw new IllegalArgumentException("잘못된 접근입니다.");
        }
        return new MyInfoLoadResponseDto(_user.get());
    }

    //내 개인정보 업데이트
    public MyInfoUpdateResponseDto updateMyInfo(MyInfoUpdateRequestDto myInfoUpdateRequestDto, UserDetailsIMP userDetails){
        Optional<User> _user = userRepository.findByEmail(userDetails.getUsername());

        if(_user.isEmpty()){
            throw new IllegalArgumentException("잘못된 접근입니다.");
        }

        _user.get().update(myInfoUpdateRequestDto);
        return new MyInfoUpdateResponseDto(_user.get());
    }
}
