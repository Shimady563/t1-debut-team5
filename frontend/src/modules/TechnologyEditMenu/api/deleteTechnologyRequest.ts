import apiClient from '@/globalApi/apiClient';
import useGetAllTechnologiesRequest from '@/globalApi/getAllTechnologiesRequest';
import { getTokenFromCookie } from '@/utils/tokenGetter';
import { toast } from 'react-toastify';

/**
 * Возвращает delete-request на удаление существующей технологии.
 */
export const useDeleteTechnology = () => {
  const getAllTechnologies = useGetAllTechnologiesRequest();
  const TechnologyDeleteRequest = async (id: number) => {
    try {
      const token = getTokenFromCookie();

      await apiClient.delete(`technologies/${id}`, {
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${token}`,
        },
      });

      getAllTechnologies();
    } catch {
      toast.error('Упс...Что-то пошло не так');
    }
  };

  return TechnologyDeleteRequest;
};
