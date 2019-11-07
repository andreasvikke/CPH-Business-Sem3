import React from 'react';
import { Link } from 'react-router-dom';

const AllUsers = ({ data }) => {
    console.log(data);
    let usersList = data.map((user, index) => (
        <tr key={index}>
        <td><img src={user.picture.thumbnail} alt="Profile" /></td>
        <td>{user.name.title}. {user.name.first} {user.name.last}</td>
        <td><Link to={`/details/${index}`}>Details</Link></td>
        </tr>
    ));
    return (
        <div>
        <table className="table">
            <thead className="thead-dark">
            <tr>
                <th></th>
                <th>Name</th>
                <th>Details</th>
            </tr>
            </thead>
            <tbody>
            {usersList}
            </tbody>
        </table>
        <Link className="btn btn-primary" to="/">Back</Link>
        </div>
    );
}
export default AllUsers;