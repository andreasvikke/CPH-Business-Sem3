import React from 'react';
import { Link } from 'react-router-dom';

const UserDetail = ({ match, data }) => {
    let user = data.find((u, index) => index == match.params.index);

    return (
        <div className="container">
            <div className="row">
                <div className="col-sm">
                    <table className="table">
                        <tbody>
                            <tr><td>Name: </td><td>{user.name.title}. {user.name.first} {user.name.last}</td></tr>
                            <tr><td>Gender: </td><td>{user.gender}</td></tr>
                            <tr><td>Email: </td><td>{user.email}</td></tr>
                            <tr><td>Phone: </td><td>{user.phone}</td></tr>
                            <tr><td></td><td></td></tr>
                            <tr><td>Street: </td><td>{user.location.street.name} {user.location.street.number}</td></tr>
                            <tr><td>City: </td><td>{user.location.city}</td></tr>
                            <tr><td>ZIP: </td><td>{user.location.postcode}</td></tr>
                        </tbody>
                    </table>
                </div>
                <div className="col-sm">
                    <img className="col-sm" src={user.picture.large} alt="Profile" />
                </div>
            </div>
            <Link className="btn btn-primary" to="/all">Back</Link>
        </div>
    );
}

export default UserDetail;