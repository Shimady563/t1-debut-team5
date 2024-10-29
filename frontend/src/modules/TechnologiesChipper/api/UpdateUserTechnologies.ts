import apiClient from '@/globalApi/apiClient';
import { getTokenFromCookie } from '@/utils/tokenGetter';
import { toast } from 'react-toastify';

export const useUpdateUserTechnologies = () => {
  const UserTechnologiesUpdateRequest = async (data: number[]) => {
    try {
      const token = getTokenFromCookie();
      await apiClient.put('users/technology', data, {
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${token}`,
        },
      });
      toast.success('Ваш стек успешно изменён!');
    } catch {
      toast.error('Упс...Что-то пошло не так');
    }
  };

  return UserTechnologiesUpdateRequest;
};
