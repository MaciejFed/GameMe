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
    public List<Pair> diamonds;

    public GameMapDTO(int height, int width, List<Pair> obstacles, List<Pair> diamonds) {
        this.height = height;
        this.width = width;
        this.obstacles = obstacles;
        this.diamonds = diamonds;
    }

    public GameMapDTO(){}

}
