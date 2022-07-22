package tinder.tindesrv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tinder.tindesrv.entity.PersToPers;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

public interface PersToPersRepository extends JpaRepository<PersToPers, Integer> {
    @Query(value = "select p.crushId from PersToPers p where p.userId=?1")
    Set<Integer> getDistinctCrushIdByUserId(Integer userId);

    @Query(value = "select p.userId from PersToPers p where p.crushId=?1")
    Set<Integer> getDistinctUserIdByCrushId(Integer crushId);

    @Query(value = "select p1.crushId from PersToPers p1, PersToPers p2 where p1.userId=p2.crushId " +
            "and p2.userId=p1.crushId and p1.userId=?1")
    Set<Integer> getMatchesId(Integer userId);

    List<PersToPers> getByUserIdAndCrushId(Integer userId, Integer crushId);
    @Transactional
    void deleteByUserIdAndCrushId(Integer userId, Integer crushId);
}