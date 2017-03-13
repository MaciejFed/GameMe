export default class GameObject{
    constructor(bitmapObject, RECTANGLE_SIZE){
        this.size = RECTANGLE_SIZE;
        this.x = RECTANGLE_SIZE * 0.1;
        this.y = RECTANGLE_SIZE * 0.1;
        this.bitmapObject = bitmapObject;
    }

    applyDirection(direction){
        this.x += direction.dX * this.size;
        this.y += direction.dY * this.size;
    };

}
