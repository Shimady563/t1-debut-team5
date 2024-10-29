import useCheckUser from '@/globalApi/checkUserRequest';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';

export const useLogin = () => {
  const checkUser = useCheckUser();
  const navigate = useNavigate();
  const loginRequest = async (email: string, password: string) => {
    try {
      const response: any = await axios(
        'http://localhost:8080/api/v1/auth/login',
        {
          headers: {
            'Content-Type': 'application/json',
          },
          method: 'POST',
          data: { email: email, password },
        }
      );
      document.cookie = `jwt=${
        response.data.jwtToken
      }; path=/; SameSite=Lax; Expires=${new Date(
        Date.now() + 365 * 24 * 60 * 60 * 1000
      ).toUTCString()}`;
      await checkUser();

      navigate('/radar');
    } catch (error) {
      toast.error('Проверьте введенные данные!');
    }
  };

  return loginRequest;
};
