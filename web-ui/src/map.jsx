var tempMap = {};
tempMap.width = 4;
tempMap.height = 2;
tempMap.obstacles = [{x: 1, y: 0}, {x: 2, y: 0}, {x: 3, y: 1}];

function GameMap(map) {
    console.log(tempMap);
    this.width = tempMap.width;
    this.height = tempMap.height;
    this.obstacles = tempMap.obstacles;
}

GameMap.prototype.openField = function (point) {
    for (var i = 0; i < this.obstacles.length; i++) {
        if (this.obstacles[i].x == point.x && this.obstacles[i].y == point.y) {
            return true;
        }
    }
    return false;
};

module.exports = GameMap;


