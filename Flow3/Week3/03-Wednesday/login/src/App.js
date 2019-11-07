import React, { useState, useEffect } from "react"
import facade from "./apiFacade";

function LogIn(props) {
  const [state, setState] = useState({});

  const login = (evt) => {
    evt.preventDefault();
    props.login(state.username, state.password);
  }

  const onChange = (evt) => {
    setState({...state, [evt.target.id]: evt.target.value})
  }
  
  return (
    <div>
      <h2>Login</h2>
      <form onSubmit={login} onChange={onChange} >
        <input placeholder="User Name" id="username" />
        <input placeholder="Password" id="password" />
        <button>Login</button>
      </form>
    </div>
  )
}

function LoggedIn() {
  const [dataFromServer, setDataFromServer] = useState("Fetching!!");

  useEffect(() => {
    facade.fetchData()
      .then(res => setDataFromServer(res.msg))
      .catch(e => console.log(e));
  }, [])

  return (
    <div>
      <h2>Data Received from server</h2>
      <h3>{dataFromServer}</h3>
    </div>
  )
}

function App() {
  const [loggedIn, setLoggedIn] = useState(false);
  const [error, setError] = useState("");

  const logout = () => {
    facade.logout();
    setLoggedIn(false);
  }
  const login = (user, pass) => {
    facade.login(user, pass)
      .then(res => {setLoggedIn(true); setError("")})
      .catch(e => setError("Wrong credentials"));
  }

  return (
    <div>
      {!loggedIn ? 
        (<div>
          <LogIn login={login} /> 
          <p>{error}</p>
        </div>) :
        ( <div>
            <LoggedIn/>
            <button onClick={logout}>Logout</button>
          </div>)}
    </div>
  )
}
export default App;
