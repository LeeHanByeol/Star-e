package Stare.Stare.domain.follows;

import Stare.Stare.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Follow {

    //PROBLEM: DB 컬럼 재설정 필요함. 헷갈려서 ERD 컬럼명 변경했음.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "follower_id")          //구독자 id
    private User follower;

    @ManyToOne
    @JoinColumn(name = "followee_id")       //구독채널 id (팔로워에게 팔로우 '당하는' 사람의 id)
    private User followee;

    @Builder
    public Follow(User follower, User followee){
        this.follower = follower;
        this.followee = followee;
    }
}
