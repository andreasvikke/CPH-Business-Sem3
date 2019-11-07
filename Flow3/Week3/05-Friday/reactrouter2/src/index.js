import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import 'bootstrap/dist/css/bootstrap.min.css'
import BookStore from './BookStore'

ReactDOM.render(<App BookStore={BookStore} />, document.getElementById('root'));
