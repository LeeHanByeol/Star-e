package Stare.Stare.service.posts;

import Stare.Stare.domain.posts.Post;
import Stare.Stare.domain.posts.PostRepository;
import Stare.Stare.domain.user.User;
import Stare.Stare.domain.user.UserRepository;
import Stare.Stare.service.user.UserDetailsIMP;
import Stare.Stare.web.PostApiController;
import Stare.Stare.web.dto.posts.PostCreateRequestDto;
import Stare.Stare.web.dto.posts.PostResponseDto;
import Stare.Stare.web.dto.posts.PostUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    //게시글 작성
    @Transactional
    public PostResponseDto createPost(@Valid PostCreateRequestDto postCreateRequestDto,User user){
        Post post = new Post(postCreateRequestDto, user);
        postRepository.save(post);

        return new PostResponseDto(post, false);
    }

    //내가 쓴 모든 게시글 조회
    @Transactional
    public List<PostResponseDto> loadMyPost(UserDetailsIMP userDetails){

        //비회원이 조회 중
        if(userDetails == null){
            throw new IllegalArgumentException("로그인이 필요합니다.");          //CONCERN: handler로 redirection
        }

        List<Post> wholePosts = postRepository.findAll();
        User user = userRepository.findByEmail(userDetails.getUsername())
                        .orElseThrow(()->new IllegalArgumentException("회원정보를 가져올 수 없습니다. 관리자에게 문의하세요."));

        List<PostResponseDto> myPostResponseDtoList = new ArrayList<>();
        boolean isLiked;
        for(Post post : wholePosts){
            isLiked = false;
            //NotImplemented 좋아요 구현
            myPostResponseDtoList.add(new PostResponseDto(post, isLiked));
        }
        return myPostResponseDtoList;
    }

    //특정 게시글 조회
    @Transactional
    public PostResponseDto loadPost(Long post_id, UserDetailsIMP userDetails){

        Post post = postRepository.findById(post_id)
                       .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));

        //비회원이 조회 중이라면,
        if(userDetails == null){
            return new PostResponseDto(post, false);              //로그인 하지 않은 회원은 무조건 게시글 좋아요 false일 것
        }


        //회원이 조회 중이라면,
        User user = userRepository.findByEmail(userDetails.getUsername())
                         .orElseThrow(()->new IllegalArgumentException("회원정보를 가져올 수 없습니다. 관리자에게 문의하세요."));
        boolean isLiked = false;

        //NotImplemented 해당 회원이 좋아요 눌렀는지 여부 구현해야함
        return new PostResponseDto(post, isLiked);
    }



    //특정 게시글 수정
    @Transactional
    public PostResponseDto updatePost(Long post_id,
                                      PostUpdateRequestDto postUpdateRequestDto,
                                      UserDetailsIMP userDetails){

        Post post = postRepository.findById(post_id)
                        .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 게시글입니다."));
        User user = userRepository.findByEmail(userDetails.getUsername())
                        .orElseThrow(() -> new IllegalArgumentException("로그인이 필요합니다."));

        if(post.getUser().getId() != user.getId()){
            throw new IllegalArgumentException("다른 사람의 게시글을 수정할 수 없습니다.");
        }

        boolean isLiked = false;
        post.update(postUpdateRequestDto);
        //NotImplemented 해당 회원이 좋아요 눌렀는지 여부 구현해야함
        return new PostResponseDto(post, isLiked);
    }


    //특정 게시글 삭제
    @Transactional
    public void deletePost(Long post_id,
                           UserDetailsIMP userDetails){
        Post post = postRepository.findById(post_id)
                        .orElseThrow(()->new IllegalArgumentException("존재하지 않는 게시글입니다."));
        User user = userRepository.findByEmail(userDetails.getUsername())
                        .orElseThrow(() -> new IllegalArgumentException("로그인이 필요합니다."));

        if(post.getUser().getId() != user.getId()){
            throw new IllegalArgumentException("다른 사람의 게시글을 삭제할 수 없습니다.");
        }

        postRepository.delete(post);
    }

}
