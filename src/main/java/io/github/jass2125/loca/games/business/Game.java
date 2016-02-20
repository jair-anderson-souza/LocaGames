/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.loca.games.business;

/**
 * @author Anderson Souza <jair_anderson_bs@hotmail.com>
 * <jair_anderson_bs@hotmail.com>
 * @since 14:17:38, 20-Feb-2016 Package io.github.jass2125.loca.games.business
 * Project Name loca-games Encoding UTF-8 File Name Game.java
 */
public class Game {

    private Long id;
    private byte img[];
    private String description;

    public Game() {
    }

    public Game(String description, byte img[]) {
        this.description = description;
        this.img = img;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }
}
