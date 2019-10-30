import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import App2 from './App2';
import App3 from './App3';

const Applications = () => {
    return (
        <div>
            <App />
            <App2 />
            <App3 />
        </div>
    )
}

ReactDOM.render(<Applications />, document.getElementById('root'));
