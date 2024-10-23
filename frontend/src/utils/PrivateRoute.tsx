import { useUserInfo } from '@/store/UserSlice';
import React from 'react';
import { Outlet, Navigate } from 'react-router-dom';

type PrivateRouteProps = {
  isAllowed: boolean;
};

const PrivateRoute: React.FC<PrivateRouteProps> = ({ isAllowed }) => {
  if (isAllowed) {
    return <Outlet />;
  } else {
    return <Navigate to="" />;
  }
};

export default PrivateRoute;
