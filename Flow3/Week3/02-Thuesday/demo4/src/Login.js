import React, { useState, useEffect } from 'react';
import { Redirect } from 'react-router-dom';

function Login(props) {
    const { from } = props.location.state || { from: { pathname: '/'} };
    const [redirectToReferrer, setRedirectToReferrer] = useState(false);

    const login = () => {
        fakeAuth.authenticate(() => {
            setRedirectToReferrer(true);
        })
    }

    if(redirectToReferrer) {
        return (
            <Redirect to={from} />
        )
    } else {
        return (
            <div>
                <p>You must log in to view the page at {from.pathname}</p>
                <button onClick={login}>Log in</button>
            </div>
        )
    }
}
export default Login;

export const fakeAuth = {
    isAuthenticated: false,
    authenticate(cb) {
        this.isAuthenticated = true;
        setTimeout(cb, 100);
      }
  }