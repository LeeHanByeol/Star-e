package Stare.Stare.web.dto.users;

import Stare.Stare.domain.user.User;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
public class MyInfoUpdateResponseDto {

    private final String nickname;
    private final String profile_image;

    public MyInfoUpdateResponseDto(String nickname, String profile_image){
        this.nickname = nickname;
        this.profile_image = profile_image;
    }

    public MyInfoUpdateResponseDto(User user){
        this.nickname = user.getNickname();
        this.profile_image = user.getProfile_image();
    }

}
