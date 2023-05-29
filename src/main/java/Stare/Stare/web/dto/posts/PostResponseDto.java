package Stare.Stare.web.dto.posts;

import Stare.Stare.domain.posts.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto {


    //post 관련
    private final Long post_id;
    private final String location;
    private final LocalDateTime createDate;
    private final LocalDateTime updateDate;
    private Boolean isLiked;

    //글 작성자 관련
    private final String nickname;       //글 작성자 닉네임
    private final String profile_url;           //글 작성자 프로필 이미지

    //Concern: 해당 포스트의 총 좋아요 개수
    //private final Long numOfLike;

    public PostResponseDto(Post post, boolean isLiked){

        this.post_id = post.getId();
        this.location = post.getLocation();
        this.createDate = post.getCreateDate();
        this.updateDate = post.getUpdateDate();
        this.isLiked = isLiked;

        this.nickname = post.getUser().getNickname();
        this.profile_url = post.getUser().getProfile_image();
    }


}
