import React from 'react';
import PropTypes from 'prop-types';
import './App.css';
import {names} from './file2';

function Welcome(props) {
    return <h1>Hello, {props.name}</h1>;
  }

function WelcomePerson(props) {
    return <h1>Hello, {props.person.firstName} {props.person.lastName}, {props.person.email}</h1>;
  }

  
  
  function App() {
    return (
      <div>
        <Welcome name="Sara" />
        <Welcome name="Cahal" />
        <Welcome name="Edite" />
        <hr />
        <Welcome />
        <Welcome name={45} />
        <hr />
        {names.map( (p, index) => <WelcomePerson key={index} person={p}/>)}
      </div>
    );
  }

  export default App;

  Welcome.propTypes = {
    name: PropTypes.string.isRequired
  };
  Welcome.defaultProps = {
    name: 'Stranger'
  };

  WelcomePerson.propTypes = {
    person: PropTypes.element.isRequired &&
    PropTypes.shape(
      {
          firstName: PropTypes.string.isRequired,
          lastName: PropTypes.string.isRequired,
          email: PropTypes.string.isRequired
      })
  };