import React from 'react';
import { Link } from 'react-router-dom';
import Header from './Header';
const DisplayFriendList = () => {

    const handleLeaveClick = () => {
        // Handle leave logic here
        console.log('Leave button clicked');
    };

    const handleDeleteClick = () => {
        // Handle delete logic here
        console.log('Delete button clicked');
    };

    return (
        <div>
            <Header />

            <div >
                <h2>Display List</h2>
                <div >
                    <Link to="/friendcirclename" >Friend Circle Name</Link>
                    <Link to="/addmember" >Add Friend</Link>
                    <Link to="/addexpense" >Add Expense</Link>
                    <button onClick={handleLeaveClick}>Leave</button>
                    <button onClick={handleDeleteClick}>Delete</button>

                </div>
            </div>
        </div>
    );
};

export default DisplayFriendList;
