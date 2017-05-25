package com.mfed.dto;

import java.util.List;

/**
 * Created by Maciej Fedorowiat
 * on 21/08/2016 14:16.
 * mfedorowiat@gmail.com
 */

public class GameMapDTO {

    public int height;
    public int width;
    public List<Pair> obstacles;

    public GameMapDTO(int height, int width, List<Pair> obstacles) {
        this.height = height;
        this.width = width;
        this.obstacles = obstacles;
    }

    public GameMapDTO(){}

}
