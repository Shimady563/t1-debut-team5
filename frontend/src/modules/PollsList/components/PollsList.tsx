import React from 'react';
import './PollsList.scss';
import Poll from '@/components/Poll/components/Poll';

const mockLevels = [
  { id: 0, label: 'Adopt', slug: 'Adopt' },
  { id: 1, label: 'Trial', slug: 'Trial' },
  { id: 2, label: 'Assess', slug: 'Assess' },
  { id: 3, label: 'Hold', slug: 'Hold' },
];

const MockPolls = [
  {
    id: 1,
    technologyId: 1,
    technologyName: 'React',
    options: mockLevels,
    isVoted: false,
    voteChoise: '',
  },
  {
    id: 2,
    technologyId: 2,
    technologyName: 'Java',
    options: mockLevels,
    isVoted: true,
    voteChoise: 'Adopt',
  },
];

const PollsList = () => {
  return (
    <div className="poll-list">
      {MockPolls.map((poll) => (
        <Poll
          key={poll.id}
          title={`Уровень технологии ${poll.technologyName}`}
          technologyId={poll.technologyId}
          options={mockLevels}
          isVoted={poll.isVoted}
          votedLevel={poll.voteChoise}
        />
      ))}
    </div>
  );
};

export default PollsList;
