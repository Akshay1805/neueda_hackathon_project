// Header.js
import React from 'react';
import { Link } from 'react-router-dom';
import './Header.css'; // Import the CSS file for styling

const Header = () => {
    return (
        <div className="navbar">
            <h1 className="navbar-title">Title</h1>
            <div className="navbar-links">
                <Link to="/home" className="navbar-link">
                    <button className="navbar-button">Home</button>
                </Link>
                <Link to="/friendcirclepage" className="navbar-link">
                    <button className="navbar-button">Create Friend Circle</button>
                </Link>
                <Link to="/displayfriendlist" className="navbar-link">
                    <button className="navbar-button">Display Friend Circle List</button>
                </Link>
            </div>
        </div>
    );
};

export default Header;
