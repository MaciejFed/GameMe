package com.mfed.dto;

import java.util.List;

/**
 * Created by Maciej Fedorowiat
 * on 06/02/2017 20:47.
 * mfedorowiat@gmail.com
 */
public class LevelDTO {
    public GameMapDTO gameMap;
    public List<String> introductionText;
    public List<String> functions;
    public int number;

    public LevelDTO(GameMapDTO gameMapDTO, List<String> introductionText, List<String> functions, int number) {
        this.gameMap = gameMapDTO;
        this.introductionText = introductionText;
        this.functions = functions;
        this.number = number;
    }

    public LevelDTO() {

    }
}
