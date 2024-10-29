import { useDispatch } from 'react-redux';
import { setUser } from '@/store/UserSlice';
import { getTokenFromCookie } from '@/utils/tokenGetter';
import apiClient from './apiClient';

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
      console.error('Ошибка при отправке данных:', error);
    }
  };

  return checkUser;
};

export default useCheckUser;
