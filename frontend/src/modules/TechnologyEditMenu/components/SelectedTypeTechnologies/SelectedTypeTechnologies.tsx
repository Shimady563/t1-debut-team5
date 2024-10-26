import React from 'react';
import './SelectedTypeTechnologies.scss';
import { useFilteredTechnologies } from '@/store/TechnologiesStore';
import clsx from 'clsx';

type SelectedTypeTechnologiesProps = {
  type: number;
  selectTechnology: (a: number) => void;
  tech: number;
};

const SelectedTypeTechnologies: React.FC<SelectedTypeTechnologiesProps> = ({
  type,
  selectTechnology,
  tech,
}) => {
  const techsList = useFilteredTechnologies(type);

  return (
    <ul className="technologies-list">
      {techsList.map((el) => (
        <li
          className={clsx(
            'technologies-list__item',
            tech == el.id ? 'active' : ''
          )}
          onClick={() => selectTechnology(el.id)}
          key={el.id}
        >
          {el.name}
        </li>
      ))}
    </ul>
  );
};

export default SelectedTypeTechnologies;
