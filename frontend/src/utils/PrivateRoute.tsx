import { useUserInfo } from '@/store/UserSlice';
import React from 'react';
import { Outlet, Navigate } from 'react-router-dom';

const PrivateRoute = () => {
  const user = useUserInfo();
  return user.isAuth ? <Outlet /> : <Navigate to="/login" />;
};

export default PrivateRoute;
