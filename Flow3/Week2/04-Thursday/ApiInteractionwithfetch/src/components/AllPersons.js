import React from "react"
import PropTypes from 'prop-types';

export default function AllPersons(props) {
  const { persons,editPerson,deletePerson} = props;
  //console.log(persons);
  
  return (
    <div>
      <table className="table">
        <thead>
          <tr><th>Age</th><th>Name</th><th>Gender</th><th>Email</th><th></th></tr>
        </thead>
        <tbody>
          {persons.map(person => (
            <tr key={person.id}>
              <td>{person.id}</td>
              <td>{person.name}</td>
              <td>{person.age}</td>
              <td>{person.gender}</td>
              <td>{person.email}</td>
              <td>
                (<a href="xx" onClick={(e)=>{e.preventDefault();editPerson(person)}}>edit</a>, 
                <a href="xx" onClick={(e)=>{e.preventDefault();deletePerson(person.id)}}>delete)</a>
              </td>
            </tr>
          ))}
        </tbody>
      </table>      
    </div>
  )
}

AllPersons.propTypes = {
  persons : PropTypes.array.isRequired,
  editPerson: PropTypes.func.isRequired,
  deletePerson: PropTypes.func.isRequired
}
