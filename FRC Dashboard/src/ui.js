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
        slider: document.getElementById('test-element'),
        arm: document.getElementById('climb-arm'),
    },
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

setInterval(() => {
    var value = ui.climber.slider.getAttribute('value');

    ui.climber.arm.setAttribute('cx', 57 + value);
}, 1)

ui.climber.slider.oninput = function() {
    NetworkTables.putValue('/SmartDashboard/Climber Elevation', parseInt(this.value));
}

// Update match timer
NetworkTables.addKeyListener('/robot/time', (key, value) => {
    ui.timer.textContent = value < 0 ? '0:00' : Math.floor(value / 60) + ':' + (value % 60 < 10 ? '0' : '') + value % 60;
});

// Error handler
addEventListener('error',(ev)=>{
    ipc.send('windowError',{mesg:ev.message,file:ev.filename,lineNumber:ev.lineno})
})

//Event Listeners:

NetworkTables.addKeyListener('/SmartDashboard/turret_rotation', (key, value) => {
    ui.gyro.turret.marker.setAttribute('transform', 'rotate(' + value + ',0,30)');
})

NetworkTables.addKeyListener('/SmartDashboard/', (key, value) => {

})
