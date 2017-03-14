package com.mfed.dto;

/**
 * Created by Maciej Fedorowiat
 * on 06/02/2017 18:33.
 * mfedorowiat@gmail.com
 */
public class MoveDTO {
    public int dX, dY, rotation;

    public MoveDTO(int dX, int dY, int rotation){
        this.dX = dX;
        this.dY = dY;
        this.rotation = rotation;
    }
}
