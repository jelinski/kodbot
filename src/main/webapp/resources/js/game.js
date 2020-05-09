var options = CodeMirror.defaults;
options.mode = "kodbot";
options.theme = "monokai";
options.lineNumbers = true;
options.autofocus = true;
options.value = "main{\n\t\n}";
var myCodeMirror = CodeMirror($("#codemirror-div").get(0), options);
myCodeMirror.setSize("100%", "100%");

myCodeMirror.on('change', countCommands);

function countCommands() {
    var bracketMatch = myCodeMirror.getValue().match(/{|}/g);
    var semicolonMatch = myCodeMirror.getValue().match(/;/g);
    var bracketsCounter = bracketMatch == null ? 0 : bracketMatch.length;
    var semicolonCounter = semicolonMatch == null ? 0 : semicolonMatch.length;
    var commandsCount = Math.floor(bracketsCounter / 2) + semicolonCounter;
    $('#command-counter').text(commandsCount);
}

window.onresize = function () {
    var mainDiv = $('#main-div');
    var marginTop = (window.innerHeight - mainDiv.height()) / 2.0;
    if (marginTop < 0)
        marginTop = 0;
    mainDiv.css('margin-top', marginTop);
};

var instructionHelpDom;
$(function () {
    window.onresize();
    hideNextStepButton();

    var instructionHelp = $('#instruction-help-overlay')[0];
    instructionHelpDom = new createjs.DOMElement(instructionHelp);
    c.stage.addChild(instructionHelpDom);
    $('#instruction-help-up').hide();

    if (!supports_html5_storage()) {
        slideDownHelp();
    }
    else {
        if (typeof localStorage['showHelp'] == 'undefined') {
            slideDownHelp();
        }
        else if (localStorage['showHelp'] == 'true') {
            slideDownHelp();
        }
    }

});

function slideDownHelp() {
    setTimeout(function () {
        $('#instruction-help-down').click();
    }, 2000);
}

$('input, button').focus(function () {
    this.blur();
});

var isPlaying = false;
$("#play-button").click(function () {
    if (!isPlaying) {
        $(this).text("Cancel");
        $(this).removeClass("btn-success");
        $(this).addClass("btn-danger");
        $.ajax({
            type: "POST",
            url: resolveUrl,
            data: {
                'code': myCodeMirror.getValue(),
                'mapKey': $('#mapKey').val()
            },
            success: function (data) {
                processResponse(data)
            },
            dataType: 'json'
        });
    } else {
        $(this).text("Play");
        $(this).removeClass("btn-danger");
        $(this).addClass("btn-success");
        resetGameState();
        $("#error-message-container").css('visibility', 'hidden');
        $('#score-container').css('visibility', 'hidden');
        hideNextStepButton();
    }
    isPlaying = !isPlaying;
});


$('#instruction-help-down').click(function () {
    createjs.Tween.get(instructionHelpDom).to({y: 285}, 1000, createjs.Ease.circInOut).call(function () {
        $('#instruction-help-down').hide();
        $('#instruction-help-up').show();
        if (supports_html5_storage()) {
            localStorage['showHelp'] = 'true';
        }
    });
});

$('#instruction-help-up').click(function () {
    createjs.Tween.get(instructionHelpDom).to({y: 0}, 1000, createjs.Ease.circInOut).call(function () {
        $('#instruction-help-up').hide();
        $('#instruction-help-down').show();
        if (supports_html5_storage()) {
            localStorage['showHelp'] = 'false';
        }
    });
});

var isGameLoaded = false;

function gameLoaded() {
    $('#game-overlay').css('visibility', 'visible');
    isGameLoaded = true;
    $('#game-speed-modes-div').css('visibility', 'visible');
}

function displayError(errorMessage) {
    $("#error-message").text(errorMessage);
    $("#error-message-container").css('visibility', 'visible');
}

function displayWin(gameResults) {
    $('#score-container').css('visibility', 'visible');
    $('#score-old').show();
    displayScore(gameResults, 'move', 'moveCount');
    displayScore(gameResults, 'jump', 'jumpCount');
    displayScore(gameResults, 'left', 'turnLeftCount');
    displayScore(gameResults, 'right', 'turnRightCount');
    displayScore(gameResults, 'assign', 'assignCount');
    displayScore(gameResults, 'assign-with-addition', 'assignWithAdditionCount');
    displayScore(gameResults, 'assign-with-subtraction', 'assignWithSubtractionCount');
    displayScore(gameResults, 'increment', 'incrementCount');
    displayScore(gameResults, 'decrement', 'decrementCount');
    displayScore(gameResults, 'repeat', 'repeatCount');
    displayScore(gameResults, 'function-definitions', 'definedFunctionCount');
    displayScore(gameResults, 'function-calls', 'calledFunctionCount');

    var newBattery = gameResults.newMapUserScore.batteryLevel * 1;
    var newCommand = gameResults.newMapUserScore.commandCounter * 1;

    $('#score-command-new').text(newCommand);
    $('#score-battery-new').text(newBattery);

    if (gameResults.nextMapKey) {
        $('#next-map-button').show();
        $('#next-map-button').attr('href', gameUrl + gameResults.nextMapKey);
    }
    else {
        $('#next-map-button').hide();
    }
}

