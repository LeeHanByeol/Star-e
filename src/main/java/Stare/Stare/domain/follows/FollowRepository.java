package Stare.Stare.domain.follows;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    //팔로우 하기
    @Modifying(clearAutomatically = true)
    @Query(value = "INSERT INTO FOLLOW(follower_id, followee_id) " +
            "VALUES(:follower_id, :followee_id)",
            nativeQuery = true)
    void follow(Long follower_id, Long followee_id);

    //팔로우 해제
    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM FOLLOW" +
            "WHERE follower_id = :follower_id " +
            "AND followee_id = :followee_id",
            nativeQuery = true)
    void cancelFollow(Long follower_id, Long followee_id);


    //내가 팔로우 중인 '팔로위'들의 id 리스트
    @Query(value = "SELECT followee_id FROM FOLLOW" +
            "WHERE follower_id = :follower_id",
            nativeQuery = true)
    List<Long> findByFollower_id(@Param(value = "follower_id") Long follower_id);

    //CONCERN : 역시 안 쓸 듯?
    //나를 팔로우 중인 '팔로워'들의 id 리스트
    @Query(value = "SELECT follower_id FROM FOLLOW" +
            "WHERE followee_id = :followee_id",
            nativeQuery = true)
    List<Long> findByFollowee_id(@Param(value = "followee_id") Long followee_id);
}
