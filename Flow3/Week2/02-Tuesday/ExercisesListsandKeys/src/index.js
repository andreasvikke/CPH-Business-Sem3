import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './ListDemoApp';
import App2 from './ListDemoApp2';

let app = <App/>

const DontUseMeForReal = () => {
    return (
        <div className="App">
         <a href="/" onClick={handleSelect} id="app1">ex1</a> &nbsp;
         <a href="/" onClick={handleSelect} id="app2">ex2</a> &nbsp;
         {app}
        </div>
    )
}

function handleSelect(event) {
    event.preventDefault();
    const id = event.target.id;
    switch (id) {
        case "app1": app = <App />; break;
        case "app2": app = <App2 />; break;
    }
    ReactDOM.render(<DontUseMeForReal />, document.getElementById('root'));
}

ReactDOM.render(<DontUseMeForReal />, document.getElementById('root'));
