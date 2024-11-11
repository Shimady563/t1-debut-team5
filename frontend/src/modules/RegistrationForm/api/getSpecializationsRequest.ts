import apiClient from '@/globalApi/apiClient';
import { TSpecialization } from '@/types';
// import { getTokenFromCookie } from '@/utils/tokenGetter';
import { useEffect, useState } from 'react';
// import { toast } from 'react-toastify';

/**
 * Выполняет get-request на получение стека пользователя. Возвращает список технологий в стеке пользователя.
 */
export const useSpecializations = () => {
  const [specializations, setSpecializations] = useState<TSpecialization[]>([]);

  const getSpecializationsRequest = async () => {
    try {
      const response = await apiClient.get('api/v1/specializations', {
        headers: {
          'Content-Type': 'application/json',
          //   Authorization: `Bearer ${token}`,
        },
      });

      setSpecializations(response.data);
    } catch {
      //   toast.success('Упс...Что-то пошло не так');
    }
  };

  useEffect(() => {
    getSpecializationsRequest();
  }, []);

  return specializations;
};
