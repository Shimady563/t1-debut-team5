import apiClient from '@/globalApi/apiClient';
import { getTokenFromCookie } from '@/utils/tokenGetter';
import { toast } from 'react-toastify';

export const useVoteRequest = () => {
  const voteRequest = async (level: string, technologyId: number) => {
    try {
      const token = getTokenFromCookie();
      await apiClient.post(
        'votes',
        { level, technologyId },
        {
          headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${token}`,
          },
        }
      );

      toast.success('Вы успешно проголосовали!');
    } catch (error: any) {
      toast.error('Упс...Что-то пошло не так');
    }
  };

  return voteRequest;
};
