import React from 'react';
import './TechnologyTypeSelector.scss';
import { mockTypes } from '@/modules/Radar/consts';
import clsx from 'clsx';

type TechnologyTypeSelectorProps = {
  onTechnologyClick: (a: number) => void;
  selectedType: number;
};

const TechnologyTypeSelector: React.FC<TechnologyTypeSelectorProps> = ({
  onTechnologyClick,
  selectedType,
}) => {
  return (
    <ul className="selector">
      {mockTypes.map((el) => (
        <li
          className={clsx(
            'selector__item',
            el.id == selectedType ? 'active' : ''
          )}
          onClick={() => onTechnologyClick(el.id)}
          key={el.id}
        >
          {el.label}
        </li>
      ))}
    </ul>
  );
};

export default TechnologyTypeSelector;
