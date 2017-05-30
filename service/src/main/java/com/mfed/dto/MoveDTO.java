package com.mfed.dto;

import java.util.List;

/**
 * Created by Maciej Fedorowiat
 * on 06/02/2017 18:33.
 * mfedorowiat@gmail.com
 */
public class MoveDTO {
    public int dX, dY, rotation;
    public List<Pair> collectedDiamonds;

    public MoveDTO(int dX, int dY, int rotation, List<Pair> collectedDiamonds){
        this.dX = dX;
        this.dY = dY;
        this.rotation = rotation;
        this.collectedDiamonds = collectedDiamonds;
    }
}
