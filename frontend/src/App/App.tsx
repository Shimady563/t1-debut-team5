import Button from '@/ui/Button/Button';
import './App.scss';
import Radar from '@/modules/Radar/components/Radar/Radar';

import AuthPage from '@/pages/AuthPage/components/AuthPage/AuthPage';
import RegistrationPage from '@/pages/RegistrationPage/components/RegistrationPage/RegistrationPage';
import PrivateRoute from '@/utils/PrivateRoute';
import Header from '@/components/Header/Header';
import { Route, BrowserRouter, Routes, Navigate } from 'react-router-dom';
import MainPage from '@/pages/MainPage/MainPage';
import AdminPage from '@/pages/AdminPage/components/AdminPage';
import { useDispatch } from 'react-redux';
import { setTechnologies } from '@/store/TechnologiesStore';
import { mockElements } from '@/modules/Radar/consts';
import { useEffect, useState } from 'react';
import axios from 'axios';
import { setUser, useUserInfo } from '@/store/UserSlice';
import useCheckUser from '@/globalApi/checkUserRequest';
import StartPage from '@/pages/StartPage/StartPage';

const App = () => {
  const dispatch = useDispatch();
  const chekUser = useCheckUser();
  const user = useUserInfo();

  const [isAuth, setIsAuth] = useState<boolean>(false);

  useEffect(() => {
    chekUser();
  }, []);

  useEffect(() => {
    setIsAuth(user.isAuth);
    console.log(isAuth);
  }, [useUserInfo()]);

  return (
    <>
      <BrowserRouter>
        <Header />
        <Routes>
          <Route element={<PrivateRoute isAllowed={user.isAuth} />}>
            <Route element={<MainPage />} path="/radar" />
          </Route>

          <Route
            element={
              <PrivateRoute isAllowed={user.isAuth && user.user.admin} />
            }
          >
            <Route element={<AdminPage />} path="/admin" />
          </Route>

          <Route element={<PrivateRoute isAllowed={!user.isAuth} />}>
            <Route element={<AuthPage />} path="/login" />
          </Route>

          <Route element={<StartPage />} path="/" />
        </Routes>
      </BrowserRouter>
    </>
  );
};

export default App;