function displayScore(gameResults, domString, resultString) {
    var newOne = $('#stat-' + domString + '-new');
    var newScore = gameResults.newStatistics[resultString] * 1;
    newOne.text(newScore);
}

$('#game-speed-fast-button').click(function () {
    changeGameSpeed(GameSpeedModes.FAST);
    clearGameSpeedSelection();
    $('#game-speed-fast-button').addClass('btn-primary');
});

$('#game-speed-slow-button').click(function () {
    changeGameSpeed(GameSpeedModes.SLOW);
    clearGameSpeedSelection();
    $('#game-speed-slow-button').addClass('btn-primary');
});

$('#game-speed-step-button').click(function () {
    changeGameSpeed(GameSpeedModes.STEP);
    clearGameSpeedSelection();
    $('#game-speed-step-button').addClass('btn-primary');
});

function clearGameSpeedSelection() {
    $('#game-speed-fast-button').removeClass('btn-primary');
    $('#game-speed-slow-button').removeClass('btn-primary');
    $('#game-speed-step-button').removeClass('btn-primary');
}

$('#game-next-step-button').click(function () {
    stepPause = false;
    consumeActionStack();
});

function hideNextStepButton() {
    $('#game-next-step-button').hide();
}

function showNextStepButton() {
    $('#game-next-step-button').show();
}

function showLeftSliderNavigator() {
    $('#map-slide-previous-button').show();
}

function hideLeftSliderNavigator() {
    $('#map-slide-previous-button').hide();
}

function showRightSliderNavigator() {
    $('#map-slide-next-button').show();
}

function hideRightSliderNavigator() {
    $('#map-slide-next-button').hide();
}

function showCloseSliderNavigator() {
    $('#map-slide-close-button').show();
}

function hideCloseSliderNavigator() {
    $('#map-slide-close-button').hide();
}

$('#map-slide-next-button').click(function () {
    nextMapSlide();
});

$('#map-slide-previous-button').click(function () {
    previousMapSlide();
});
$('#map-slide-close-button').click(function () {
    stopMapSlideShow();
});

function showSlidesControls() {
    $('#map-slide-left-container').css('visibility', 'visible');
    $('#map-slide-right-container').css('visibility', 'visible');
}

var isPlaySound = true;
$('#sound-toggle-button').click(function () {
    isPlaySound = !isPlaySound;
    if (isPlaySound) {
        $(this).find('i').addClass('fa-volume-up');
        $(this).find('i').removeClass('fa-volume-off');
        $(this).removeClass('btn-warning');
        $(this).addClass('btn-primary');
        enableSound();
    }
    else {
        $(this).find('i').removeClass('fa-volume-up');
        $(this).find('i').addClass('fa-volume-off');
        $(this).addClass('btn-warning');
        $(this).removeClass('btn-primary');
        disableSound();
    }
});


//################################### COMMON ###############################################
var imagesIds = {
    botJumpSprite: 'botJumpSprite',
    botMoveSprite: 'botMoveSprite',
    tile: 'tile',
    boxLight: 'boxLight',
    boxHeavy: 'boxHeavy',
    boxSpiked: 'boxSpiked',
    batteryLow: 'batteryLow',
    batteryMedium: 'batteryMedium',
    batteryHigh: 'batteryHigh',
    batteryCharge: 'batteryCharge',
    batteryMask: 'batteryMask'
};

var soundsIds = {
    jump: 'jump',
    turn: 'turn',
    moveFast: 'move-fast',
    moveSlow: 'move-slow',
    win: 'win',
    pickup: 'pickup',
    noMorePower: 'no-more-power'
};

var directions = {
    BOTTOM_RIGHT: 0,
    BOTTOM_LEFT: 1,
    TOP_LEFT: 2,
    TOP_RIGHT: 3
};

var gameOver = false;
var mapSlides = [];

function loadMapData() {
    $.ajax({
        type: "POST",
        url: fetchUrl,
        data: {
            "mapKey": $('#mapKey').val()
        },
        success: function (data) {
            c.mapData = data;
            loadResources(data.mapSlides);
        },
        dataType: 'json'
    });
}

function loadResources(slides) {
    var resources = v.resourceImages.concat(v.resourceSounds);
    if (slides != null) {
        for (var i = 0; i < slides.length; i++) {
            mapSlides.push(new ResourceItem("mapSlide" + i, slides[i]));
        }
        resources = resources.concat(mapSlides);
    }
    queue.loadManifest(resources);
}

var currentMapSlide = 0;

function prepareMapSlides() {
    v.mapSlidesContainer = new createjs.Container();
    for (var i = 0; i < mapSlides.length; i++) {
        var slideBitmap = new createjs.Bitmap(queue.getResult(mapSlides[i].id));
        mapSlides[i].bitmap = slideBitmap;
        slideBitmap.visible = false;
        v.mapSlidesContainer.addChild(slideBitmap);
    }
    v.mapSlidesContainer.name = "mapSlidesContainer";
    c.stage.addChild(v.mapSlidesContainer);

    if (mapSlides.length > 0) {
        showSlidesControls();
        setMapSlide(currentMapSlide);
    } else {
        stopMapSlideShow();
    }
}

