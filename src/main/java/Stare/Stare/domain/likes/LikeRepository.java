package Stare.Stare.domain.likes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    //좋아요 누름
    @Modifying(clearAutomatically = true)
    @Query(value = "INSERT INTO LIKE(user_id, post_id) " +
                                  "VALUES(:user_id, :post_id)",
            nativeQuery = true)
    void likes(Long user_id, Long post_id);

    //좋아요 해제
    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM LIKE" +
                         "WHERE user_id = :user_id " +
                                "AND post_id = :post_id",
            nativeQuery = true)
    void cancelLikes(Long user_id, Long post_id);


    //
    Optional<Like> findByUser_idAndPost_id(@Param(value = "user_id") Long user_id, @Param(value = "post_id") Long post_id);

}
