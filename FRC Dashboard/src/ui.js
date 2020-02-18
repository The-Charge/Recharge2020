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
    test: document.getElementById('test-element'),
};

// Update camera every second
setInterval(() => {
    ui.camera.screen.style.backgroundImage = 'url("https://www.alimentarium.org/en/system/files/thumbnails/image/AL027-01_pomme_de_terre_0.jpg")';
}, 1000);

setInterval(() => {
    ui.gyro.turret.value += 1;
    ui.gyro.turret.marker.setAttribute('transform', 'rotate(' + ui.gyro.turret.value + ',0,30)');
}, 1)

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
