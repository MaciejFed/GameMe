const RECTANGLE_SIZE = window.innerWidth / 20;


export default class Direction{
    constructor(dX, dY){
        this.dX = dX;
        this.dY = dY;
    }

    static mapToDirection(stringDirection){
        switch (stringDirection) {
            case "goUp();":
                return new Direction(0, -RECTANGLE_SIZE);
            case "goRight();":
                return new Direction(RECTANGLE_SIZE, 0);
            case "goDown();":
                return new Direction(0, RECTANGLE_SIZE);
            case "goLeft();":
                return new Direction(-RECTANGLE_SIZE, 0);
            default:
                return new Direction(0, 0);
        }
    }
}