function stopMapSlideShow() {
    if (mapSlides.length > 0) {
        mapSlides[currentMapSlide].bitmap.visible = false;
    }
    hideLeftSliderNavigator();
    hideRightSliderNavigator();
    hideCloseSliderNavigator();
    var mapSlidesContainer = c.stage.getChildByName("mapSlidesContainer");
    mapSlidesContainer.removeAllChildren();
    c.stage.removeChild(mapSlidesContainer);
    setup();
}

function nextMapSlide() {
    var nextMapSlideNumber = currentMapSlide == mapSlides.length ? mapSlides.length : currentMapSlide + 1;
    setMapSlide(nextMapSlideNumber);
}

function previousMapSlide() {
    var previousMapSlideNumber = currentMapSlide == 0 ? 0 : currentMapSlide - 1;
    setMapSlide(previousMapSlideNumber);
}

function setMapSlide(mapSlideNumber) {
    if (mapSlideNumber == 0) {
        hideLeftSliderNavigator();
    } else {
        showLeftSliderNavigator();
    }

    if (mapSlideNumber == mapSlides.length - 1) {
        hideRightSliderNavigator();
        showCloseSliderNavigator();
    } else {
        showRightSliderNavigator();
        hideCloseSliderNavigator();
    }

    mapSlides[currentMapSlide].bitmap.visible = false;
    mapSlides[mapSlideNumber].bitmap.visible = true;
    currentMapSlide = mapSlideNumber;
}

function loadingFinishedCallback() {
    if (c.mapData == null || !v.imagesLoaded)
        return;
    prepareMapSlides();
}

$(function () {
    var canvas = document.getElementsByTagName('canvas')[0];
    canvas.width = 600;
    canvas.height = 600;
    drawProgressBar();
    loadMapData();
});

function setup() {
    v.gameContainer = new createjs.Container();
    setElementTypesImages();
    drawBatteryBar();
    drawTiles();
    var spriteData = {
        framerate: 10,
        images: [v.images[imagesIds.botMoveSprite], v.images[imagesIds.botJumpSprite]],
        frames: {
            width: 72,
            height: 72
        },
        animations: {
            BL: [0, 13],
            BR: [14, 27],
            TL: [28, 41],
            TR: [42, 55],
            JBL: [56, 69],
            JBR: [70, 83],
            JTL: [84, 97],
            JTR: [98, 111]
        }
    };
    var spriteSheet = new createjs.SpriteSheet(spriteData);
    v.botSprite = new createjs.Sprite(spriteSheet, "BR");
    v.gameContainer.addChild(v.botSprite);
    resetGameState();
    changeGameSpeed(GameSpeedModes.SLOW);
    c.stage.addChild(v.gameContainer);
    gameLoaded();
}

function resetGameState() {
    gameOver = false;
    createMap();
    createjs.Tween.removeAllTweens();
    m.bot.row = c.mapData.botPositionRow;
    m.bot.col = c.mapData.botPositionCol;
    m.bot.level = m.map[m.bot.row][m.bot.col].length;
    m.bot.direction = c.mapData.botRotation;
    m.batteryLevel = c.mapData.batteryLevel;
    updateBatteryLevel();
    redrawBoxes();
}

// ################################### END OF COMMON ###############################################

// ################################### CONTROLLER ###############################################
var c = {
    mapData: null,
    inverse: null,
    stage: new createjs.Stage("gameCanvas")
};

createjs.Ticker.setFPS(50);
createjs.Ticker.addEventListener("tick", function (event) {
    c.stage.update(event);
});

function setupInverse() {
    var direction = m.bot.direction;
    if (direction == directions.BOTTOM_RIGHT || direction == directions.TOP_LEFT) {
        c.inverse = false;
    } else if (direction == directions.BOTTOM_LEFT || direction == directions.TOP_RIGHT) {
        c.inverse = true;
    }
}

function redrawBoxes() {
    v.gameContainer.removeAllChildren();
    v.gameContainer.addChild(v.botSprite);
    // uwzglednia kolejnosc rysowania bloczkow na planszy - problem z koeljnoscia wyswietlania
    setupInverse();
    drawBoxes();
    drawBot();
}

function createMap() {
    var typeId;
    var box;

    m.map = new Array(10);
    for (var row = 0; row < 10; row++) {
        m.map[row] = new Array(10);
        for (var col = 0; col < 10; col++) {
            m.map[row][col] = [];
            for (var k = 0; k < c.mapData.data.length; k++) {
                if (c.mapData.data[k]['row'] === row && c.mapData.data[k]['col'] === col) {
                    typeId = c.mapData.data[k]['type'];
                    box = new Element(row, col, getElementTypeById(typeId));
                    m.map[row][col].push(box);
                }
            }
        }
    }
}

