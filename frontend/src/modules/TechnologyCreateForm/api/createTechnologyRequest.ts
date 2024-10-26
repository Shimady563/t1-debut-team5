import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';

export const useCreateTechnology = () => {
  const navigate = useNavigate();
  const TechnologyCreateRequest = async (data: any) => {
    try {
      const token = document.cookie
        .split('; ')
        .find((row) => row.startsWith('jwt='))
        ?.split('=')[1];
      const response = await axios(
        `http://localhost:8080/api/v1/technologies`,
        {
          headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${token}`,
          },
          method: 'POST',
          data: data,
        }
      );
      console.log(response);
      toast.success('Технология добавлена!');
      navigate('/radar');
    } catch {
      toast.success('Упс...Что-то пошло не так');
    }
  };

  return TechnologyCreateRequest;
};
