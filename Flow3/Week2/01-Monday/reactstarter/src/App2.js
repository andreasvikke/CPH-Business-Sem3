import React from 'react';
import './App.css';
import person, {males, females} from './file2';

const {firstName, email, gender, lastName} = person;

const personv2 = {
    email: email,
    firstName: firstName,
    friends: [...males, ...females],
    gender: gender,
    lastName: lastName,
    phone: "12345678"
}

function App() {
    console.log(...males, ...females);
    console.log(...males, "Kurt", "Helle", ...females, "Tina");
    console.log(personv2);

    return (
      <div className="App">
        <p>{firstName}</p>
        <p>{email}</p>
      </div>
    );
  }
  
  export default App;