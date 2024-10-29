import { useState } from 'react';
import './TechnologyCreateForm.scss';
import Input from '@/ui/Input/Input';
import DropDown from '@/ui/DropDown/DropDown';
import { mockLevels, mockTypes } from '@/globalConsts';
import Button from '@/ui/Button/Button';
import { useCreateTechnology } from '../../api/createTechnologyRequest';
import { toast } from 'react-toastify';

const TechnologyCreateForm = () => {
  const [level, setLevel] = useState<string>('Adopt');
  const [name, setName] = useState<string>();
  const [type, setType] = useState<string>('Platforms');

  const createTechnology = useCreateTechnology();

  const handleNameChange = (value: string) => {
    setName(value);
  };

  const handleCreateTechnologyClick = () => {
    if (!name) {
      toast.error('Поле не должно быть пустым');
      return;
    }
    const data = {
      name,
      level: level.toUpperCase(),
      type: type.toUpperCase(),
    };
    createTechnology(data);
  };

  const handleLevelSelect = (id: number) => {
    const selectedLevel = mockLevels.find((el) => el.id === id);
    selectedLevel?.label != undefined && setLevel(selectedLevel?.label);
  };

  const handleTypeSelect = (id: number) => {
    const selectedType = mockTypes.find((el) => el.id === id);
    selectedType?.id != undefined && setType(mockTypes[selectedType?.id].label);
  };

  return (
    <div className="create-form">
      <div className="create-form__title">Добавление технологии</div>
      <div className="create-form__content">
        <div className="create-form__content_item">
          <span className="edit-form__content_item_title">Название</span>
          <Input
            value={name}
            onChangeValue={handleNameChange}
            sizing="medium"
            placeholder="Введите название..."
          />
        </div>
        <div className="create-form__content_item">
          <span className="edit-form__content_item_title">Уровень</span>
          <DropDown
            handleSelect={handleLevelSelect}
            title={level}
            options={mockLevels}
          />
        </div>
        <div className="create-form__content_item">
          <span className="edit-form__content_item_title">Категория</span>
          <DropDown
            handleSelect={handleTypeSelect}
            title={type?.toString()}
            options={mockTypes}
          />
        </div>
      </div>
      <div className="create-form__submit">
        <Button onClick={() => handleCreateTechnologyClick()} size="medium">
          Добавить технологию
        </Button>
      </div>
    </div>
  );
};

export default TechnologyCreateForm;
