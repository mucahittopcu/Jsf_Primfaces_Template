/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.bean;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author test
 */
@ManagedBean
@SessionScoped
public class LoginController implements Serializable {

    private String username;
    private String pass;
    public boolean isLogged = false;

    public LoginController() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String action() {

        if (username.equals("admin") && pass.equals("admin")) {
            isLogged = true;
            return "forum.xhtml?faces-redirect=true";
        } else {
            username = null;
            pass = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Wrong username or pass, please try again"));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            return "login.xhtml?faces-redirect=true";
        }
    }

}
