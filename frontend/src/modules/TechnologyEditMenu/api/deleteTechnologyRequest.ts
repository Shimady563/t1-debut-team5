import useGetAllTechnologiesRequest from '@/globalApi/getAllTechnologiesRequest';
import axios from 'axios';

export const useDeleteTechnology = () => {
  const getAllTechnologies = useGetAllTechnologiesRequest();
  const TechnologyDeleteRequest = async (id: number) => {
    try {
      const token = document.cookie
        .split('; ')
        .find((row) => row.startsWith('jwt='))
        ?.split('=')[1];
      const response = await axios(
        `http://localhost:8080/api/v1/technologies/${id}`,
        {
          headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${token}`,
          },
          method: 'DELETE',
        }
      );
      console.log(response.data);
      getAllTechnologies();
    } catch {
      console.log('delete error');
    }
  };

  return TechnologyDeleteRequest;
};
