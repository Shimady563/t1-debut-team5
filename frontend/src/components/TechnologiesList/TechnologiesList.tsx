import React from 'react';
import {
  useAllTechnologies,
  useFilteredTechnologies,
} from '@/store/TechnologiesStore';
import { groupTechnologies } from './helpers/TechnologiesGrouping';
import './TechnologiesList.scss';
import { mockTypes } from '@/globalConsts';

type TechnologiesListProps = {
  type: number;
};

const TechnologiesList: React.FC<TechnologiesListProps> = ({ type }) => {
  const technologies =
    type == -1 ? useAllTechnologies() : useFilteredTechnologies(type);
  const groupedTechnologies = groupTechnologies(technologies);

  return (
    <div className="tech-list">
      {Object.entries(groupedTechnologies).map(([key, techs]) => (
        <div key={key} className="tech-list__group">
          <h3 className="tech-list__group_title">
            {mockTypes[parseInt(key)].label}
          </h3>
          <ul className="tech-list__group_list" key={key}>
            {techs.map((tech) => (
              <li key={tech.id} className="tech-list__group_list_item">
                {tech.name}
              </li>
            ))}
          </ul>
        </div>
      ))}
    </div>
  );
};

export default TechnologiesList;
