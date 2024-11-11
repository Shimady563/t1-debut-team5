import apiClient from '@/globalApi/apiClient';
import { TSpecialization } from '@/types';
import { useEffect, useState } from 'react';

/**
 * Выполняет get-request на получение существующих специализаций. Возвращает массив специализаций.
 */
export const useSpecializations = () => {
  const [specializations, setSpecializations] = useState<TSpecialization[]>([]);

  const getSpecializationsRequest = async () => {
    try {
      const response = await apiClient.get('specializations', {
        headers: {
          'Content-Type': 'application/json',
        },
      });
      // С бэкенда приходит специализация администратор системы первым элементом
      setSpecializations(response.data.slice(1));
    } catch {}
  };

  useEffect(() => {
    getSpecializationsRequest();
  }, []);

  return specializations;
};
