import React, { useState, useEffect } from 'react';
import './App.css';

function FetchJoke(url) {
    return fetch(url)
    .then(response => response.json());
}

function App() {
    const [cnjoke, setCNJoke] = useState("");
    const [dadjoke, setDadJoke] = useState("");

    useEffect(() => {
        const interval = setInterval(() => {
            FetchJoke("https://icanhazdadjoke.com/slack").then(data => setDadJoke(data.attachments[0].text));
        }, 10000);
        return () => clearInterval(interval);
    });

    return (
        <div className="App">
            <button onClick={() => FetchJoke("https://api.chucknorris.io/jokes/random").then(data => setCNJoke(data.value))}>Get Chuck Norris Joke</button>
            <p><b>Chuck Norris Joke:</b> {cnjoke}</p>
            <p><b>I Can Joke:</b> {dadjoke}</p>
        </div>
    )
}

export default App;
