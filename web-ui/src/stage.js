var Stage = React.createClass({
    getInitialState: function () {
        return ({
            stage: new createjs.Stage("demoCanvas")
        })
    },
    render: function () {
        return (
            <div>
                <canvas id="demoCanvas" width="500" height="300"></canvas>
            </div>
        );
    },
    init: function () {
        console.log("hello world");
        var stage = new createjs.Stage("demoCanvas");
        var circle = new createjs.Shape();
        circle.graphics.beginFill("DeepSkyBlue").drawCircle(0, 0, 50);
        circle.x = 100;
        circle.y = 100;
        stage.addChild(circle);
        stage.update();
    },
    componentDidMount: function() {
        this.init();
    }

});
ReactDOM.render(
    <Stage />,
    document.getElementById('stage')
);