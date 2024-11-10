import { TTechnology, TUserVoteResponse } from '@/types';

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
