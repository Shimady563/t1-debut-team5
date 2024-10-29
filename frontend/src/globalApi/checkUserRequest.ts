import { useDispatch } from 'react-redux';
import { setUser } from '@/store/UserSlice';
import { getTokenFromCookie } from '@/utils/tokenGetter';
import apiClient from './apiClient';
import { toast } from 'react-toastify';

const useCheckUser = () => {
  const dispatch = useDispatch();

  const checkUser = async () => {
    try {
      const token = getTokenFromCookie();
      const response = await apiClient.get('users/me', {
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${token}`,
        },
      });

      dispatch(setUser(response.data));
      localStorage.setItem('isAuth', 'true');
      localStorage.setItem('isAdmin', response.data.admin);
    } catch (error) {
      toast.error('Упс...Что-то пошло не так');
    }
  };

  return checkUser;
};

export default useCheckUser;
