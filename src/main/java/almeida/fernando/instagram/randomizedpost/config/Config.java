package almeida.fernando.instagram.randomizedpost.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Value("${ig.user}")
    String igUser;

    @Value("${ig.password}")
    String igPassword;

    public String getIgUser() {
        return igUser;
    }

    public String getIgPassword() {
        return igPassword;
    }

    public void setIgUser(String igUser) {
        this.igUser = igUser;
    }

    public void setIgPassword(String igPassword) {
        this.igPassword = igPassword;
    }
}
