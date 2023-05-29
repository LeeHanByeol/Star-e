package Stare.Stare.web;

import Stare.Stare.service.follows.FollowService;
import Stare.Stare.service.user.UserDetailsIMP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FollowApiController {

    private final FollowService followService;
    @Autowired
    public FollowApiController(FollowService followService){
        this.followService = followService;
    }

    //팔로우 하기
    @PostMapping("/follow/{followee_id}")
    public ResponseEntity<?> follow(@PathVariable Long followee_id,
                                    @AuthenticationPrincipal UserDetailsIMP userDetails){
        followService.follow(followee_id, userDetails);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //팔로우 취소
    @DeleteMapping("/follow/{followee_id}")
    public ResponseEntity<?> cancelFollow(@PathVariable Long followee_id,
                                    @AuthenticationPrincipal UserDetailsIMP userDetails){
        followService.cancelFollow(followee_id, userDetails);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
