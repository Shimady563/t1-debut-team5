import { TTechnology } from '@/types';
import { useEffect, useState } from 'react';
import { useGetTechnologiesStats } from '../../api/TechnologiesStatsRequest';
import { Bar } from 'react-chartjs-2';
import 'chart.js/auto';
import './BarChart.scss';

type TechStat = TTechnology & {
  score: number;
};

const BarChart = () => {
  const statsa = useGetTechnologiesStats();

  const [stats, setStats] = useState<TechStat[]>(statsa);

  useEffect(() => {
    setStats(statsa);
  }, [statsa]);

  return (
    <div className="bar-chart">
      <h1 className="bar-chart__title">
        Статистика по использованию технологий
      </h1>
      <div className="bar-chart__content">
        <Bar
          data={{
            labels: stats.map((stat) => stat.name),
            datasets: [
              {
                label: 'Кол-во использований',
                data: stats.map((stat) => stat.score),
                backgroundColor: '#6c63ff',
              },
            ],
          }}
        />
      </div>
    </div>
  );
};

export default BarChart;
