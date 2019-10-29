import React, { useState, useEffect } from 'react';
import './App.css';

function Clock() {
    var [hours, setHours] = useState(12);
    var [minutes, setMinutes] = useState(59);
    var [seconds, setSeconds] = useState(55);
    var [time, setTime] = useState("PM");

    useEffect(() => {
        const interval = setInterval(() => {
            if(seconds < 59) {
                setSeconds(++seconds);
            } else {
                setSeconds(0);
                setMinutes(++minutes);
            }
            if(minutes >= 60) {
                setMinutes(0)
                setHours(++hours);
            }
            if(hours >= 13) {
                setHours(1);
                if(time === "PM") {
                    setTime("AM");
                } else {
                    setTime("PM");
                }
            }
        }, 1000);
        return () => clearInterval(interval);
    });

    return (
        <h1>Time is: {("0" + hours).slice(-2)}:{("0" + minutes).slice(-2)}:{("0" + seconds).slice(-2)} {time}</h1>
    )
}

function App() {
  return (
    <div className="App">
        <Clock />
    </div>
  )
}

export default App;
