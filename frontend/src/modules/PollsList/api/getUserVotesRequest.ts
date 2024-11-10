import apiClient from '@/globalApi/apiClient';
import { TUserVoteResponse } from '@/types';
import { getTokenFromCookie } from '@/utils/tokenGetter';
import { useEffect, useState } from 'react';
import { toast } from 'react-toastify';

export const useUserVotes = () => {
  const [userVotes, setUserVotes] = useState<TUserVoteResponse[]>([]);

  const UserTechnologiesRequest = async () => {
    try {
      const token = getTokenFromCookie();

      const response = await apiClient.get('votes/user', {
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${token}`,
        },
      });

      setUserVotes(response.data);
    } catch (error) {
      toast.error('Упс...Что-то пошло не так');
    }
  };

  useEffect(() => {
    UserTechnologiesRequest();
  }, []);

  const refreshVotes = () => {
    UserTechnologiesRequest();
  };

  return { userVotes, refreshVotes };
};
