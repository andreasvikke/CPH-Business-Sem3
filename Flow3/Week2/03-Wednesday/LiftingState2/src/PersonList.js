import React from 'react';
import PropTypes from 'prop-types';

const PersonList = ({ persons, deletePerson, editPerson }) => {
  return (
    <React.Fragment>
      <h2>All Todos</h2>
      <table>
        <thead>
          <tr>
            <th>id</th>
            <th>name</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
            {persons.map(person => (
              <tr key={person.id}>
                <td>{person.id}</td>
                <td>{person.name}</td>
                <td>
                  <a href="#/" onClick={() => deletePerson(person.id)}>(delete</a>
                  <a href="#/" onClick={() => editPerson(person.id)}>, edit)</a>
                </td>
              </tr>
            ))}
        </tbody>
      </table>
    </React.Fragment>
  );
};
export default PersonList;

PersonList.propTypes = {
  persons: PropTypes.array
}
