package common;

import java.io.Serializable;

/**
 * A class giving a Queue's informations
 *@author Nicolas MEURGUES
 *
 */
public class JMSConnectionInformations implements Serializable {
    private String url;
    private String login;
    private String password;
    private String nom;

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
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
}