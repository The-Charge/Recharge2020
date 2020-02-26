// Define UI elements
let ui = {
    timer: document.getElementById('timer'),
    robotState: document.getElementById('robot-state'),
    camera: {
        screen: document.getElementById('camera'),
        label: document.getElementById('camera-label'),
        status: document.getElementById('camera-status'),
    },
    gyro: {
        // turret: {
        //     val: 0,
        //     offset: 0,
        //     visualVal: 0,
        // },
        // container: document.getElementById('gyro'),
        turret: {
            marker: document.getElementById('turret-direction'),
            value: 0,
        }
    },
    climber: {
        slider: document.getElementById('test-element-climber'),
        arm: document.getElementById('climb-arm'),
        top: document.getElementById('climb-top'),
    },
    elevation: {
        slider: document.getElementById('test-element-elevation'),
        arm: document.getElementById('turret-elevation'),
        number: document.getElementById('elevation-number'),
    },
    vision: {
        text: document.getElementById('vision-target'),
        found: document.getElementById('vision-target-found'),
        moving: document.getElementById('vision-target-moving'),
        none: document.getElementById('vision-target-none'),
        leftArrow: document.getElementById('left-arrow'),
        rightArrow: document.getElementById('right-arrow'),
        degreeNumber: document.getElementById('vision-degrees'),
    },
    gearText: document.getElementById('gear'),
    indexerText: document.getElementById('indexer-text'),
    autoSelect: document.getElementById('auto-select'),
    ballCount: document.getElementById('ball-count'),
    testInput: document.getElementById('test-element'),
    panelText: document.getElementById('control-panel'),

    test: document.getElementById('test-element'),
};

// Update camera every second
setInterval(() => {
    ui.camera.screen.style.backgroundImage = 'url("https://upload.wikimedia.org/wikipedia/commons/thumb/9/93/BakedPotatoWithButter.jpg/1200px-BakedPotatoWithButter.jpg")';
}, 1000);

setInterval(() => {
    ui.gyro.turret.value += 1;
    ui.gyro.turret.marker.setAttribute('transform', 'rotate(' + ui.gyro.turret.value + ',0,30)');
}, 1)

ui.climber.slider.oninput = function() {
    NetworkTables.putValue('/SmartDashboard/Climber Elevation', parseInt(this.value));
}

ui.elevation.slider.oninput = function() {
    NetworkTables.putValue('/SmartDashboard/Turret Elevation', parseInt(this.value));
}

ui.testInput.oninput = function() {
    NetworkTables.putValue('/SmartDashboard/TurnDirection', parseInt(this.value));
}

// Update match timer
NetworkTables.addKeyListener('/robot/time', (key, value) => {
    ui.timer.textContent = value < 0 ? '0:00' : Math.floor(value / 60) + ':' + (value % 60 < 10 ? '0' : '') + value % 60;
});

// Error handler
addEventListener('error',(ev)=>{
    ipc.send('windowError',{mesg:ev.message,file:ev.filename,lineNumber:ev.lineno})
});

//Event Listeners:

NetworkTables.addKeyListener('/SmartDashboard/current_camera', (key, value) => {
    if(value == true) {
        ui.camera.screen.style.backgroundImage = 'url("https://upload.wikimedia.org/wikipedia/commons/thumb/9/93/BakedPotatoWithButter.jpg/1200px-BakedPotatoWithButter.jpg")';
    } else {
        ui.camera.screen.style.backgroundImage = 'url(https://image.shutterstock.com/image-photo/young-potato-isolated-on-white-260nw-630239534.jpg)';
    }
});

NetworkTables.addKeyListener('/SmartDashboard/turret_rotation', (key, value) => {
    ui.gyro.turret.marker.setAttribute('transform', 'rotate(' + value + ',0,30)');
});

NetworkTables.addKeyListener('/SmartDashboard/vision_error', (key, value) => {
    if(value == true) {
        ui.vision.leftArrow.classList.add('on');
        ui.vision.rightArrow.classList.add('on');
    } else {
        ui.vision.leftArrow.classList.add('off');
        ui.vision.rightArrow.classList.add('off');
    }
});

