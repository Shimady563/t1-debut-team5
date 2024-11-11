import { setTechnologies } from '@/store/TechnologiesStore';
import { getTokenFromCookie } from '@/utils/tokenGetter';
import { useDispatch } from 'react-redux';
import apiClient from './apiClient';
// import { toast } from 'react-toastify';

const useGetAllTechnologiesRequest = () => {
  const dispatch = useDispatch();
  const getTechnologies = async () => {
    try {
      const token = getTokenFromCookie();
      const response = await apiClient.get('technologies', {
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${token}`,
        },
      });
      dispatch(setTechnologies(response.data));
    } catch (error) {
      // toast.error('Произошла ошибка, попробуйте позднее');
    }
  };

  return getTechnologies;
};

export default useGetAllTechnologiesRequest;
