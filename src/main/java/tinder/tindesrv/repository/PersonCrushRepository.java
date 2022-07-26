package tinder.tindesrv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tinder.tindesrv.entity.PersonCrush;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PersonCrushRepository extends JpaRepository<PersonCrush, Long> {
    /**
     * Возвращает список любимцев найденных по user id
     *
     * @param userId id клиента
     * @return Set<Integer> id любимцев
     */
    Set<PersonCrush> getCrushIdByUserId(Long userId);

    /**
     * Возвращает список клиентов найденных по crush id
     *
     * @param crushId id любимца
     * @return Set<Integer> id клиентов
     */
    Set<PersonCrush> getUserIdByCrushId(Long crushId);

    /**
     * Возвращает список клиентов с которым был мэтч
     *
     * @param userId id клинта
     * @return Set<Integer> id клиентов
     */
    @Query(value = "SELECT p1.crushId FROM PersonCrush p1, PersonCrush p2 WHERE p1.userId=p2.crushId " +
            "AND p2.userId=p1.crushId AND p1.userId=?1")
    Set<Long> getMatchesId(Long userId);

    /**
     * Найти все связи для контакта и любимца
     *
     * @param userId  id клиентов
     * @param crushId id любимцев
     * @return List<PersonCrush> лист связей
     */
    List<Optional<PersonCrush>> findByUserIdInAndCrushIdIn(List<Long> userId, List<Long> crushId);

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