package tinder.tindesrv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tinder.tindesrv.entity.PersonCrush;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

public interface PersonCrushRepository extends JpaRepository<PersonCrush, Long> {
    /**
     * Возвращает список любимцев найденных по user id
     *
     * @param userId id клиента
     * @return Set<Integer> id любимцев
     */
    @Query(value = "select p.crushId from PersonCrush p where p.userId=?1")
    Set<Long> getCrushIdByUserId(Long userId);

    /**
     * Возвращает список клиентов найденных по crush id
     *
     * @param crushId id любимца
     * @return Set<Integer> id клиентов
     */
    @Query(value = "select p.userId from PersonCrush p where p.crushId=?1")
    Set<Long> getUserIdByCrushId(Long crushId);

    /**
     * Возвращает список клиентов с которым был мэтч
     *
     * @param userId id клинта
     * @return Set<Integer> id клиентов
     */
    @Query(value = "select p1.crushId from PersonCrush p1, PersonCrush p2 where p1.userId=p2.crushId " +
            "and p2.userId=p1.crushId and p1.userId=?1")
    Set<Long> getMatchesId(Long userId);

    /**
     * Найти всех контактов по user id или crush id
     *
     * @param userId  id клиента
     * @param crushId id любимца
     * @return List<PersonCrush> лист связей
     */
    List<PersonCrush> findDistinctByUserIdOrCrushId(Long userId, Long crushId);

    /**
     * Есть ли связь между клиентом и любимцем
     *
     * @param userId  id клиента
     * @param crushId id любимца
     * @return true - если есть, false - если нет
     */
    boolean existsByUserIdAndCrushId(Long userId, Long crushId);

    /**
     * Удаление связи между клиентом и любимцем
     *
     * @param userId  id клиента
     * @param crushId id любимца
     */
    @Transactional
    void deleteByUserIdAndCrushId(Long userId, Long crushId);
}