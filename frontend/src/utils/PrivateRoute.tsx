import React from 'react';
import { Outlet, Navigate } from 'react-router-dom';

type PrivateRouteProps = {
  isAllowed: boolean;
  navPath: string;
};

const PrivateRoute: React.FC<PrivateRouteProps> = ({ isAllowed, navPath }) => {
  if (isAllowed) {
    return <Outlet />;
  } else {
    return <Navigate to={`/${navPath}`} />;
  }
};

export default PrivateRoute;
