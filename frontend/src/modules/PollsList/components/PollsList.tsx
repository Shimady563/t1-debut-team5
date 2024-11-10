import { useEffect, useState } from 'react';
import './PollsList.scss';
import Poll from '@/components/Poll/components/Poll';
import { useAllTechnologies } from '@/store/TechnologiesStore';
import { useUserVotes } from '../api/getUserVotesRequest';
import { updateUserVotes } from '../helpers/FilterVotes';
import { TPoll } from '@/types';
import { useVoteRequest } from '../api/voteRequest';
import { mockLevels } from '@/globalConsts';

const PollsList = () => {
  const techs = useAllTechnologies();
  const { userVotes, refreshVotes } = useUserVotes();
  const [polls, setPolls] = useState<TPoll[]>([]);
  const voteRequest = useVoteRequest();

  useEffect(() => {
    console.log('refresh');
    setPolls(updateUserVotes(userVotes, techs));
  }, [userVotes, techs]);

  const handlePollSubmit = async (techId: number, level: string) => {
    await voteRequest(level.toUpperCase(), techId);
    refreshVotes();
  };

  return (
    <div className="poll-list">
      {polls.map((poll, index) => (
        <Poll
          key={index}
          poll={poll}
          options={mockLevels}
          onVote={handlePollSubmit}
        />
      ))}
    </div>
  );
};

export default PollsList;
