import React, { useState } from 'react';
import './Poll.scss';
import { TLevel } from '@/types';
import clsx from 'clsx';

type PollProps = {
  title: string;
  technologyId: number;
  options: TLevel[];
  isVoted: boolean;
  votedLevel: string;
  onVote?: (id: number) => void;
};

const Poll: React.FC<PollProps> = ({
  title,
  technologyId,
  options,
  isVoted,
  votedLevel,
  onVote,
}) => {
  const [currentVote, setCurrentVote] = useState<string>(votedLevel);

  const handleOptionVote = (option: string) => {
    console.log('aaa');
    setCurrentVote(option);
  };

  return (
    <div className="poll">
      <span className="poll__title">{title}</span>
      <div className="poll__content">
        {options.map((option, i) => (
          <div
            key={i}
            onClick={() =>
              !isVoted ? handleOptionVote(option.label) : () => {}
            }
            className={clsx(
              'poll__content_option',
              currentVote == option.label ? 'voted' : ''
            )}
          >
            {option.label}
          </div>
        ))}
      </div>
    </div>
  );
};

export default Poll;
