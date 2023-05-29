package Stare.Stare.domain.posts;

import Stare.Stare.domain.user.User;
import Stare.Stare.web.dto.posts.PostCreateRequestDto;
import Stare.Stare.web.dto.posts.PostUpdateRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Post extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, nullable = true)
    private String location;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;


    @Builder
    public Post(PostCreateRequestDto postCreateRequestDto, User user){
        this.location = postCreateRequestDto.getLocation();
        this.user = user;
    }

    public void update(PostUpdateRequestDto postUpdateRequestDto){
        this.location = postUpdateRequestDto.getLocation();
    }

}
