import Radar from '@/modules/Radar/components/Radar/Radar';
import './MainPage.scss';
import BarChart from '@/modules/BarChart/components/BarChart/BarChart';

const MainPage = () => {
  return (
    <div className="main-page">
      <Radar />
      {/* <BarChart /> */}
    </div>
  );
};

export default MainPage;
