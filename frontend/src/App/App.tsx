import './App.scss';
import AuthPage from '@/pages/AuthPage/components/AuthPage/AuthPage';
import RegistrationPage from '@/pages/RegistrationPage/components/RegistrationPage/RegistrationPage';
import PrivateRoute from '@/utils/PrivateRoute';
import Header from '@/components/Header/Header';
import { Route, BrowserRouter, Routes, Navigate } from 'react-router-dom';
import MainPage from '@/pages/MainPage/MainPage';
import AdminPage from '@/pages/AdminPage/components/AdminPage';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { useEffect, useState } from 'react';
import { useUserInfo } from '@/store/UserSlice';
import useCheckUser from '@/globalApi/checkUserRequest';
import ModalWindow from '@/components/ModalWindow/ModalWindow';
import TechnologiesChipper from '@/modules/TechnologiesChipper/components/TechnologiesChipper';
import StatsPage from '@/pages/StatsPage/components/StatsPage';
import useGetAllTechnologiesRequest from '@/globalApi/getAllTechnologiesRequest';
import PollsPage from '@/pages/PollsPage/components/PollsPage';
import SharedPage from '@/pages/SharedPage/components/SharedPage';

const App = () => {
  const chekUser = useCheckUser();
  const user = useUserInfo();
  const [modalOpen, setModalOpen] = useState<boolean>(false);

  const isAuth = localStorage.getItem('isAuth');
  const isAdmin = localStorage.getItem('isAdmin');

  const [isUserAuth, setIsUserAuth] = useState<string>('false');
  const [isUserAdmin, setIsUserAdmin] = useState<string>('false');

  useEffect(() => {
    chekUser();
  }, []);

  useEffect(() => {
    isAuth && setIsUserAuth(isAuth);
    isAdmin && setIsUserAdmin(isAdmin);
    console.log(isUserAuth, isUserAdmin);
  }, [user.isAuth]);

  const getAllTechnologies = useGetAllTechnologiesRequest();

  useEffect(() => {
    getAllTechnologies();
  }, []);

  return (
    <>
      <BrowserRouter>
        <Header onModalClick={() => setModalOpen(true)} />
        <Routes>
          <Route
            element={
              <PrivateRoute navPath="login" isAllowed={isAuth == 'true'} />
            }
          >
            <Route element={<MainPage />} path="/radar" />
            <Route element={<PollsPage />} path="/polls" />
            <Route element={<SharedPage />} path="/share" />
          </Route>

          <Route
            element={
              <PrivateRoute navPath="radar" isAllowed={isAdmin == 'true'} />
            }
          >
            <Route element={<StatsPage />} path="/stats" />
            <Route element={<AdminPage />} path="/admin" />
          </Route>

          <Route
            element={
              <PrivateRoute navPath="radar" isAllowed={isAuth != 'true'} />
            }
          >
            <Route element={<AuthPage />} path="/login" />
            <Route element={<RegistrationPage />} path="/reg" />
          </Route>
          <Route path="*" element={<Navigate to="/radar" replace />} />
        </Routes>
      </BrowserRouter>
      <ModalWindow
        active={modalOpen}
        handleBackdropClick={() => setModalOpen(false)}
      >
        {modalOpen && (
          <TechnologiesChipper onSubmit={() => setModalOpen(false)} />
        )}
      </ModalWindow>
      <ToastContainer autoClose={2000} pauseOnHover={true} />
    </>
  );
};

export default App;
