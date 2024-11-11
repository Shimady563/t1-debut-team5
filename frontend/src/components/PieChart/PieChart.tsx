import React from 'react';
import './PieChart.scss';
import { TVoteStat } from '@/types';
import { Pie } from 'react-chartjs-2';

type PieChartProps = {
  stat: TVoteStat;
};

const PieChart: React.FC<PieChartProps> = ({ stat }) => {
  const data = {
    labels: ['Assess', 'Hold', 'Adopt', 'Trial'],
    datasets: [
      {
        data: [
          stat.votes.Assess || 0,
          stat.votes.Hold || 0,
          stat.votes.Adopt || 0,
          stat.votes.Trial || 0,
        ],
        backgroundColor: [
          'rgba(108, 99, 255, 0.8)',
          'rgba(54, 162, 235, 0.8)',
          'rgba(255, 206, 86, 0.8)',
          'rgba(75, 192, 192, 0.8)',
        ],
      },
    ],
  };

  const options = {
    responsive: true,
    plugins: {
      legend: {
        position: 'top' as const,
      },
      title: {
        display: true,
        text: `Голоса по технологии ${stat.technology.name}`,
      },
    },
  };

  return (
    <div className="pie-chart">
      <Pie data={data} options={options} />
    </div>
  );
};

export default PieChart;
