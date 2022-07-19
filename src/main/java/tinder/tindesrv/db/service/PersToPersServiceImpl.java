package tinder.tindesrv.db.service;

import org.springframework.stereotype.Service;
import tinder.tindesrv.entity.PersToPers;
import tinder.tindesrv.repository.PersToPersRepository;

import java.util.List;

@Service
public class PersToPersServiceImpl implements PersToPersService {

    private final PersToPersRepository persToPersRepository;

    public PersToPersServiceImpl(PersToPersRepository persToPersRepository) {
        this.persToPersRepository = persToPersRepository;
    }

    @Override
    public void create(PersToPers persToPers) {
        persToPersRepository.save(persToPers);
    }


    @Override
    public List<PersToPers> readAll() {
        return persToPersRepository.findAll();
    }

    @Override
    public PersToPers read(int id) {
        return persToPersRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public boolean update(PersToPers persToPers, int id) {
        if (persToPersRepository.existsById(id)) {
            persToPers.setId(id);
            persToPersRepository.save(persToPers);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        if (persToPersRepository.existsById(id)) {
            persToPersRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
