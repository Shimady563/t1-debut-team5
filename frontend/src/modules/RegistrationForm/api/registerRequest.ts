import apiClient from '@/globalApi/apiClient';
import useCheckUser from '@/globalApi/checkUserRequest';
import { setTokenToCookie } from '@/utils/tokenSetter';
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';

export const useRegister = () => {
  const checkUser = useCheckUser();
  const navigate = useNavigate();
  const registerRequest = async (email: string, password: string) => {
    try {
      const response = await apiClient.post('auth/register', {
        email,
        password,
        specializationId: 1,
      });
      setTokenToCookie(response.data.jwtToken);

      await checkUser();

      navigate('/radar');
    } catch (error) {
      toast.error('Пользователь с такой почтой уже существует!');
    }
  };

  return registerRequest;
};
