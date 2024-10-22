import React, { useState } from 'react';
import './TechnologyEditMenu.scss';
import TechnologyTypeSelector from '../TechologyTypeSelector/TechnologyTypeSelector';
import SelectedTypeTechnologies from '../SelectedTypeTechnologies/SelectedTypeTechnologies';
import TechnologyForm from '../TechnologyForm/TechnologyForm';

const TechnologyEditMenu = () => {
  const [selectedType, setSelectedType] = useState<number>(0);
  const [selectedTechnology, setSelectedTechnology] = useState<number>(-1);

  return (
    <div className="menu">
      <h1 className="menu__title">Управление списоком технологий</h1>
      <div className="menu__type-nav">
        <TechnologyTypeSelector
          selectedType={selectedType}
          onTechnologyClick={setSelectedType}
        />
      </div>

      <div className="menu__content">
        <SelectedTypeTechnologies
          tech={selectedTechnology}
          selectTechnology={setSelectedTechnology}
          type={selectedType}
        />
        <TechnologyForm technologyId={selectedTechnology} />
      </div>
    </div>
  );
};

export default TechnologyEditMenu;