function changeBotPositionAtStage(row, col) {

    var foundReverse = true;
    if (m.map[row][col].length == 0) { // Jesli na niczym nie stoi

        // Szukanie dalszych lub blizszych
        var fRow = row, fCol = col, cRow = row, cCol = col; // far/close

        do {
            if (!c.inverse) {
                fCol--;
                if (fCol < 0) {
                    fCol = 9;
                    fRow++;
                    if (fRow == 10) {
                        foundReverse = false;
                        break;
                    }
                }
            } else {
                fRow++;
                if (fRow > 9) {
                    fRow = 0;
                    fCol--;
                    if (fCol < 0) {
                        foundReverse = false;
                        break;
                    }
                }
            }
        } while (m.map[fRow][fCol].length == 0);

        if (!foundReverse) { // znaczy, ze nie znalazl poprzednika
            do {
                if (!c.inverse) {
                    cCol++;
                    if (cCol > 9) {
                        cCol = 0;
                        cRow--;
                        if (cRow == -1) {
                            return; // pusta plansza bez bloczkow
                        }
                    }
                } else {
                    cRow--;
                    if (cRow < 0) {
                        cRow = 9;
                        cCol++;
                        if (cCol > 9) {
                            return; // plansza pusta
                        }
                    }
                }
            } while (m.map[cRow][cCol].length == 0);
            col = cCol;
            row = cRow;
        } else {
            col = fCol;
            row = fRow;
        }
    }
    var levelAtPosition = m.map[row][col].length;
    v.gameContainer.removeChild(v.botSprite);
    var poz = null;
    if (foundReverse) {
        poz = levelAtPosition - 1;
    } else {
        poz = 0;
    }
    var indx = v.gameContainer.getChildIndex(m.map[row][col][poz].element);
    // var botIndx = v.gameContainer.getChildIndex(v.botSprite);
    // v.gameContainer.swapChildrenAt(botIndx, foundReverse? indx+1:indx);
    v.gameContainer.addChildAt(v.botSprite, foundReverse ? indx + 1 : indx);
}

function getHeightAtPos(row, col) {
    var height = 0;
    for (var elem in m.map[row][col]) {
        if (checkIfStandableElement(m.map[row][col][elem].elementType)) {
            height += m.map[row][col][elem].element.getBounds().height - v.tileHeight;
        } else {
            break;
        }
    }
    return height;
}

function getElementYOffset(row, col, element) {
    var yOffset = 0;
    for (var elem in m.map[row][col]) {
        if (m.map[row][col][elem] !== element) {
            yOffset += m.map[row][col][elem].element.getBounds().height - v.tileHeight;
        } else {
            break;
        }
    }
    return yOffset;
}

function getTopStandableElementLevel(row, col) {
    var topStandableElementLevel = 0;
    for (var elem in m.map[row][col]) {
        if (checkIfStandableElement(m.map[row][col][elem].elementType)) {
            topStandableElementLevel++;
        } else {
            break;
        }
    }
    return topStandableElementLevel;
}

function getPositionStackLevel(row, col) {
    return m.map[row][col].length;
}

function setElementTypesImages() {
    elementTypes.boxHeavy.img = v.images[imagesIds.boxHeavy];
    elementTypes.boxLight.img = v.images[imagesIds.boxLight];
    elementTypes.boxSpiked.img = v.images[imagesIds.boxSpiked];
    elementTypes.batteryLow.img = v.images[imagesIds.batteryLow];
    elementTypes.batteryMedium.img = v.images[imagesIds.batteryMedium];
    elementTypes.batteryHigh.img = v.images[imagesIds.batteryHigh];
}

function checkIfPickupableElement(elementType) {
    return pickupableElements.indexOf(elementType) >= 0;
}

function checkIfStandableElement(elementType) {
    return standableElements.indexOf(elementType) >= 0;
}

function getElementTypeById(id) {
    for (var type in elementTypes) {
        if (elementTypes[type].id == id) {
            return elementTypes[type];
        }
    }
}

function ElementType(id, offsetX, offsetY) {
    this.id = id;
    this.img = null;
    offsetX = typeof offsetX == 'undefined' ? 0 : offsetX;
    offsetY = typeof offsetY == 'undefined' ? 0 : offsetY;
    this.offsetX = offsetX;
    this.offsetY = offsetY;
}

function BatteryElement(id, batteryPoints, offsetX, offsetY) {
    ElementType.call(this, id, offsetX, offsetY);
    this.batteryPoints = batteryPoints;
}

BatteryElement.prototype = Object.create(ElementType.prototype);
BatteryElement.prototype.constructor = BatteryElement;

var elementTypes = {
    boxHeavy: new ElementType(1),
    boxLight: new ElementType(2),
    boxSpiked: new ElementType(3),
    batteryLow: new BatteryElement(100, 25, 0, -7),
    batteryMedium: new BatteryElement(101, 50, 0, -7),
    batteryHigh: new BatteryElement(102, 75, 0, -7)
};

var standableElements = [elementTypes.boxHeavy, elementTypes.boxLight];

