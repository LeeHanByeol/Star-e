package Stare.Stare.web;

import Stare.Stare.service.likes.LikeService;
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
public class LikeApiController {

    private final LikeService likeService;
    @Autowired
    public LikeApiController(LikeService likeService){
        this.likeService = likeService;
    }


    //좋아요
    @PostMapping("/feeds/{feeds_id}/like")
    public ResponseEntity<?> likes(@PathVariable Long post_id,
                                @AuthenticationPrincipal UserDetailsIMP userDetails){
        likeService.likes(userDetails.getUser().getId(), post_id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //좋아요 취소
    @DeleteMapping("/feeds/{feeds_id}/like")
    public ResponseEntity<?> cancelLikes(@PathVariable Long post_id,
                            @AuthenticationPrincipal UserDetailsIMP userDetails){
        likeService.cancelLikes(userDetails.getUser().getId(), post_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
