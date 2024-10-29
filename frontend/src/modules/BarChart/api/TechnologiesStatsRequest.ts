import { TTechnology } from '@/types';
import axios from 'axios';
import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';

type TechStat = TTechnology & {
  score: number;
};

export const useGetTechnologiesStats = () => {
  const [stats, setStats] = useState<TechStat[]>([]);

  const TechnologyStatsRequest = async () => {
    try {
      const token = document.cookie
        .split('; ')
        .find((row) => row.startsWith('jwt='))
        ?.split('=')[1];
      const response = await axios(
        `http://localhost:8080/api/v1/technologies/usage-stats`,
        {
          headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${token}`,
          },
          method: 'GET',
        }
      );

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
