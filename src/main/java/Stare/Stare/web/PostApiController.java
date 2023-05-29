package Stare.Stare.web;

import Stare.Stare.service.posts.PostService;
import Stare.Stare.service.user.UserDetailsIMP;
import Stare.Stare.web.dto.posts.PostCreateRequestDto;
import Stare.Stare.web.dto.posts.PostResponseDto;
import Stare.Stare.web.dto.posts.PostUpdateRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class PostApiController {

    private final PostService postService;

    @Autowired
    public PostApiController(PostService postService){
        this.postService = postService;
    }


    //게시글 작성
    @PostMapping("/feeds")
    public PostResponseDto createPost(@Valid PostCreateRequestDto postCreateRequestDto,
                                     BindingResult bindingResult,
                                     @AuthenticationPrincipal UserDetailsIMP userDetails){

        if(bindingResult.hasErrors()){  //NotImplemented
        }

        return postService.createPost(postCreateRequestDto, userDetails.getUser());
    }

    //내가 쓴 모든 게시글 조회
    @GetMapping("/feeds/my-feed")
    public List<PostResponseDto> loadMyPost(@AuthenticationPrincipal UserDetailsIMP userDetails){
        return postService.loadMyPost(userDetails);
    }

    //특정 게시글 조회
    @GetMapping("/feeds/{feeds_id}")
    public PostResponseDto loadPost(@PathVariable Long post_id,
                                    @AuthenticationPrincipal UserDetailsIMP userDetails){
        return postService.loadPost(post_id, userDetails);
    }

    //특정 게시글 수정
    @PutMapping("/feeds/{feeds_id}")
    public PostResponseDto updatePost(@PathVariable Long post_id,
                                      PostUpdateRequestDto postUpdateRequestDto,
                                      @AuthenticationPrincipal UserDetailsIMP userDetails){
         return postService.updatePost(post_id, postUpdateRequestDto, userDetails);
    }

    //특정 게시글 삭제
    @DeleteMapping("/feeds/{feeds_id}")
    public void deletePost(@PathVariable Long post_id,
                                      @AuthenticationPrincipal UserDetailsIMP userDetails){
        postService.deletePost(post_id, userDetails);

        //Concern : redirection
        //return "/";
    }

}
