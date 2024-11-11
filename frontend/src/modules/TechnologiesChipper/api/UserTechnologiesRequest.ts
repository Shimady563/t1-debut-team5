import apiClient from '@/globalApi/apiClient';
import { getTokenFromCookie } from '@/utils/tokenGetter';
import { useEffect, useState } from 'react';
import { toast } from 'react-toastify';

/**
 * Выполняет get-request на получение стека пользователя. Возвращает список технологий в стеке пользователя.
 */
export const useUserTechnologies = () => {
  const [userTechnologies, setUserTechnologies] = useState<any>([]);

  const UserTechnologiesRequest = async () => {
    try {
      const token = getTokenFromCookie();

      const response = await apiClient.get('users/technology', {
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${token}`,
        },
      });

      setUserTechnologies(response.data.technologies);
    } catch {
      toast.success('Упс...Что-то пошло не так');
    }
  };

  useEffect(() => {
    UserTechnologiesRequest();
  }, []);

  return userTechnologies;
};
