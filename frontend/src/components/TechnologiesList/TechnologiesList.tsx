import React from 'react';
import {
  useActiveTechnologies,
  useFilteredActiveTechnologies,
} from '@/store/TechnologiesStore';
import { groupTechnologies } from './helpers/TechnologiesGrouping';
import './TechnologiesList.scss';
import { mockTypes } from '@/globalConsts';
import { TTechnology } from '@/types';

type TechnologiesListProps = {
  type: number;
  isTechsPassed?: boolean;
  passedTechs?: TTechnology[];
};

const TechnologiesList: React.FC<TechnologiesListProps> = ({
  type,
  isTechsPassed,
  passedTechs,
}) => {
  const technologies =
    type == -1 ? useActiveTechnologies() : useFilteredActiveTechnologies(type);
  const groupedTechnologies = isTechsPassed
    ? passedTechs && groupTechnologies(passedTechs)
    : groupTechnologies(technologies);

  return (
    <div className="tech-list">
      {groupedTechnologies &&
        Object.entries(groupedTechnologies).map(([key, techs]) => (
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
