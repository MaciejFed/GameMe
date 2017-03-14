export default class GameObject{
    constructor(bitmapObject, arrowBitmap, RECTANGLE_SIZE){
        this.size = RECTANGLE_SIZE;
        this.x = RECTANGLE_SIZE * 0.1;
        this.y = RECTANGLE_SIZE * 0.1;
        this.rotation = 1;
        this.bitmapObject = bitmapObject;
        this.bitmapArrow = arrowBitmap;
    }

    applyDirection(direction){
        this.x += direction.dX * this.size;
        this.y += direction.dY * this.size;
        this.rotation += direction.rotation % 4;
    };

}
