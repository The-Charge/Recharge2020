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
        turret: {
            val: 0,
            offset: 0,
            visualVal: 0,
        },
        container: document.getElementById('gyro'),
        turret: document.getElementById('gyro-turret'),
    },
    test: document.getElementById('test-element'),
};

// Update camera every second
setInterval(() => {
    ui.camera.screen.style.backgroundImage = 'url("https://www.alimentarium.org/en/system/files/thumbnails/image/AL027-01_pomme_de_terre_0.jpg")';
}, 1000);

// Update match timer
NetworkTables.addKeyListener('/robot/time', (key, value) => {
    ui.timer.textContent = value < 0 ? '0:00' : Math.floor(value / 60) + ':' + (value % 60 < 10 ? '0' : '') + value % 60;
});

// Error handler
addEventListener('error',(ev)=>{
    ipc.send('windowError',{mesg:ev.message,file:ev.filename,lineNumber:ev.lineno})
})

//Event Listeners:

// Gyro rotation
let updateGyro = (key, value) => {
    ui.gyro.val = value;
    ui.gyro.visualVal = Math.floor(ui.gyro.val - ui.gyro.offset);
    ui.gyro.visualVal %= 360;
    if (ui.gyro.visualVal < 0) {
        ui.gyro.visualVal += 360;
    }

    ui.gyro.arm.style.transform = `rotate(${ui.gyro.visualVal}deg)`;
};
NetworkTables.addKeyListener('/SmartDashboard/Yaw', updateGyro);
