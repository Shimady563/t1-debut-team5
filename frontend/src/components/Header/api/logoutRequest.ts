import { clearUser } from '@/store/UserSlice';
import { useDispatch } from 'react-redux';
import { useNavigate } from 'react-router-dom';

/**
 * Возвращает request на logout
 */
export const useLogout = () => {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const logoutRequest = () => {
    localStorage.removeItem('isAuth');
    localStorage.removeItem('isAdmin');
    document.cookie = 'jwt=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;';
    dispatch(clearUser());
    navigate('/login');
  };

  return logoutRequest;
};
