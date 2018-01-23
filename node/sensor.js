const execSync = require('child_process').execSync;
var fs = require('fs');
var util = require('util');
var logFile = fs.createWriteStream('logs/temp_log.txt', { flags: 'a' });
var logStdout = process.stdout;


var nano = require('nano')('http://192.168.1.201:5984');
var sensors_db = nano.db.use('sensor_db');

console.log = function () {
  logFile.write(util.format.apply(null, arguments) + '\n');
  logStdout.write(util.format.apply(null, arguments) + '\n');
}
console.error = console.log;

function sendSensorData () {
  // Get raw temp data
  let tempRaw = execSync('./tmp102.py').toString();

  // Parse raw temp data
  let temp = parseFloat(tempRaw.split('\n')[0]);

  var data = new SensorData(temp)
  sensors_db.insert(data, function(err, body){
    if(!err){
      console.log('awesome')
    }else{
          console.log(err)
        }
  });
}

function SensorData(temperature){
  this.timestamp = new Date()
  this.name = 'Temperature'
  //this.value = (Math.random() * 2) + 7
  this.value = temperature
  this.unit = 'Celsius'
}

setInterval(() => {
  sendSensorData()
}, 500)
