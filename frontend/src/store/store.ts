import { configureStore } from '@reduxjs/toolkit';
import userReducer from './UserSlice';
import technologiesReducer from './TechnologiesStore';

const store = configureStore({
  reducer: {
    userData: userReducer,
    technologiesData: technologiesReducer,
  },
});

export default store;
