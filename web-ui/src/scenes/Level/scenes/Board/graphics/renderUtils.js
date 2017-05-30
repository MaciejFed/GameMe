import { animateRobot } from './animations'
const RECTANGLE_SIZE = window.innerWidth / 20;
const OPEN_FIELD_COLOR = "#999";
const CLOSED_FIELD_COLOR = "#222";
let robot = require("./robot.png");
let arrow = require("./arrow-right-black.png");
let diamond = require("./diamond.png");
let robotBitmap = undefined;
let arrowBitmap = undefined;
let diamondBitmap = undefined;
let diamondBitmaps = [];

export function renderInitialMap(stage, gameMap) {
    unboundOldBitmaps(stage);
    renderMap(stage, gameMap);
    robotBitmap = new createjs.Bitmap(robot);
    arrowBitmap = new createjs.Bitmap(arrow);
    diamondBitmap = new createjs.Bitmap(diamond);
    robotBitmap.image.onload = () => renderRobot(stage, robotBitmap);
    arrowBitmap.image.onload = () => renderArrow(stage, arrowBitmap);
    diamondBitmap.image.onload = () => renderDiamonds(stage, diamondBitmap, gameMap);
}

export function animateRoad(stage, road) {
    return animateRobot(stage, robotBitmap, arrowBitmap, diamondBitmaps, road);
}

const unboundOldBitmaps = (stage) => {
    stage.removeChild(robotBitmap);
    stage.removeChild(arrowBitmap);
    stage.removeChild(diamondBitmap);
    diamondBitmaps.forEach(d => stage.removeChild(d.bitmap));
    diamondBitmaps = [];
};

const renderMap = (stage, gameMap) => {
    for (let h = 0; h < gameMap.height; h++) {
        for (let w = 0; w < gameMap.width; w++) {
            let border = new createjs.Shape();
            border.graphics.beginStroke(OPEN_FIELD_COLOR);
            border.graphics.setStrokeStyle(2);

            if (gameMap.openField({ x: w, y: h }))
                border.graphics.beginFill(CLOSED_FIELD_COLOR);


            border.graphics.drawRect(0, 0, RECTANGLE_SIZE, RECTANGLE_SIZE);
            border.x = w * RECTANGLE_SIZE;
            border.y = h * RECTANGLE_SIZE;
            stage.addChild(border);
            stage.update();
        }
    }
    stage.update();
    console.log("map rendered");
};

const renderRobot = (stage, robot) => {
    let scale = RECTANGLE_SIZE * 0.8 / robot.getBounds().width;
    robot.x = RECTANGLE_SIZE * 0.1;
    robot.y = RECTANGLE_SIZE * 0.1;
    robot.scaleX = scale;
    robot.scaleY = scale;
    stage.addChild(robot);
    stage.update();
};

const renderArrow = (stage, arrow) => {
    let scale = RECTANGLE_SIZE * 0.8 / arrow.getBounds().width * 0.25;
    arrow.x = RECTANGLE_SIZE * 0.15;
    arrow.y = RECTANGLE_SIZE * 0.15;
    arrow.regX = arrow.getBounds().width * 0.5;
    arrow.regY = arrow.getBounds().height * 0.5;
    arrow.scaleX = scale;
    arrow.scaleY = scale;
    stage.addChild(arrow);
    stage.update();
};

const renderDiamonds = (stage, diamond, gameMap) => {
    const scale = RECTANGLE_SIZE * 0.5 / diamond.getBounds().width;

    for (let h = 0; h < gameMap.height; h++) {
        for (let w = 0; w < gameMap.width; w++) {
            if(gameMap.diamondField({ x: w, y: h })){
                let clonedDiamondBitmap = diamond.clone();
                clonedDiamondBitmap.x = RECTANGLE_SIZE * (w + 0.25);
                clonedDiamondBitmap.y = RECTANGLE_SIZE * (h + 0.25);
                clonedDiamondBitmap.scaleX = scale;
                clonedDiamondBitmap.scaleY = scale;
                stage.addChild(clonedDiamondBitmap);
                diamondBitmaps.push({x: w, y: h, bitmap: clonedDiamondBitmap});
            }
        }
    }

    stage.update();
};