var pickupableElements = [elementTypes.batteryLow, elementTypes.batteryMedium, elementTypes.batteryHigh];
// ################################### END OF CONTROLLER ###############################################

// ################################### VIEW ###############################################
var progressBarOptions = {
    outerWidth: 580,
    outerHeight: 20,
    outerX: 10,
    outerY: 290,
    innerWidth: 570,
    innerHeight: 10,
    innerX: 15,
    innerY: 295
};

function updateProgressBar(progress) {
    var innerBar = c.stage.getChildByName("splash").getChildByName("inner");
    innerBar.graphics.clear();
    var width = progressBarOptions.innerWidth * progress;
    innerBar.graphics.beginFill("#ffffff").rect(0, 0, width, 10).endFill();
}

function drawProgressBar() {
    var splashContainer = new createjs.Container();
    var outerRectangle = new createjs.Shape();
    outerRectangle.graphics.beginStroke("#ffffff").setStrokeStyle(1).rect(0, 0, progressBarOptions.outerWidth, progressBarOptions.outerHeight).endStroke();
    outerRectangle.x = progressBarOptions.outerX + 0.5;
    outerRectangle.y = progressBarOptions.outerY + 0.5;
    var innerRectangle = new createjs.Shape();
    innerRectangle.graphics.beginFill("#ffffff").rect(0, 0, 1, 10).endFill();
    innerRectangle.x = progressBarOptions.innerX;
    innerRectangle.y = progressBarOptions.innerY;
    innerRectangle.name = "inner";
    splashContainer.addChild(outerRectangle);
    splashContainer.addChild(innerRectangle);
    splashContainer.name = "splash";
    c.stage.addChild(splashContainer);
}

var v = {
    boardXOffset: 39,
    boardYOffset: 350,
    tileWidth: 54,
    tileHeight: 27,
    tileContainer: null,
    guiContainer: null,
    gameContainer: null,
    imagesLoaded: false,
    resourceSounds: [new ResourceItem(soundsIds.jump, '/resources/sounds/jump.mp3'), new ResourceItem(soundsIds.moveSlow, '/resources/sounds/move_slow.mp3'), new ResourceItem(soundsIds.moveFast, '/resources/sounds/move_fast.mp3'),
        new ResourceItem(soundsIds.turn, '/resources/sounds/turn.mp3'), new ResourceItem(soundsIds.win, '/resources/sounds/win.mp3'), new ResourceItem(soundsIds.pickup, '/resources/sounds/pickup.mp3'),
        new ResourceItem(soundsIds.noMorePower, '/resources/sounds/no_more_power.mp3')],
    resourceImages: [new ResourceItem(imagesIds.botMoveSprite, '/resources/images/game/bot_move_sprite.png'), new ResourceItem(imagesIds.botJumpSprite, '/resources/images/game/bot_jump_sprite.png'),
        new ResourceItem(imagesIds.tile, '/resources/images/game/tile.png'), new ResourceItem(imagesIds.boxLight, '/resources/images/game/boxLight.png'), new ResourceItem(imagesIds.boxHeavy, '/resources/images/game/boxHeavy.png'),
        new ResourceItem(imagesIds.boxSpiked, '/resources/images/game/boxSpiked.png'), new ResourceItem(imagesIds.batteryLow, '/resources/images/game/batteryLow.png'),
        new ResourceItem(imagesIds.batteryMedium, '/resources/images/game/batteryMedium.png'), new ResourceItem(imagesIds.batteryHigh, '/resources/images/game/batteryHigh.png'),
        new ResourceItem(imagesIds.batteryCharge, '/resources/images/game/batteryCharge.png'), new ResourceItem(imagesIds.batteryMask, '/resources/images/game/batteryMask.png')],
    images: {},
    botSprite: null
};

var queue = new createjs.LoadQueue();
createjs.Sound.alternateExtensions = ["ogg"];
queue.installPlugin(createjs.Sound);
queue.on("fileload", function (event) {
    updateProgressBar(queue._numItemsLoaded / queue._numItems);
}, this);
queue.on("complete", function () {
    var id;
    for (var res in v.resourceImages) {
        id = v.resourceImages[res].id;
        v.images[id] = queue.getResult(id);
    }
    v.imagesLoaded = true;
    var splash = c.stage.getChildByName("splash");
    splash.removeAllChildren();
    c.stage.removeChild(splash);
    loadingFinishedCallback();
}, this);

function ResourceItem(id, src) {
    this.id = id;
    this.src = src;
}

function translateToXY(row, col) {
    var x = v.boardXOffset + col * (v.tileWidth - 2) / 2 + row * (v.tileWidth - 2) / 2;
    var y = v.boardYOffset + col * (v.tileHeight - 1) / 2 - row * (v.tileHeight - 1) / 2;

    return {
        x: x,
        y: y
    };
}

function drawTiles() {
    var tile;
    var cords;
    v.tileContainer = new createjs.Container();
    for (var row = 0; row < 10; row++) {
        for (var col = 0; col < 10; col++) {
            tile = new createjs.Bitmap(v.images[imagesIds.tile]);
            cords = translateToXY(row, col);
            tile.x = cords.x;
            tile.y = cords.y;
            v.tileContainer.addChild(tile);
        }
    }
    c.stage.addChild(v.tileContainer);
}

