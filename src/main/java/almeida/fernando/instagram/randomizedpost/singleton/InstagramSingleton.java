package almeida.fernando.instagram.randomizedpost.singleton;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.brunocvcunha.instagram4j.Instagram4j;

import java.io.IOException;
public class InstagramSingleton {

    //Singleton impl attributes
    private static volatile InstagramSingleton instance;
    private static Object mutex = new Object();

    //Instagram credentials
    public static String igUser;
    public static String igPass;

    //Attribute of singleton instance representing the IG account
    private Instagram4j instagram4j;

    final static Log logger = LogFactory.getLog(InstagramSingleton.class);

    {
        instagram4j = Instagram4j.builder()
                .username(igUser)
                .password(igPass)
                .build();

        instagram4j.setup();
        try {
            instagram4j.login();
            logger.info("Login sucessful : " + igUser);
        } catch (IOException e) {
        }
    }

    private InstagramSingleton(){}

    public static InstagramSingleton getInstance(){
        InstagramSingleton result = instance;
        if(result == null){
            synchronized (mutex){
                result = instance;
                if(result == null){
                    result = instance = new InstagramSingleton();
                }
            }
        }
        return result;
    }

    public Instagram4j getInstagram4j() {
        return instagram4j;
    }
}
