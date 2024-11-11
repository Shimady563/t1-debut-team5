import './VotesStats.scss';
import { useVotesStats } from '../api/getVotesStatsRequest';
import PieChart from '@/components/PieChart/PieChart';
import { TVoteStat } from '@/types';

const VotesStats = () => {
  const votesStats = useVotesStats();

  return (
    <div className="votes-stats-block">
      <h1 className="votes-stats-block__title">Статистика по голосованиям</h1>
      <div className="votes-stats-block__content">
        {votesStats.map(
          (stat: TVoteStat) =>
            Object.entries(stat.votes).length !== 0 && <PieChart stat={stat} />
        )}
      </div>
    </div>
  );
};

export default VotesStats;
