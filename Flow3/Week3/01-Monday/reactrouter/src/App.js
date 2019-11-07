import React, { useEffect, useState } from 'react';
import './App.css';
import {BrowserRouter as Router, Route, Link, Switch} from "react-router-dom"
import AllUsers from './AllUsers'
import UserDetail from './UserDetails'

function App({ facade }) {
  const [data, setData] = useState([]);
  
  useEffect(() => {
    facade.getUsers(10).then(d => setData(d.results)); 
  }, [facade])

  return (
    <Router>
      <Switch>
        <div className="container text-center">
          <Route path="/" exact component={Welcome} />
          <Route path="/all" render={() => <AllUsers data={data} />} />
          <Route path="/details/:index" render={({match}) => <UserDetail match={match} data={data} />} />
        </div>
      </Switch>
    </Router>
  );
}

function Welcome() {
  return (
    <div>
      <h1>Welcome to our friends site</h1>
      <Link className="btn btn-primary" to="/all">See all Users</Link>
    </div>
  )
}

export default App;
