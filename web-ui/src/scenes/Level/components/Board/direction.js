const RECTANGLE_SIZE = window.innerWidth / 20;


export default class Direction{
    constructor(dX, dY){
        this.dX = dX;
        this.dY = dY;
    }

    static mapToDirection(stringDirection){
        switch (stringDirection) {
            case "Up":
                return new Direction(0, -RECTANGLE_SIZE);
            case "Right":
                return new Direction(RECTANGLE_SIZE, 0);
            case "Down":
                return new Direction(0, RECTANGLE_SIZE);
            case "Left":
                return new Direction(-RECTANGLE_SIZE, 0);
        }
    }
}