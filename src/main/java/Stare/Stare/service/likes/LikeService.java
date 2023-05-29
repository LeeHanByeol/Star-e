package Stare.Stare.service.likes;

import Stare.Stare.domain.likes.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LikeService {

    private final LikeRepository likeRepository;

    //좋아요
    @Transactional
    public void likes(Long user_id, Long post_id){
        likeRepository.like(user_id, post_id);
    }

    //좋아요 취소
    @Transactional
    public void cancelLikes(Long user_id, Long post_id){
        likeRepository.cancelLike(user_id, post_id);
    }

}
