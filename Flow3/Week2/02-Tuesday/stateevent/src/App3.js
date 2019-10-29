import React, { useState, useEffect } from 'react';
import './App.css';

function FetchJoke(url) {
    return fetch(url, {
        headers: new Headers({
            "Accept" : "application/json"
        })
    })
    .then(response => response.json());
}

function App() {
    const [cnjoke, setCNJoke] = useState("");
    const [dadjoke, setDadJoke] = useState("");

    useEffect(() => {
        const interval = setInterval(() => {
            FetchJoke("https://icanhazdadjoke.com/").then(data => setDadJoke(data.joke));
        }, 10000);
        return () => clearInterval(interval);
    });

    return (
        <div className="App">
            <button onClick={() => FetchJoke("https://api.chucknorris.io/jokes/random").then(data => setCNJoke(data.value))}>Get Chuck Norris Joke</button>
            <p><b>Chuck Norris Joke:</b> {cnjoke}</p>
            <p><b>Dad Joke:</b> {dadjoke}</p>
        </div>
    )
}

export default App;
