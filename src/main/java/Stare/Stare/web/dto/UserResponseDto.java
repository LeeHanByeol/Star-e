package Stare.Stare.web.dto;

import Stare.Stare.domain.user.User;
import lombok.Getter;

@Getter
public class UserResponseDto {

    //User table의 id
    //private Long id;        //CONCERN : 필요한가...?

    //호출된 유저 정보
    private final String email;
    private final String nickname;
    private final String profile_image;

    public UserResponseDto(String email, String nickname, String profile_image){
        //this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.profile_image = profile_image;
    }

    public UserResponseDto(User user){
        this.email = user.getEmail();
        this.nickname = user.getNickname();
        this.profile_image = user.getProfile_image();
    }


}
