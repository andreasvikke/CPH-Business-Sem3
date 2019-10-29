import React, { useState, useEffect } from 'react';
import './App.css';

function App() {
    const [joke, setJoke] = useState("");
    const [apiurl, setApiurl] = useState("https://icanhazdadjoke.com/");

    useEffect(() => {
        fetch(apiurl, {
            headers: new Headers({
                "Accept" : "application/json"
            })
        })
        .then(response => response.json());
    }, [apiurl]);

    useEffect(() => {
        const interval = setInterval(() => {
            setApiurl("https://icanhazdadjoke.com/");
        }, 10000);
        return () => clearInterval(interval);
    });

    return (
        <div className="App">
            <button onClick={() => setApiurl("https://api.chucknorris.io/jokes/random")}>Get Chuck Norris Joke</button>
            <p><b>Chuck Norris Joke:</b> {cnJoke}</p>
            <p><b>Dad Joke:</b> {dadJoke}</p>
        </div>
    )
}

export default App;
