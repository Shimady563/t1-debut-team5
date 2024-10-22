import Button from '@/ui/Button/Button';
import './App.scss';
import Radar from '@/modules/Radar/components/Radar/Radar';

import AuthPage from '@/pages/AuthPage/components/AuthPage/AuthPage';
import RegistrationPage from '@/pages/RegistrationPage/components/RegistrationPage/RegistrationPage';
import PrivateRoute from '@/utils/PrivateRoute';
import Header from '@/components/Header/Header';
import { Route, BrowserRouter, Routes } from 'react-router-dom';
import MainPage from '@/pages/MainPage/MainPage';
import AdminPage from '@/pages/AdminPage/components/AdminPage';
import { useDispatch } from 'react-redux';
import { setTechnologies } from '@/store/TechnologiesStore';
import { mockElements } from '@/modules/Radar/consts';
import { useEffect, useState } from 'react';
import axios from 'axios';
import { setUser, useUserInfo } from '@/store/UserSlice';
import useCheckUser from '@/globalApi/checkUserRequest';

const App = () => {
  const dispatch = useDispatch();
  const chekUser = useCheckUser();
  const user = useUserInfo();

  const getTechnologies = async () => {
    try {
      const token = document.cookie
        .split('; ')
        .find((row) => row.startsWith('jwt='))
        ?.split('=')[1];

      const response = await axios(
        `http://localhost:8080/api/v1/technologies/active?active=true`,
        {
          headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${token}`,
          },
          method: 'GET',
        }
      );
      dispatch(setTechnologies(response.data));
    } catch (error) {
      console.log(error);
    }
  };

  useEffect(() => {
    chekUser();
    getTechnologies();
  }, []);

  return (
    <>
      <BrowserRouter>
        <Header />
        <Routes>
          <Route element={<PrivateRoute />}>
            <Route element={<AdminPage />} path="/admin" />
            <Route element={<MainPage />} path="/" />
          </Route>

          <Route element={<AuthPage />} path="/login" />
          <Route element={<RegistrationPage />} path="/reg" />
        </Routes>
      </BrowserRouter>
    </>
  );
};

export default App;
