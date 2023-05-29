package Stare.Stare.web.dto.users;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class MyInfoUpdateRequestDto {

    @NotBlank(message = "필수 작성 항목입니다.")
    @Size(max = 10, message = "닉네임은 10자까지 가능합니다.")
    private String nickname;

    private String profile_image;

    public MyInfoUpdateRequestDto(String nickname, String profile_image){
        this.nickname = nickname;
        this.profile_image = profile_image;
    }
}
