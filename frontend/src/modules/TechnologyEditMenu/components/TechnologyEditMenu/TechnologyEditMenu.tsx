import React, { useEffect, useState } from 'react';
import './TechnologyEditMenu.scss';
import TechnologyTypeSelector from '../TechologyTypeSelector/TechnologyTypeSelector';
import SelectedTypeTechnologies from '../SelectedTypeTechnologies/SelectedTypeTechnologies';
import TechnologyForm from '../TechnologyForm/TechnologyForm';
import useGetAllTechnologiesRequest from '@/globalApi/getAllTechnologiesRequest';
import { useTechnologies } from '@/store/TechnologiesStore';
import axios from 'axios';

const TechnologyEditMenu = () => {
  const [selectedType, setSelectedType] = useState<number>(0);
  const [selectedTechnology, setSelectedTechnology] = useState<number>(-1);

  const getAllTechnologies = useGetAllTechnologiesRequest();

  useEffect(() => {
    getAllTechnologies();
  }, []);

  const onTechnologyUpdateClick = async (data: any, id: number) => {
    try {
      const token = document.cookie
        .split('; ')
        .find((row) => row.startsWith('jwt='))
        ?.split('=')[1];
      const response = await axios(
        `http://localhost:8080/api/v1/technologies/${id}`,
        {
          headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${token}`,
          },
          method: 'PUT',
          data: data,
        }
      );
      console.log(response.data);
      getAllTechnologies();
    } catch {
      console.log('put error');
    }
  };

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
            onUpdate={onTechnologyUpdateClick}
            technologyId={selectedTechnology}
          />
        )}
      </div>
    </div>
  );
};

export default TechnologyEditMenu;
