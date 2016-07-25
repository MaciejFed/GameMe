var RECTANGLE_SIZE = 75;


var Stage = React.createClass({
    stage: null,
    ball: null,
    directions: [new Direction(150, 150)],
    render: function () {
        return (
            <div>
                <canvas id="gameStage"
                        width={RECTANGLE_SIZE * this.props.widthRectangles}
                        height={RECTANGLE_SIZE * this.props.heightRectangles}>
                </canvas>
                <button onClick={() => this.animateBall(this.directions)}>Animate</button>
            </div>
        );
    },
    init: function () {
        this.ball = new GameObject(new createjs.Shape());
        console.log(this.ball);
        this.ball.circle.graphics.beginFill("DeepSkyBlue").drawCircle(0, 0, 25);
        this.stage.addChild(this.ball.circle);
        for (var h = 0; h < this.props.heightRectangles; h++){
            for (var w = 0; w < this.props.widthRectangles; w++){
                var border = new createjs.Shape();
                border.graphics.beginStroke("#306");
                border.graphics.setStrokeStyle(1);
                border.graphics.drawRect(0, 0, RECTANGLE_SIZE, RECTANGLE_SIZE);
                border.x = w * RECTANGLE_SIZE;
                border.y = h * RECTANGLE_SIZE;
                this.stage.addChild(border);
            }
        }
        this.stage.update();
    },
    animateBall: function(directions) {
        var circleTween = createjs.Tween.get(this.ball.circle);
        for(var i = 0; i < directions.length; i++){
            circleTween.to({ x: directions[i].x, y: directions[i].y}, 1000, createjs.Ease.getPowInOut(4));
        }
        createjs.Ticker.setFPS(60);
        createjs.Ticker.addEventListener("tick", this.stage);
    },
    componentDidMount: function () {
        this.stage = new createjs.Stage("gameStage");
        this.init();
    }

});

function GameObject(circle) {
    this.x = 0;
    this.y = 0;
    this.circle = circle;
}

function Direction(x, y) {
    this.x = x;
    this.y = y;
}
ReactDOM.render(
    <Stage heightRectangles="5" widthRectangles="8"/>,
    document.getElementById('stage')
);