NetworkTables.addKeyListener('/SmartDashboard/Climber Elevation', (key, value) => {
    ui.climber.arm.style.y = String(150 - parseInt(value));
    ui.climber.top.style.cy = String(132 - parseInt(value));
});

NetworkTables.addKeyListener('/SmartDashboard/Turret Elevation', (key, value) => {
    ui.elevation.arm.setAttribute('transform', 'rotate(-' + value + ',-60,0)');
    ui.elevation.number.innerHTML = value + '°';
});

NetworkTables.addKeyListener('/SmartDashboard/Ball Count', (key, value) => {
    ui.ballCount.innerHTML = value;
    if(parseInt(value) == 5) {
        ui.ballCount.style.color = 'lime';
        ui.ballCount.style.fontWeight = 'bold';
    }
    else {
        ui.ballCount.style.color = 'white';
        ui.ballCount.style.fontWeight = 'normal';
    }
});

NetworkTables.addKeyListener('/SmartDashboard/VisionFound', (key, value) => {
    //0 is none, 1 is moving, 2 is found
    if(parseInt(value) == 0) {
        ui.vision.text.innerHTML = 'NONE';
        ui.vision.text.style.color = 'red';
        ui.vision.moving.style.opacity = 0;
        ui.vision.found.style.opacity = 0;
    } else if(parseInt(value) == 1) {
        ui.vision.text.innerHTML = 'MOVING';
        ui.vision.text.style.color = 'yellow';
        ui.vision.moving.style.opacity = 1;
        ui.vision.found.style.opacity = 0;
    } else if(parseInt(value) == 2) {
        ui.vision.text.innerHTML = 'FOUND';
        ui.vision.text.style.color = 'lime';
        ui.vision.moving.style.opacity = 1;
        ui.vision.found.style.opacity = 1;
    }
});

NetworkTables.addKeyListener('/SmartDashboard/Gear', (key, value) => {
    if(value == true) {
        ui.gearText.innerHTML = 'HIGH';
        ui.gearText.color = 'lime';
    } else {
        ui.gearText.innerHTML = 'LOW';
        ui.gearText.color = 'red';
    }
});

NetworkTables.addKeyListener('/SmartDashboard/Indexer', (key, value) => {
    if(value == true) {
        ui.indexerText.innerHTML = 'RUNNING';
        ui.indexerText.style.color = 'lime';
    } else {
        ui.indexerText.innerHTML = 'STOPPED';
        ui.indexerText.style.color = 'red';
    }
});

NetworkTables.addKeyListener('/SmartDashboard/TurnDirection', (key, value) => {
    if(value == 0) {
        ui.vision.degreeNumber.style.opacity = 0;
        ui.vision.leftArrow.style.opacity = 0;
        ui.vision.rightArrow.style.opacity = 0;

    } else {
        if(value > 0) {
            ui.vision.rightArrow.style.opacity = 1;
            ui.vision.leftArrow.style.opacity = 0;
        } else {
            ui.vision.rightArrow.style.opacity = 0;
            ui.vision.leftArrow.style.opacity = 1;
        }
        ui.vision.degreeNumber.style.opacity = 1;
        ui.vision.degreeNumber.innerHTML = Math.abs(value) + 'º';
    }
});

//UNTESTED:
// Load list of prewritten autonomous modes
NetworkTables.addKeyListener('/SmartDashboard/autonomous/modes', (key, value) => {
    // Clear previous list
    while (ui.input.autoSelect.firstChild) {
        ui.input.autoSelect.removeChild(ui.input.autoSelect.firstChild);
    }
    // Make an option for each autonomous mode and put it in the selector
    for (let i = 0; i < value.length; i++) {
        var option = document.createElement('option');
        option.appendChild(document.createTextNode(value[i]));
        ui.input.autoSelect.appendChild(option);
    }
    // Set value to the already-selected mode. If there is none, nothing will happen.
    ui.input.autoSelect.value = NetworkTables.getValue('/SmartDashboard/currentlySelectedMode');
});
