import React, { useEffect, useState } from 'react';
import { TTechnologiesList, TTechnology } from '@/types';
import {
  useTechnologies,
  useFilteredTechnologies,
} from '@/store/TechnologiesStore';
import { groupTechnologies } from './helpers/TechnologiesGrouping';
import './TechnologiesList.scss';
import { mockTypes } from '@/modules/Radar/consts';

type TechnologiesListProps = {
  type: number;
};

const TechnologiesList: React.FC<TechnologiesListProps> = ({ type }) => {
  const technologies =
    type == -1 ? useTechnologies() : useFilteredTechnologies(type);
  const groupedTechnologies = groupTechnologies(technologies);

  return (
    <div className="tech-list">
      {Object.entries(groupedTechnologies).map(([key, techs]) => (
        <div className="tech-list__group">
          <h3 className="tech-list__group_title">{mockTypes[key].label}</h3>
          <ul className="tech-list__group_list" key={key}>
            {techs.map((tech) => (
              <li className="tech-list__group_list_item" key={tech.id}>
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
