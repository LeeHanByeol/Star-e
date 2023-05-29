package Stare.Stare.domain.user;

import Stare.Stare.web.dto.users.MyInfoUpdateRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User {

    //Problem: DB column명이랑 다를거임... 중간에 헷갈려서...수정 필

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     //auto-increment
    private Long id;

    @Column(length = 50, nullable = false)
    private String email;

    @Column(length = 20, nullable = false)
    private String password;

    @Column(length = 10, nullable = false)
    private String name;

    @Column(length = 10, nullable = false)
    private String nickname;

    private String profile_image;     //nullable

    @Builder
    public User(String email, String password, String name, String nickname){
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickname = nickname;

        this.profile_image = null;
    }

    public void update(MyInfoUpdateRequestDto myInfoUpdateRequestDto){
        this.nickname = myInfoUpdateRequestDto.getNickname();
        this.profile_image = myInfoUpdateRequestDto.getProfile_image();
    }

}
