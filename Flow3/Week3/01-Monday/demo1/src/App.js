import React from 'react';
import './App.css';
import { Link, Route, Switch } from 'react-router-dom';

const Home = () => (
  <div>
    <h2>Home</h2>
  </div>
)

const Category = () => (
  <div>
    <h2>Category</h2>
  </div>
)

const Products = () => (
  <div>
    <h2>Products</h2>
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
