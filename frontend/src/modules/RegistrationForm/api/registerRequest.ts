import apiClient from '@/globalApi/apiClient';
import useCheckUser from '@/globalApi/checkUserRequest';
import useGetAllTechnologiesRequest from '@/globalApi/getAllTechnologiesRequest';
import { setTokenToCookie } from '@/utils/tokenSetter';
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';

/**
 * Возвразает post-request на регистрацию.
 */
export const useRegister = () => {
  const checkUser = useCheckUser();
  const getTechnologies = useGetAllTechnologiesRequest();

  const navigate = useNavigate();
  const registerRequest = async (
    email: string,
    password: string,
    specializationId: number
  ) => {
    try {
      const response = await apiClient.post('auth/register', {
        email,
        password,
        specializationId,
      });
      setTokenToCookie(response.data.jwtToken);
      await checkUser();
      await getTechnologies();

      navigate('/radar');
    } catch (error) {
      toast.error('Пользователь с такой почтой уже существует!');
    }
  };

  return registerRequest;
};
