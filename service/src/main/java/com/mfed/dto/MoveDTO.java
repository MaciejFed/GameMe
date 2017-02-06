package com.mfed.dto;

/**
 * Created by Maciej Fedorowiat
 * on 06/02/2017 18:33.
 * mfedorowiat@gmail.com
 */
public class MoveDTO {
    public int x, y, rotation;

    public MoveDTO(int x, int y, int rotation){
        this.x = x;
        this.y = y;
        this.rotation = rotation;
    }
}
