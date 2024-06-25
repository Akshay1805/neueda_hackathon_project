import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom'; // Import useNavigate hook
import './Register.css';
import Header1 from './Header1';
const RegistrationForm = () => {
    const [form, setForm] = useState({
        userId: '',
        firstName: '',
        lastName: '',
        email: '',
        password: '',
    });

    const navigate = useNavigate(); // Initialize useNavigate hook

    const handleChange = (e) => {
        const { name, value } = e.target;
        setForm({
            ...form,
            [name]: value,
        });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log('Form submitted:', form);
        // Add form submission logic here (e.g., send data to backend)

        // Navigate to create-friend-circle page after successful registration
        navigate('/friendcirclepage');
    };

    return (
        <div>
            <Header1 />
            <div className="registration-container">
                <form onSubmit={handleSubmit} className="registration-form">
                    <h2>Create Account</h2>
                    <div className="form-group">
                        <label>User Id:</label>
                        <input
                            type="text"
                            name="userId"
                            value={form.userId}
                            onChange={handleChange}
                            placeholder="Enter User Id"
                            required
                        />
                    </div>
                    <div className="form-group">
                        <label>First Name:</label>
                        <input
                            type="text"
                            name="firstName"
                            value={form.firstName}
                            onChange={handleChange}
                            placeholder="Enter First Name"
                            required
                        />
                    </div>
                    <div className="form-group">
                        <label>Last Name:</label>
                        <input
                            type="text"
                            name="lastName"
                            value={form.lastName}
                            onChange={handleChange}
                            placeholder="Enter Last Name"
                            required
                        />
                    </div>
                    <div className="form-group">
                        <label>Email:</label>
                        <input
                            type="email"
                            name="email"
                            value={form.email}
                            onChange={handleChange}
                            placeholder="Enter Email"
                            required
                        />
                    </div>
                    <div className="form-group">
                        <label>Password:</label>
                        <input
                            type="password"
                            name="password"
                            value={form.password}
                            onChange={handleChange}
                            placeholder="Enter Password"
                            required
                        />
                    </div>
                    <button type="submit" className="register-button">Register</button>
                </form>
            </div>
        </div>
    );
};

export default RegistrationForm;
