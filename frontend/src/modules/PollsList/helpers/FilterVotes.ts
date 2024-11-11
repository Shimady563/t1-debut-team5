import { TTechnology, TUserVoteResponse } from '@/types';

/**
 * Проверяет за какие технологии уже голсовал пользователь, и возвращает список технологий с соответсвующими полями isVoted и level
 *
 */
export const updateUserVotes = (
  votes: TUserVoteResponse[],
  technology: TTechnology[]
) => {
  return technology.map((tech) => {
    const matchingTech = votes.find((vote) => vote.technology.id === tech.id);
    return {
      technology: {
        ...tech,
      },
      isVoted: !!matchingTech,
      level: matchingTech?.level || '',
    };
  });
};
