package Stare.Stare.service.follows;

import Stare.Stare.domain.follows.FollowRepository;
import Stare.Stare.domain.user.User;
import Stare.Stare.domain.user.UserRepository;
import Stare.Stare.service.user.UserDetailsIMP;
import Stare.Stare.web.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    //팔로우 하기
    @Transactional
    public void follow(Long followee_id, UserDetailsIMP userDetails){

        User follower = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("로그인이 필요합니다."));
        followRepository.follow(follower.getId(), followee_id);

    }

    //팔로우 취소
    @Transactional
    public void cancelFollow(Long followee_id, UserDetailsIMP userDetails){
        User follower = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("로그인이 필요합니다."));
        followRepository.cancelFollow(follower.getId(), followee_id);
    }


    //follower가 follow하는 followee들의 리스트
    @Transactional(readOnly = true)
    public List<UserResponseDto> followList(Long follower_id){

        List<UserResponseDto> followDtoList = new ArrayList<>();

        List<Long> followeeIdList = followRepository.findByFollower_id(follower_id);
        for(Long followee_id : followeeIdList){
            User followee = userRepository.findById(followee_id)
                    .orElseThrow(()-> new IllegalArgumentException("해당 유저가 존재하지 않습니다. 관리자에게 문의하세요."));

            followDtoList.add(new UserResponseDto(followee));
        }
        return followDtoList;
    }



}
