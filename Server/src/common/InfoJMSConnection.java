package common;

import java.io.Serializable;

/**
 * Created by Sarah on 22/05/16.
 */
public class InfoJMSConnection implements Serializable {

    private String login;
    private String password;
    private String nom;
    private String url;


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
