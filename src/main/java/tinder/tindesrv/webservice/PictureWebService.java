package tinder.tindesrv.webservice;

import net.minidev.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tinder.tindesrv.exception.EmptyPictureException;
import tinder.tindesrv.exception.PictureServiceException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static tinder.tindesrv.constants.ConstantsUtil.PICTURE_URL;

@Service
public class PictureWebService {
    String createPersonUrl = PICTURE_URL;

    public InputStream makePicture(String text) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            JSONObject pictureJsonObject = new JSONObject();
            pictureJsonObject.put("text", text);
            HttpEntity<String> request = new HttpEntity<>(pictureJsonObject.toString(), headers);
            byte[] picture =
                    restTemplate.postForObject(createPersonUrl, request, byte[].class);
            if (picture == null) {
                throw new EmptyPictureException();
            }
            return new ByteArrayInputStream(picture);
        } catch (RuntimeException e) {
            throw new PictureServiceException(e);
        }
    }
}
