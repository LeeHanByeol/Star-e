package Stare.Stare.service.tags;

import Stare.Stare.domain.tags.TagRepository;
import Stare.Stare.domain.user.User;
import Stare.Stare.domain.user.UserRepository;
import Stare.Stare.web.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TagService {

    private final TagRepository tagRepository;
    private final UserRepository userRepository;

    //Tagging
    @Transactional
    public void tagging(Long user_id, Long post_id){
        tagRepository.tagging(user_id, post_id);
    }

    //Untagging
    @Transactional
    public void cancelTagging(Long user_id, Long post_id){
        tagRepository.cancelTagging(user_id, post_id);
    }

    //List of Tagged User
    @Transactional(readOnly = true)
    public List<UserResponseDto> tagList(Long post_id){

        List<UserResponseDto> taggedUserDtoList = new ArrayList<>();

        List<Long> taggedUserIdList = tagRepository.findByPost_id(post_id);
        for(Long userId : taggedUserIdList){
            User user = userRepository.findById(userId)
                    .orElseThrow(()-> new IllegalArgumentException("해당 유저가 존재하지 않습니다. 관리자에게 문의하세요."));

            taggedUserDtoList.add(new UserResponseDto(user));
        }
        return taggedUserDtoList;
    }
}
