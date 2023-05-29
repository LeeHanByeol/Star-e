package Stare.Stare.web;

import Stare.Stare.service.user.UserDetailsIMP;
import Stare.Stare.service.user.UserService;
import Stare.Stare.web.dto.users.MyInfoLoadResponseDto;
import Stare.Stare.web.dto.users.MyInfoUpdateRequestDto;
import Stare.Stare.web.dto.users.MyInfoUpdateResponseDto;
import Stare.Stare.web.dto.users.SignUpRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

//@RequiredArgsConstructor  -> 안 붙이는게 좋다함
@Controller
public class UserApiController {

    private final UserService userService;

    @Autowired
    public UserApiController(UserService userService){
        this.userService = userService;
    }

    //회원가입
    @GetMapping("/sign-up")
    public String registerForm(){
        return "users/userRegisterForm";
    }

    @PostMapping("/sign-up")
    public ResponseEntity register(@Valid SignUpRequestDto signUpRequestDto, BindingResult bindingResult){ //Concern responseEntity로 주는게 맞나...

        if(bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors());
        }

        userService.register(signUpRequestDto);

        //CONCERN "redirect:/"는 안되려나
        return ResponseEntity.ok().build();
    }


    //로그인
    @GetMapping("/login")
    public String loginForm(    @RequestParam(value = "error", required = false) String error,
                                @RequestParam(value = "exception", required = false) String exception,
                                Model model){

        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return "users/userLoginForm";
    }


    //내 개인 정보 조회 = 수정 페이지 로딩
    @GetMapping("/users/info")
    public MyInfoLoadResponseDto myInfoLoad(@AuthenticationPrincipal UserDetailsIMP userDetails){
        return userService.loadMyInfo(userDetails);
    }

    //내 개인 정보 업데이트
    @PutMapping("/users/info")
    public MyInfoUpdateResponseDto myInfoUpdate(@Valid MyInfoUpdateRequestDto myInfoUpdateRequestDto,
                                                BindingResult bindingResult,
                                                @AuthenticationPrincipal UserDetailsIMP userDetails){

        if(bindingResult.hasErrors()){  //NotImplemented

        }
        return userService.updateMyInfo(myInfoUpdateRequestDto, userDetails);
    }


}