var AnimationSpeedSlow = {
    JUMP: 840,
    MOVE: 840,
    TURN: 600
};

var AnimationSpeedFast = {
    JUMP: 280,
    MOVE: 280,
    TURN: 200
};

var AnimationSpeed = AnimationSpeedSlow;

var GameSpeedModes = {
    SLOW: 0,
    FAST: 1,
    STEP: 2
};

var gameSpeed = GameSpeedModes.SLOW;

function changeGameSpeed(gameSpeedMode) {
    gameSpeed = gameSpeedMode;
    if (gameSpeedMode == GameSpeedModes.SLOW) {
        AnimationSpeed = AnimationSpeedSlow;
    } else if (gameSpeedMode == GameSpeedModes.FAST) {
        AnimationSpeed = AnimationSpeedFast;
    } else if (gameSpeedMode == GameSpeedModes.STEP) {
        AnimationSpeed = AnimationSpeedFast;
    }
    v.botSprite.spriteSheet.framerate = 1 / (AnimationSpeed.MOVE / 14) * 1000;
}

function animateBotJump(callback, isJumpingUp) {
    var newPoz = calculateBotPosition();

    v.botSprite.gotoAndPlay("J" + v.botSprite.currentAnimation);
    playSound(soundsIds.jump);

    setTimeout(function () {
        changeBotPositionAtStage(m.bot.row, m.bot.col);
    }, AnimationSpeed.JUMP / 2);
    createjs.Tween.get(v.botSprite).to({
        x: newPoz.x,
        y: newPoz.y
    }, AnimationSpeed.JUMP, createjs.Ease.sineIn).call(function () {
        v.botSprite.gotoAndStop(v.botSprite.currentAnimation.substr(1));
        callback();
    });
}

function animateBotMove(callback) {

    var newPoz = calculateBotPosition();
    var delayedChangePos = false;

    if (m.bot.direction == directions.TOP_RIGHT || m.bot.direction == directions.TOP_LEFT) {
        delayedChangePos = true;
    }

    if (gameSpeed == GameSpeedModes.SLOW) {
        playSound(soundsIds.moveSlow);
    }
    else {
        playSound(soundsIds.moveFast);
    }
    v.botSprite.gotoAndPlay(v.botSprite.currentAnimation);
    if (!delayedChangePos) {
        changeBotPositionAtStage(m.bot.row, m.bot.col);
        createjs.Tween.get(v.botSprite).to({
            x: newPoz.x,
            y: newPoz.y
        }, AnimationSpeed.MOVE).call(function () {
            v.botSprite.gotoAndStop(v.botSprite.currentAnimation);
            callback();
        });
    } else {
        createjs.Tween.get(v.botSprite).to({
            x: newPoz.x,
            y: newPoz.y
        }, AnimationSpeed.MOVE).call(function () {
            changeBotPositionAtStage(m.bot.row, m.bot.col);
            v.botSprite.gotoAndStop(v.botSprite.currentAnimation);
            callback();
        });
    }
}

function animateTurn(callback) {
    setTimeout(function () {
        playSound(soundsIds.turn);
        drawBot();
        setTimeout(callback, AnimationSpeed.TURN / 2);
    }, AnimationSpeed.TURN / 2);
}

function drawBot() {
    var animation = null;
    var direction = m.bot.direction;
    if (direction == directions.BOTTOM_RIGHT) {
        animation = "BR";
    } else if (direction == directions.BOTTOM_LEFT) {
        animation = "BL";
    } else if (direction == directions.TOP_LEFT) {
        animation = "TL";
    } else if (direction == directions.TOP_RIGHT) {
        animation = "TR";
    }

    changeBotPositionAtStage(m.bot.row, m.bot.col);
    var pos = calculateBotPosition();
    v.botSprite.x = pos.x;
    v.botSprite.y = pos.y;
    v.botSprite.gotoAndStop(animation);
}

function calculateBotPosition() {
    var cords = translateToXY(m.bot.row, m.bot.col);
    var x = cords.x - (Math.abs(v.tileWidth - v.botSprite.getBounds().width)) / 2;
    var y = cords.y - Math.abs(v.botSprite.getBounds().height - v.tileHeight) - getHeightAtPos(m.bot.row, m.bot.col);
    return {
        x: x,
        y: y
    }
}

function drawBoxes() {
    if (!c.inverse) {
        for (var row = 9; row >= 0; row--) {
            for (var col = 0; col <= 9; col++) {
                for (var k = 0; k < m.map[row][col].length; k++) {
                    var element = drawElement(m.map[row][col][k]);
                    m.map[row][col][k].element = element;
                }
            }
        }
    } else {
        for (var col = 0; col <= 9; col++) {
            for (var row = 9; row >= 0; row--) {
                for (var k = 0; k < m.map[row][col].length; k++) {
                    var element = drawElement(m.map[row][col][k]);
                    m.map[row][col][k].element = element;
                }
            }
        }
    }
}

