import { useDispatch } from 'react-redux';
import axios from 'axios';
import { setUser } from '@/store/UserSlice';
import { getTokenFromCookie } from '@/utils/tokenGetter';

const useCheckUser = () => {
  const dispatch = useDispatch();

  const checkUser = async () => {
    try {
      const token = getTokenFromCookie();

      const response: any = await axios.get(
        'http://localhost:8080/api/v1/users/me',
        {
          headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${token}`,
          },
        }
      );
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
