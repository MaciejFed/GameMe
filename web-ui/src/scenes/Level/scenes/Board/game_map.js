
function GameMap(map) {
    this.width = map.width;
    this.height = map.height;
    this.obstacles = map.obstacles;
    this.diamonds = map.diamonds;
    return this;
}

GameMap.prototype.openField = function (point) {
    for (let i = 0; i < this.obstacles.length; i++) {
        if (this.obstacles[i].x === point.x && this.obstacles[i].y === point.y) {
            return true;
        }
    }
    return false;
};

GameMap.prototype.diamondField = function (point) {
    for (let i = 0; i < this.diamonds.length; i++) {
        if (this.diamonds[i].x === point.x && this.diamonds[i].y === point.y) {
            return true;
        }
    }
    return false;
};

module.exports = GameMap;


