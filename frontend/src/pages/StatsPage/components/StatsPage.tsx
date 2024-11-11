import BarChart from '@/modules/BarChart/components/BarChart/BarChart';
import './StatsPage.scss';
import VotesStats from '@/modules/VotesStats/components/VotesStats';

const StatsPage = () => {
  return (
    <div className="stats-page">
      <BarChart />
      <VotesStats />
    </div>
  );
};

export default StatsPage;
