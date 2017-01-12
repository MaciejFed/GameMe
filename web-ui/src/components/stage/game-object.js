

export default class GameObject{
    constructor(bitmapObject, RECTANGLE_SIZE){
        this.size = RECTANGLE_SIZE;
        this.gameX = 0;
        this.gameY = 0;
        this.x = RECTANGLE_SIZE * 0.1;
        this.y = RECTANGLE_SIZE * 0.1;
        this.bitmapObject = bitmapObject;
    }

    applyDirection(direction){
        this.x += direction.dX;
        this.y += direction.dY;
        this.gameX += direction.dX / this.size;
        this.gameY += direction.dY / this.size;
    };

    getPoint(){
        return{
            "x" : this.gameX,
            "y" : this.gameY
        };
    };
}
