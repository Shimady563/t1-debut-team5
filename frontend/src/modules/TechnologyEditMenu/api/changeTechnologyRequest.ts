import useGetAllTechnologiesRequest from '@/globalApi/getAllTechnologiesRequest';
import axios from 'axios';
import { toast } from 'react-toastify';

export const useChangeTechnology = () => {
  const getAllTechnologies = useGetAllTechnologiesRequest();
  const TechnologyEditRequest = async (data: any, id: number) => {
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
          method: 'PUT',
          data: data,
        }
      );
      console.log(response.data);
      toast.success('Данные обновлены!');
      getAllTechnologies();
    } catch {
      toast.error('Упс...Что-то пошло не так');
    }
  };

  return TechnologyEditRequest;
};
