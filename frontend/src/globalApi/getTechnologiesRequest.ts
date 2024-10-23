import { setTechnologies } from '@/store/TechnologiesStore';
import axios from 'axios';
import { useDispatch } from 'react-redux';

const useGetTechnologiesRequest = () => {
  const dispatch = useDispatch();
  const getTechnologies = async () => {
    try {
      const token = document.cookie
        .split('; ')
        .find((row) => row.startsWith('jwt='))
        ?.split('=')[1];

      const response = await axios(
        `http://localhost:8080/api/v1/technologies/active?active=true`,
        {
          headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${token}`,
          },
          method: 'GET',
        }
      );
      dispatch(setTechnologies(response.data));
    } catch (error) {
      console.log(error);
    }
  };

  return getTechnologies;
};

export default useGetTechnologiesRequest;
