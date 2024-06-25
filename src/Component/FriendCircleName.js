import React, { useContext } from 'react';
import { FriendCircleContext } from './FriendCircleContext';
import Header from './Header';

const FriendCircleName = () => {
    const { friendCircle } = useContext(FriendCircleContext);

    if (!friendCircle) {
        return <div>No Friend Circle Data Available</div>;
    }

    return (
        <div>
            <Header />
            <div className="friend-circle-details">
                <h2>Friend Circle Details</h2>
                <p>ID: {friendCircle.circleId}</p>
                <p>Name: {friendCircle.circleName}</p>
                <p>Category: {friendCircle.category}</p>
                <p>Date: {friendCircle.date}</p>
            </div>
        </div>
    );
};

export default FriendCircleName;
