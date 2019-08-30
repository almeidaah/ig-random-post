package almeida.fernando.instagram.randomizedpost;


import almeida.fernando.instagram.randomizedpost.singleton.InstagramSingleton;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.brunocvcunha.instagram4j.requests.InstagramUploadPhotoRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Random;

@EnableScheduling
@Configuration
public class PostScheduler{

    @Value("${ig.user}")
    String igUser;

    @Value("${ig.password}")
    String igPassword;

    final static Log logger = LogFactory.getLog(PostScheduler.class);

    //“At every hour.”
    @Scheduled(fixedDelay = 3600000)
     void postRandom() throws IOException {

        String url = "https://picsum.photos/id/";
        File f = fetchImageFromUrl(url);

        InstagramSingleton.igUser = igUser;
        InstagramSingleton.igPass = igPassword;

        try {

            InstagramSingleton.getInstance()
                    .getInstagram4j()
                    .sendRequest(
                            new InstagramUploadPhotoRequest(f,
                                    "Posted automatically at :" + LocalDateTime.now())
                    );

            logger.info("Image posted sucessfully at :" + LocalDateTime.now());
        }catch(Exception e){
            postRandom();
        }
    }

    private File fetchImageFromUrl(String urlToFetch) throws IOException {
        Random r = new Random();
        int pictureId = r.nextInt((1000 - 0) + 1);

        logger.info("Fetch picture id : " + pictureId);

        URL url = new URL(urlToFetch + pictureId + "/1920/1080");
        BufferedImage img = ImageIO.read(url);
        File f = new File("temp.jpg");
        ImageIO.write(img, "jpg", f);

        return f;
    }

}