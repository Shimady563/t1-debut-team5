import './PollsPage.scss';
import PollsList from '@/modules/PollsList/components/PollsList';

const PollsPage = () => {
  return (
    <div className="polls-page">
      <PollsList />
    </div>
  );
};

export default PollsPage;
