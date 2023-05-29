package Stare.Stare.web;

import Stare.Stare.service.tags.TagService;
import Stare.Stare.web.dto.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TagApiController {
    private final TagService tagService;

    @Autowired
    public TagApiController(TagService tagService){
        this.tagService = tagService;
    }

    //Concern: 프론트 설정으로 이게 되나...?
    //유저 태그하기
    @PostMapping("/feeds/{feeds_id}/tags")
    public List<UserResponseDto> tagging(@PathVariable Long post_id,
                                         @RequestParam Long taggedUser_id){
        tagService.tagging(taggedUser_id, post_id);
        return tagService.tagList(post_id);
    }

    //태그 취소하기
    @DeleteMapping("/feeds/{feeds_id}/tags")
    public List<UserResponseDto> cancelTagging(@PathVariable Long post_id,
                                             @RequestParam Long taggedUser_id){
        tagService.cancelTagging(taggedUser_id, post_id);
        return tagService.tagList(post_id);
    }

}
