import apiClient from '@/globalApi/apiClient';
import useCheckUser from '@/globalApi/checkUserRequest';
import { setTokenToCookie } from '@/utils/tokenSetter';
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';

export const useLogin = () => {
  const checkUser = useCheckUser();
  const navigate = useNavigate();
  const loginRequest = async (email: string, password: string) => {
    try {
      const response = await apiClient.post('auth/login', { email, password });
      setTokenToCookie(response.data.jwtToken);

      await checkUser();

      navigate('/radar');
    } catch (error) {
      toast.error('Проверьте введенные данные!');
    }
  };

  return loginRequest;
};
