import React from 'react';
import './App.css';
import { Router, Link, Route, Switch } from 'react-router-dom';
import Category from './Category';
import Products from './Products';

const Home = () => (
  <div>
    <h2>Home</h2>
  </div>
)

function App() {
  return (
      <div className="App">
        <ul className="nav">
          <li className="nav-item"><Link className="nav-link" to="/">Home</Link></li>
          <li className="nav-item"><Link className="nav-link" to="/category">Category</Link></li>
          <li className="nav-item"><Link className="nav-link" to="/products">Products</Link></li>
        </ul>
        <Route path="/" exact component={Home} />
        <Route path="/category" component={Category} />
        <Route path="/products" component={Products} />
      </div>
  );
}

export default App;
