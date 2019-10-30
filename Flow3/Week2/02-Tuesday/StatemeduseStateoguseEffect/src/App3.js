import React, { useState, useEffect } from 'react';
import './App.css';

async function FetchJoke(url) {
    const response = await fetch(url, {
        headers: new Headers({
        "Accept" : "application/json"
        })
    });
    return await response.json();
}

function App() {
    const [cnjoke, setCNJoke] = useState("");
    const [dadjoke, setDadJoke] = useState("");

    useEffect(() => {
        const interval = setInterval(async() => {
            setDadJoke((await FetchJoke("https://icanhazdadjoke.com/")).joke);
        }, 10000);
        return () => clearInterval(interval);
    });

    return (
        <div className="App">
            <button onClick={async() => setCNJoke((await FetchJoke("https://api.chucknorris.io/jokes/random")).value)}>Get Chuck Norris Joke</button>
            <p><b>Chuck Norris Joke:</b> {cnjoke}</p>
            <p><b>I Can Joke:</b> {dadjoke}</p>
        </div>
    )
}

export default App;