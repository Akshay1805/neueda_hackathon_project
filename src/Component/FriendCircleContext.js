import React, { createContext, useState } from 'react';

const FriendCircleContext = createContext();

const FriendCircleProvider = ({ children }) => {
    const [friendCircle, setFriendCircle] = useState(null);

    return (
        <FriendCircleContext.Provider value={{ friendCircle, setFriendCircle }}>
            {children}
        </FriendCircleContext.Provider>
    );
};

export { FriendCircleContext, FriendCircleProvider };
