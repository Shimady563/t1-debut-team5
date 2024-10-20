import Button from '@/ui/Button/Button';
import './App.scss';
import Radar from '@/modules/Radar/components/Radar/Radar';

import AuthPage from '@/pages/AuthPage/components/AuthPage/AuthPage';
import RegistrationPage from '@/pages/RegistrationPage/components/RegistrationPage/RegistrationPage';
import PrivateRoute from '@/utils/PrivateRoute';
import Header from '@/components/Header/Header';
import { Route, BrowserRouter, Routes } from 'react-router-dom';
import MainPage from '@/pages/MainPage/MainPage';
const App = () => {
  return (
    <>
      <BrowserRouter>
        <Header />
        <Routes>
          <Route element={<PrivateRoute />}></Route>
          <Route element={<MainPage />} path="/" />
          <Route element={<AuthPage />} path="/login" />
          <Route element={<RegistrationPage />} path="/reg" />
        </Routes>
      </BrowserRouter>
    </>
  );
};

export default App;
