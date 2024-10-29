import apiClient from '@/globalApi/apiClient';
import { getTokenFromCookie } from '@/utils/tokenGetter';
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';

export const useCreateTechnology = () => {
  const navigate = useNavigate();
  const TechnologyCreateRequest = async (data: any) => {
    try {
      const token = getTokenFromCookie();

      await apiClient.post('technologies', data, {
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${token}`,
        },
      });

      toast.success('Технология добавлена!');
      navigate('/radar');
    } catch {
      toast.success('Упс...Что-то пошло не так');
    }
  };

  return TechnologyCreateRequest;
};
