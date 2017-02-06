package com.mfed.dto;

import java.util.List;

/**
 * Created by Maciej Fedorowiat
 * on 21/08/2016 14:16.
 * mfedorowiat@gmail.com
 */

public class GameMapDTO {

    public String id;
    public int levelNumber;
    public int height;
    public int width;
    public List<ObstacleDTO> obstacles;
    public List<String> introductionText;
    public List<String> functions;

    public GameMapDTO(String id, int levelNumber, int height, int width, List<ObstacleDTO> obstacles,
                      List<String> introductionText, List<String> functions) {
        this.id = id;
        this.levelNumber = levelNumber;
        this.height = height;
        this.width = width;
        this.obstacles = obstacles;
        this.introductionText = introductionText;
        this.functions = functions;
    }

    public GameMapDTO(){}

}
