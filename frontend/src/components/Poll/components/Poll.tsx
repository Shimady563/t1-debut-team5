import React, { useEffect, useState } from 'react';
import './Poll.scss';
import { TLevel, TPoll } from '@/types';
import clsx from 'clsx';
import Button from '@/ui/Button/Button';

type PollProps = {
  poll: TPoll;
  options: TLevel[];
  onVote: (techId: number, level: string) => void;
};

const Poll: React.FC<PollProps> = ({ options, poll, onVote }) => {
  const [selectedVote, setSelectedVote] = useState<string>(poll.level);

  const handleOptionSelect = (option: string) => {
    selectedVote == option ? setSelectedVote('') : setSelectedVote(option);
  };

  useEffect(() => {
    setSelectedVote(poll.level);
  }, [poll.level]);

  return (
    <div className="poll">
      <span className="poll__title">{`Уровень технологии ${poll.technology.name}`}</span>
      <div className="poll__content">
        {options.map((option, i) => (
          <div
            key={i}
            onClick={() =>
              !poll.isVoted ? handleOptionSelect(option.label) : () => {}
            }
            className={clsx(
              'poll__content_option',
              selectedVote == option.label ? 'voted' : ''
            )}
          >
            {option.label}
          </div>
        ))}
      </div>
      {!poll.isVoted && (
        <div className="poll__submit">
          <Button
            disabled={selectedVote == '' ? true : false}
            onClick={() => onVote(poll.technology.id, selectedVote)}
            className={clsx(
              'poll__submit_btn',
              selectedVote == '' ? 'disable' : ''
            )}
            size="small"
          >
            Проголосовать
          </Button>
        </div>
      )}
    </div>
  );
};

export default Poll;