function drawElement(element) {
    var boxBmp = new createjs.Bitmap(element.elementType.img);
    var cords = translateToXY(element.row, element.col);
    boxBmp.x = cords.x + (v.tileWidth - boxBmp.getBounds().width) / 2 + element.elementType.offsetX;
    boxBmp.y = cords.y - (boxBmp.getBounds().height - v.tileHeight) - getElementYOffset(element.row, element.col, element) + element.elementType.offsetY;
    v.gameContainer.addChild(boxBmp);
    return boxBmp;
}

function drawBatteryBar() {
    v.guiContainer = new createjs.Container();
    var mask = new createjs.Bitmap(v.images[imagesIds.batteryMask]);
    var amf = new createjs.AlphaMaskFilter(mask.image);

    var batteryLevel = new createjs.Shape();
    batteryLevel.graphics.beginFill("rgba(0,255,0,255)").drawRect(4, 4, 96, 44);
    batteryLevel.filters = [amf];
    batteryLevel.alpha = 0.8;
    batteryLevel.cache(0, 0, mask.image.width, mask.image.height);
    batteryLevel.name = "batteryLevel";
    batteryLevel.x = batteryLevel.y = 10;

    var battery = new createjs.Bitmap(v.images[imagesIds.batteryCharge]);
    battery.filters = [amf];
    battery.x = battery.y = batteryLevel.x;

    var batteryLabel = new createjs.Text("" + m.batteryLevel, '24px "Press Start 2P"', "#ffffff");
    batteryLabel.name = "batteryLabel";
    batteryLabel.textAlign = 'center';
    batteryLabel.x = 65;
    batteryLabel.y = 25;

    v.guiContainer.addChild(battery);
    v.guiContainer.addChild(batteryLevel);
    v.guiContainer.addChild(batteryLabel);
    c.stage.addChild(v.guiContainer);
}

function updateBatteryLevel() {
    var R = (100 - m.batteryLevel) * 255 / 100;
    if (R < 10)
        R = "0" + R;
    var G = m.batteryLevel * 255 / 100;
    if (G < 10)
        G = "0" + G;
    R = Math.floor(R);
    G = Math.floor(G);

    var width = m.batteryLevel * 96 / 100;

    var level = v.guiContainer.getChildByName('batteryLevel');
    level.graphics.clear();
    level.graphics.beginFill("rgba(" + R + "," + G + ",0,255)").drawRect(4, 4, width, 44);
    level.updateCache();
    v.guiContainer.getChildByName('batteryLabel').text = "" + m.batteryLevel;
}

// ################################### END OF VIEW ###############################################

function isPositionValid(row, col) {
    return !(row < 0 || row > 9 || col < 0 || col > 9);
}

// ################################### MODEL ###############################################

var batteryCosts = {
    MOVE: 5,
    JUMP: 10,
    TURN: 5
};

var m = {

    bot: {
        row: null,
        oldRow: null,
        col: null,
        oldCol: null,
        level: null,
        direction: null
    },

    map: null,
    batteryLevel: null,

    moveBot: function () {
        var newCol = m.bot.col;
        var newRow = m.bot.row;
        var direction = m.bot.direction;

        if (direction == directions.BOTTOM_RIGHT) {
            newCol++;
        } else if (direction == directions.BOTTOM_LEFT) {
            newRow--;
        } else if (direction == directions.TOP_LEFT) {
            newCol--;
        } else if (direction == directions.TOP_RIGHT) {
            newRow++;
        }

        if (m.batteryLevel >= batteryCosts.MOVE) {
            m.addToBatteryLevel(-batteryCosts.MOVE);
            if (isPositionValid(newRow, newCol)) {
                var topStandableElementLevel = getTopStandableElementLevel(newRow, newCol);
                if (m.bot.level == topStandableElementLevel) {
                    var level = getPositionStackLevel(newRow, newCol);
                    if (level == topStandableElementLevel || checkIfPickupableElement(m.map[newRow][newCol][level - 1].elementType)) {
                        m.bot.oldRow = m.bot.row;
                        m.bot.oldCol = m.bot.col;
                        m.bot.row = newRow;
                        m.bot.col = newCol;
                        m.checkAndCollect(AnimationSpeed.MOVE);
                    }
                }
            }
            return true;
        }
        return false;
    },

    turnLeft: function () {
        if (m.batteryLevel >= batteryCosts.TURN) {
            m.bot.direction = (m.bot.direction + 3) % 4;
            m.addToBatteryLevel(-batteryCosts.TURN);
            return true;
        }
        return false;
    },

    turnRight: function () {
        if (m.batteryLevel >= batteryCosts.TURN) {
            m.bot.direction = (m.bot.direction + 1) % 4;
            m.addToBatteryLevel(-batteryCosts.TURN);
            return true;
        }
        return false;
    },

    jump: function () {
        var newCol = m.bot.col;
        var newRow = m.bot.row;

        var direction = m.bot.direction;
        if (m.bot.direction == directions.BOTTOM_RIGHT) {
            newCol++;
        } else if (direction == directions.BOTTOM_LEFT) {
            newRow--;
        } else if (direction == directions.TOP_LEFT) {
            newCol--;
        } else if (direction == directions.TOP_RIGHT) {
            newRow++;
        }

        if (m.batteryLevel >= batteryCosts.JUMP) {
            m.addToBatteryLevel(-batteryCosts.JUMP);
            if (isPositionValid(newRow, newCol)) {
                var topStandableElementLevel = getTopStandableElementLevel(newRow, newCol);
                if (Math.abs(m.bot.level - topStandableElementLevel) == 1) {
                    var level = getPositionStackLevel(newRow, newCol);
                    if (level == topStandableElementLevel || checkIfPickupableElement(m.map[newRow][newCol][level - 1].elementType)) {
                        m.bot.oldRow = m.bot.row;
                        m.bot.oldCol = m.bot.col;
                        m.bot.row = newRow;
                        m.bot.col = newCol;
                        m.bot.level = topStandableElementLevel;
                        m.checkAndCollect(AnimationSpeed.JUMP);
                    }
                }
            }
            return true;
        }
        return false;
    },

    addToBatteryLevel: function (val) {
        m.batteryLevel += val;
        if (m.batteryLevel < 0) {
            m.batteryLevel = 0;
        } else if (m.batteryLevel > 100) {
            m.batteryLevel = 100;
        }
    },

    checkAndCollect: function (delay) {
        var row = m.bot.row;
        var col = m.bot.col;
        var length = m.map[row][col].length;
        if (length > 0) {
            var element = m.map[row][col][length - 1];
            if (checkIfPickupableElement(element.elementType)) {
                m.map[row][col].pop();
                setTimeout(function () {
                    v.gameContainer.removeChild(element.element);
                }, delay);
                if (element.elementType instanceof BatteryElement) {
                    m.addToBatteryLevel(element.elementType.batteryPoints);
                }
            }
        }
    }
};

