import { useEffect, useState } from 'react';
import './TechnologyEditMenu.scss';
import TechnologyTypeSelector from '../TechologyTypeSelector/TechnologyTypeSelector';
import SelectedTypeTechnologies from '../SelectedTypeTechnologies/SelectedTypeTechnologies';
import TechnologyForm from '../TechnologyForm/TechnologyForm';
import useGetAllTechnologiesRequest from '@/globalApi/getAllTechnologiesRequest';

import { useChangeTechnology } from '../../api/changeTechnologyRequest';
import { useDeleteTechnology } from '../../api/deleteTechnologyRequest';

const TechnologyEditMenu = () => {
  const [selectedType, setSelectedType] = useState<number>(0);
  const [selectedTechnology, setSelectedTechnology] = useState<number>(-1);

  const getAllTechnologies = useGetAllTechnologiesRequest();
  const changeTechnology = useChangeTechnology();
  const deleteTechnology = useDeleteTechnology();

  const handleDeleteTechnology = (techId: number) => {
    deleteTechnology(techId);
    setSelectedTechnology(-1);
  };

  useEffect(() => {
    getAllTechnologies();
  }, []);

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
        {selectedTechnology != -1 && (
          <TechnologyForm
            onDelete={handleDeleteTechnology}
            onUpdate={changeTechnology}
            technologyId={selectedTechnology}
          />
        )}
      </div>
    </div>
  );
};

export default TechnologyEditMenu;
