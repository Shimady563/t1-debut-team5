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
import { useEffect } from 'react';
const App = () => {
  const dispatch = useDispatch();

  const getTechnologies = () => {
    try {
      // const response = await axios(
      //   `http://localhost:8080/api/v1/technologies/active?active=true`,
      //   {
      //     method: 'GET',
      //     //   withCredentials: 'true',
      //   }
      // );
      dispatch(setTechnologies(mockElements));
    } catch (error) {
      console.log(error);
    }
  };

  useEffect(() => {
    getTechnologies();
  }, []);

  return (
    <>
      <BrowserRouter>
        <Header />
        <Routes>
          <Route element={<PrivateRoute />}></Route>
          <Route element={<MainPage />} path="/" />
          <Route element={<AuthPage />} path="/login" />
          <Route element={<AdminPage />} path="/reg" />
        </Routes>
      </BrowserRouter>
    </>
  );
};

export default App;
