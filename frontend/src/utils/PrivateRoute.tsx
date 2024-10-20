import React from 'react';
import { Outlet, Navigate } from 'react-router-dom';

const PrivateRoute = () => {
  const isAuth = true;
  return isAuth ? <Outlet /> : <Navigate to="/login" />;
};

export default PrivateRoute;
