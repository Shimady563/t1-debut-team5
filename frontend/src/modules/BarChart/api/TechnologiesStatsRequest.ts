import apiClient from '@/globalApi/apiClient';
import { TTechnology } from '@/types';
import { getTokenFromCookie } from '@/utils/tokenGetter';
import { useEffect, useState } from 'react';
import { toast } from 'react-toastify';

type TechStat = TTechnology & {
  score: number;
};

/**
 * Выполняет запрос за статистикой по технологиям. Возвращает статистику
 */
export const useGetTechnologiesStats = () => {
  const [stats, setStats] = useState<TechStat[]>([]);

  const TechnologyStatsRequest = async () => {
    try {
      const token = getTokenFromCookie();
      const response = await apiClient.get('technologies/usage-stats', {
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${token}`,
        },
      });

      setStats(response.data);
    } catch {
      toast.success('Упс...Что-то пошло не так');
    }
  };

  useEffect(() => {
    TechnologyStatsRequest();
  }, []);

  return stats;
};
