package Stare.Stare.web.dto.users;

import Stare.Stare.domain.user.User;
import lombok.Getter;

@Getter
public class MyInfoLoadResponseDto {

    private final String email;
    private final String name;
    private final String nickname;
    private final String profile_image;

    public MyInfoLoadResponseDto(String email, String name, String nickname, String profile_image){
        this.email = email;
        this.name = name;
        this.nickname = nickname;
        this.profile_image = profile_image;
    }

    public MyInfoLoadResponseDto(User user){
        this.email = user.getEmail();
        this.name = user.getName();
        this.nickname = user.getNickname();
        this.profile_image = user.getProfile_image();
    }


}
