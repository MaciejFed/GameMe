var RECTANGLE_SIZE = 100;

var React = require('react');
var DirectionButtonWrapper = require('./directon');
var GameMap = require('./map');


// tempMap.obstacles = [{1, 0}, {2, 0}];

var Stage = React.createClass({
    stage: null,
    widthRectangles: null,
    ball: new GameObject(new createjs.Shape()),
    directionValues: [],

    getInitialState: function () {
        this.loadLevelMap();
        return ({})
    },
    loadLevelMap: function () {
        this.map = new GameMap("");
    },
    render: function () {
        console.log(this.handleDirectionsValueChange);
        return (
              <div>
                <canvas id="gameStage"
                        width={RECTANGLE_SIZE * this.map.width}
                        height={RECTANGLE_SIZE * this.map.height}>
                </canvas>
                <button onClick={() => this.animateBall(this.directionValues)}>Animate</button>
                  <DirectionButtonWrapper onChange = {this.handleDirectionsValueChange}/>
            </div>
        );
    },
    init: function () {
        this.ball.circle.graphics.beginFill("DeepSkyBlue").drawCircle(0, 0, RECTANGLE_SIZE / 3);
        this.ball.circle.x = this.ball.x;
        this.ball.circle.y = this.ball.y;
        this.stage.addChild(this.ball.circle);
        for (var h = 0; h < this.map.height; h++) {
            for (var w = 0; w < this.map.width; w++) {
                var border = new createjs.Shape();
                border.graphics.beginStroke("#306");
                border.graphics.setStrokeStyle(1);
                if (this.map.openField({x: w, y: h}))
                    border.graphics.beginFill("DeepSkyBlue");
                border.graphics.drawRect(0, 0, RECTANGLE_SIZE, RECTANGLE_SIZE);
                border.x = w * RECTANGLE_SIZE;
                border.y = h * RECTANGLE_SIZE;
                this.stage.addChild(border);
            }
        }
        this.stage.update();
    },
    animateBall: function(directionsValues) {
        var directions = directionsValues.map(mapToDirection);
        var circleTween = createjs.Tween.get(this.ball.circle);
        for(var i = 0; i < directions.length; i++){
            this.ball.applyDirection(directions[i]);
            circleTween.to({ x: this.ball.x, y: this.ball.y}, 1000, createjs.Ease.getPowInOut(4));
        }
        createjs.Ticker.setFPS(60);
        createjs.Ticker.addEventListener("tick", this.stage);
    },
    handleDirectionsValueChange: function (key, newValue) {
        this.directionValues[key] = newValue;
    },
    componentDidMount: function () {
        this.stage = new createjs.Stage("gameStage");
        this.init();
    }

});

function GameObject(circle) {
    this.x = RECTANGLE_SIZE/2;
    this.y = RECTANGLE_SIZE/2;
    this.circle = circle;
}

GameObject.prototype.applyDirection = function (direction) {
    this.x += direction.dX;
    this.y += direction.dY;
};

function Direction(dX, dY) {
    this.dX = dX;
    this.dY = dY;
}

function mapToDirection(stringDirection) {
    switch (stringDirection){
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

module.exports = Stage;