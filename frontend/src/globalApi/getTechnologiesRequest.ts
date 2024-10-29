import { setTechnologies } from '@/store/TechnologiesStore';
import { getTokenFromCookie } from '@/utils/tokenGetter';
import { useDispatch } from 'react-redux';
import apiClient from './apiClient';

const useGetTechnologiesRequest = () => {
  const dispatch = useDispatch();
  const getTechnologies = async () => {
    try {
      const token = getTokenFromCookie();

      const response = await apiClient.get('technologies/active?active=true', {
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${token}`,
        },
      });
      dispatch(setTechnologies(response.data));
    } catch (error) {
      console.log(error);
    }
  };

  return getTechnologies;
};

export default useGetTechnologiesRequest;