function Element(row, col, elementType) {
    this.row = row;
    this.col = col;
    this.elementType = elementType;
    return this;
}

// ################################### END OF MODEL ###############################################

// ################################### API ###############################################

var gameWon = false;

var ServerResponseCode = {
    WIN: "WIN",
    OK: "OK",
    ERROR: "ERROR"
};

var actionStack = [];
var gameResults;

function processResponse(response) {
    gameWon = false;
    if (response.code === ServerResponseCode.ERROR) {
        displayError(response.message);
    } else if (response.code === ServerResponseCode.OK) {
        actionStack = response.actions.slice();
        actionStack.reverse();
        consumeActionStack();
    } else if (response.code === ServerResponseCode.WIN) {
        gameWon = true;
        actionStack = response.actions.slice();
        actionStack.reverse();
        consumeActionStack();
        gameResults = response;
    }
}

var stepPause = true;

function consumeActionStack() {
    if (!isPlaying)
        return;

    if (stepPause && gameSpeed == GameSpeedModes.STEP) {
        showNextStepButton();
        return;
    }

    hideNextStepButton();
    stepPause = true;

    if (actionStack.length > 0) {
        var action = actionStack.pop();
        if (action == 'MOVE') {
            BOT.move();
        } else if (action == 'JUMP') {
            BOT.jump();
        } else if (action == 'TURN_LEFT') {
            BOT.turnLeft();
        } else if (action == 'TURN_RIGHT') {
            BOT.turnRight();
        }
    } else if (gameWon) {
        playSound(soundsIds.win);
        displayWin(gameResults);
    }
}

var BOT = {
    move: function () {
        if (m.moveBot()) {
            updateBatteryLevel();
            animateBotMove(consumeActionStack);
        }
        else {
            playSound(soundsIds.noMorePower);
        }
    },

    turnLeft: function () {
        if (m.turnLeft()) {
            updateBatteryLevel();
            redrawBoxes();
            animateTurn(consumeActionStack);
        }
        else {
            playSound(soundsIds.noMorePower);
        }
    },

    turnRight: function () {
        if (m.turnRight()) {
            updateBatteryLevel();
            redrawBoxes();
            animateTurn(consumeActionStack);
        }
        else {
            playSound(soundsIds.noMorePower);
        }
    },

    jump: function () {
        var oldBotLevel = m.bot.level;
        if (m.jump()) {
            var newBotLevel = m.bot.level;
            updateBatteryLevel();
            animateBotJump(consumeActionStack, newBotLevel > oldBotLevel);
        }
        else {
            playSound(soundsIds.noMorePower);
        }
    }
};

function enableSound() {
    soundEnabled = true;
}

function disableSound() {
    soundEnabled = false;
    createjs.Sound.stop();
}

function playSound(soundName) {
    if (soundName == soundsIds.noMorePower) {
        if (!gameOver) {
            gameOver = true;
        }
        else {
            return;
        }
    }
    if (soundEnabled) {
        createjs.Sound.play(soundName);
    }
}

var soundEnabled = true;
// ################################### END OF API ###############################################
