import React, {useState} from "react";
import "./App.css";
import "bootstrap/dist/css/bootstrap.min.css";
import PersonList from "./PersonList"
import NewPerson from "./NewPerson"
import uuid from "uuid/v1";

function App() {
  const initialData = [
    { id: uuid(), name: "Martin" },
    { id: uuid(), name: "William" },
    { id: uuid(), name: "Asger" }
  ]
  const [persons, setPersons] = useState(initialData);
  const [newPerson, setNewPerson] = useState({ id: "", name: "" });
  console.log(persons)

  const addPerson = person => {
    if (person.id === "") { // id=-1 Indicates a new object
      person.id = uuid();
      persons.push(person);
    } else {//if id != "", it's an existing todo. Find it and add changes
      let personToEdit = persons.find(p => p.id === person.id);
      personToEdit.name = person.name;
    }
    setPersons([...persons]);
    setNewPerson({id:"", name:""})
  };

  const deletePerson = index => {
    setPersons(persons.filter(p => p.id !== index));
  }

  const editPerson = index => {
    setNewPerson({id: index, name: ""});
  }
  
  return (
    <div className="container outer">
      <h2 style={{ textAlign: "center", marginBottom:25 }}>
        Props and Lifting State Demo
      </h2>

      <div className="row">
        <div className="col-6 allTodos">
          <PersonList persons={persons} 
            deletePerson={deletePerson}
            editPerson={editPerson} />
        </div>
        <div className="col-5 new-todo">
          <NewPerson
            addPerson={addPerson}
            nextPerson={newPerson}            
          />
        </div>
      </div>
    </div>
  );
}
export default App;
