package tinder.tindesrv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tinder.tindesrv.entity.PersToPers;

public interface PersToPersRepository extends JpaRepository<PersToPers, Integer> {
}