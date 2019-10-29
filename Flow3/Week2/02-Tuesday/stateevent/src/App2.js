import React, { useState, useEffect } from 'react';
import './App.css';

function Clock() {
    var [time, setTime] = useState(new Date().toLocaleTimeString('en-US'));

    useEffect(() => {
        const interval = setInterval(() => {
            setTime(new Date().toLocaleTimeString('en-US'));
        }, 1000);
        return () => clearInterval(interval);
    }, []);

    return <h1>Time is: {time}</h1>;
}

function App() {
  return (
    <div className="App">
        <Clock />
    </div>
  )
}

export default App;
