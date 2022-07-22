package tinder.tindesrv.webservice;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tinder.tindesrv.entity.PersToPers;
import tinder.tindesrv.repository.PersToPersRepository;

import java.util.List;

class PictureServiceTest {
    @Autowired
    private PersToPersRepository persToPersRepository;
//    @SneakyThrows
//    @Test
//    void makePictureTest() {
//        new PictureWebService().makePicture("hialice");
//        System.out.println("wtf");
//    }
    @Test
    void getListTest() {
        List<PersToPers> persToPersList = persToPersRepository.getByUserIdAndLikes(1,true);
        System.out.println(persToPersList);
    }
}