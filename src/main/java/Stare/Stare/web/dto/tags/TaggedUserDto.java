package Stare.Stare.web.dto.tags;

import Stare.Stare.domain.user.User;
import lombok.Getter;

@Getter
public class TaggedUserDto {

    //Tag table id
    //private Long id;        //CONCERN : 필요한가...?

    //태그된 유저의 정보
    private String email;
    private String nickname;
    private String profile_image;

    public TaggedUserDto(String email, String nickname, String profile_image){
        //this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.profile_image = profile_image;
    }

    public TaggedUserDto(User user){
        this.email = user.getEmail();
        this.nickname = user.getNickname();
        this.profile_image = user.getProfile_image();
    }

}
