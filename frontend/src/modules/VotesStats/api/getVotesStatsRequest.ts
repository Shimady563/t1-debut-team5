import apiClient from '@/globalApi/apiClient';
import { getTokenFromCookie } from '@/utils/tokenGetter';
import { useEffect, useState } from 'react';
import { toast } from 'react-toastify';

/**
 * Выполняет get-request на получение статистики по голосам.
 */
export const useVotesStats = () => {
  const [stats, setStats] = useState<any>([]);

  const VotesStatsRequest = async () => {
    try {
      const token = getTokenFromCookie();

      const response = await apiClient.get('technologies/vote-stats', {
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
    VotesStatsRequest();
  }, []);

  return stats;
};
