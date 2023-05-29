package Stare.Stare.web.dto.users;


import Stare.Stare.domain.user.User;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class SignUpRequestDto {

    @NotBlank(message = "필수 작성 항목입니다.")
    @Email(message = "email을 입력해야합니다.")
    private String email;

    @NotBlank(message = "필수 작성 항목입니다.")
    @Size(min = 8, max = 20, message = "비밀번호는 8자~12자여야 합니다.")
    private String password;
    @NotBlank(message = "필수 작성 항목입니다.")
    @Size(max = 10, message = "이름은 10자까지 가능합니다.")
    private String name;

    @NotBlank(message = "필수 작성 항목입니다.")
    @Size(max = 10, message = "닉네임은 10자까지 가능합니다.")
    private String nickname;

    //profileImage는 null값이므로 굳이 넣지 않았음.


    public User toEntity(){
        return User.builder()
                .email(email)
                .password(password)
                .name(name)
                .nickname(nickname)
                .build();
    }




}
