import apiClient from '@/globalApi/apiClient';
import useCheckUser from '@/globalApi/checkUserRequest';
import useGetAllTechnologiesRequest from '@/globalApi/getAllTechnologiesRequest';
import { setTokenToCookie } from '@/utils/tokenSetter';
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';

/**
 * Возвращает request на login
 */
export const useLogin = () => {
  const checkUser = useCheckUser();
  const getTechnologies = useGetAllTechnologiesRequest();
  const navigate = useNavigate();
  const loginRequest = async (email: string, password: string) => {
    try {
      const response = await apiClient.post('auth/login', { email, password });
      setTokenToCookie(response.data.jwtToken);
      await checkUser();
      await getTechnologies();
      navigate('/radar');
    } catch (error) {
      console.log(error);
      toast.error('Проверьте введенные данные!');
    }
  };

  return loginRequest;
};
