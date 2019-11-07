import React from 'react';
import './App.css';
import { Link, Route, Switch, Redirect } from 'react-router-dom';
import Category from './Category';
import Products from './Products';
import Login, { fakeAuth } from './Login'

const Home = () => (
  <div>
    <h2>Home</h2>
  </div>
)

const Admin = ({ match }) => {
  return (
    <div>
      {" "}
      <h2>Welcome admin </h2>
    </div>
  );
};

const PrivateRoute = ({ component: Component, ...rest }) => {
  return (
    <Route
      {...rest}
      render={props =>
        fakeAuth.isAuthenticated === true ? (
          <Component {...props} />
        ) : (
          <Redirect
            to={{ pathname: "/login", state: { from: props.location } }}
          />
        )}
    />
  );
};

function App() {
  console.log(fakeAuth.isAuthenticated);
  return (
    <div className="App">
      <ul className="nav">
        <li className="nav-item"><Link className="nav-link" to="/">Home</Link></li>
        <li className="nav-item"><Link className="nav-link" to="/category">Category</Link></li>
        <li className="nav-item"><Link className="nav-link" to="/products">Products</Link></li>
        <li className="nav-item"><Link className="nav-link" to="/admin">Admin Area</Link></li>
      </ul>
      <Switch>
        <Route path="/login" component={Login}/>
        <Route path="/" exact component={Home} />
        <Route path="/category" component={Category} />
        <Route path="/products" component={Products} />
        <PrivateRoute path="/admin" component={Admin} />
      </Switch>
    </div>
  );
}

export default App;
