import useCheckUser from '@/globalApi/checkUserRequest';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';

export const useRegister = () => {
  const checkUser = useCheckUser();
  const navigate = useNavigate();
  const registerRequest = async (email: string, password: string) => {
    try {
      const response: any = await axios(
        'http://localhost:8080/api/v1/auth/register',
        {
          headers: {
            'Content-Type': 'application/json',
          },
          method: 'POST',
          data: { email: email, password, specializationId: 1 },
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
      toast.error('Пользователь с такой почтой уже существует!');
    }
  };

  return registerRequest;
};
