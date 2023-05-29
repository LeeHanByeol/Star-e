package Stare.Stare.domain.tags;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {

    //해당 게시글에 특정 유저 태그
    @Modifying(clearAutomatically = true)
    @Query(value = "INSERT INTO TAG(user_id, post_id) " +
                        "VALUES(:user_id, :post_id)",
            nativeQuery = true)
    void tagging(Long user_id, Long post_id);

    //태그 취소
    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM TAG" +
                        "WHERE user_id = :user_id " +
                        "AND post_id = :post_id",
            nativeQuery = true)
    void cancelTagging(Long user_id, Long post_id);

    //해당 게시글에 태그된 유저id 리스트
    @Query(value = "SELECT user_id FROM TAG" +
                    "WHERE post_id = :post_id",
            nativeQuery = true)
    List<Long> findByPost_id(@Param(value = "post_id") Long post_id);

    //CONCERN: 근데 안 쓸 듯?
    //특정 유저를 태그한 게시글id 리스트
    @Query(value = "SELECT post_id FROM TAG" +
            "WHERE user_id = :user_id",
            nativeQuery = true)
    List<Long> findByUser_id(@Param(value = "user_id") Long user_id);
}
