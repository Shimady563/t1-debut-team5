import apiClient from '@/globalApi/apiClient';
import useGetAllTechnologiesRequest from '@/globalApi/getAllTechnologiesRequest';
import { getTokenFromCookie } from '@/utils/tokenGetter';

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
      console.log('delete error');
    }
  };

  return TechnologyDeleteRequest;
};
