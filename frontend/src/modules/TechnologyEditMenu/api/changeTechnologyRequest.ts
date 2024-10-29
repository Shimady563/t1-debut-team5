import apiClient from '@/globalApi/apiClient';
import useGetAllTechnologiesRequest from '@/globalApi/getAllTechnologiesRequest';
import { getTokenFromCookie } from '@/utils/tokenGetter';
import { toast } from 'react-toastify';

export const useChangeTechnology = () => {
  const getAllTechnologies = useGetAllTechnologiesRequest();
  const TechnologyEditRequest = async (data: any, id: number) => {
    try {
      const token = getTokenFromCookie();

      await apiClient.put(`technologies/${id}`, data, {
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${token}`,
        },
      });

      toast.success('Данные обновлены!');
      getAllTechnologies();
    } catch {
      toast.error('Упс...Что-то пошло не так');
    }
  };

  return TechnologyEditRequest;
};
