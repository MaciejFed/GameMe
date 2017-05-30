const RECTANGLE_SIZE = window.innerWidth / 20;
const ONE_STEP_TIME = 1000;

export function animateRobot(stage, robotBitmap, arrowBitmap, diamonds, road){
    createjs.Ticker.setFPS(60);
    createjs.Ticker.addEventListener("tick", stage);
    const robotTween = createjs.Tween.get(robotBitmap);
    const arrowTween = createjs.Tween.get(arrowBitmap);
    const robotPositions = toMapPositions({ x: robotBitmap.x, y: robotBitmap.y, rotation: road[0].rotation }, road);
    const arrowPositions = toMapPositions({ x: arrowBitmap.x, y: arrowBitmap.y, rotation: 1 }, road);
    let animationTimeout = 0;
    for (let i = 1; i <= road.length; i++) {
        if(road[i-1].collectedDiamonds.length === 0){
            robotTween.to({ x: robotPositions[i].x, y: robotPositions[i].y }, ONE_STEP_TIME, createjs.Ease.getPowInOut(4));
            arrowTween.to({ x: arrowPositions[i].x, y: arrowPositions[i].y, rotation: (arrowPositions[i].rotation - 1) * 90 }, ONE_STEP_TIME, createjs.Ease.getPowInOut(4));
        }
        hideCollectedDiamonds(stage, diamonds, road[i-1].collectedDiamonds, animationTimeout);
        animationTimeout += ONE_STEP_TIME - road[i-1].collectedDiamonds.length * ONE_STEP_TIME;
    }
    return animationTimeout;
}

const toMapPositions = (startPosition, road) => {
    let positions = [startPosition];
    for(let i = 1; i <= road.length; i++){
        positions.push({
            x: positions[i - 1].x + RECTANGLE_SIZE * road[i - 1].dX,
            y: positions[i - 1].y + RECTANGLE_SIZE * road[i - 1].dY,
            rotation : (positions[i - 1].rotation + road[i - 1].rotation) % 4
        })
    }

    return positions;
};

const hideCollectedDiamonds = (stage, diamonds, collectedDiamonds, delay) => {
    setTimeout(() => {
        for(let i = 0; i < collectedDiamonds.length; i++){
            const diamond = diamonds.find((d) => d.x === collectedDiamonds[i].x && d.y === collectedDiamonds[i].y);
            const diamondBitmap = diamond.bitmap;
            const diamondTween  = createjs.Tween.get(diamondBitmap);

            diamondTween.to({
                    x: diamondBitmap.x,
                    y: diamondBitmap.y - RECTANGLE_SIZE,
                    alpha: 0},
                ONE_STEP_TIME * 1.5,
                createjs.Ease.getPowInOut(4));
        }
    }, delay - ONE_STEP_TIME)

};

