import React, { useEffect, useState } from 'react';
import './TechnologyForm.scss';
import { useTechnologyById } from '@/store/TechnologiesStore';
import DropDown from '@/ui/DropDown/DropDown';
import { mockLevels, mockStatuses, mockTypes } from '@/globalConsts';
import Input from '@/ui/Input/Input';
import { Toggle } from '@admiral-ds/react-ui';
import Button from '@/ui/Button/Button';
import { toast } from 'react-toastify';
import { statusToName } from '../../helpers/intStatusToName';

type TechnologyFormProps = {
  technologyId: number;
  onUpdate: (data: any, id: number) => void;
  onDelete: (id: number) => void;
};

const TechnologyForm: React.FC<TechnologyFormProps> = ({
  technologyId,
  onUpdate,
  onDelete,
}) => {
  const [level, setLevel] = useState<string>();
  const [name, setName] = useState<string>();
  const [type, setType] = useState<string>();
  const [state, setState] = useState<boolean>();
  const [status, setStatus] = useState<number>();

  const currentTech = useTechnologyById(technologyId);

  useEffect(() => {
    setLevel(currentTech?.level);
    setName(currentTech?.name);
    currentTech?.type != undefined &&
      setType(mockTypes[currentTech?.type].label);
    setState(currentTech?.isActive);
    setStatus(currentTech?.moved);
  }, [currentTech]);

  const onUpdateClick = async () => {
    if (name == '') {
      toast.error('Поле не должно быть пустым!');
      return;
    }

    const data = {
      name: name,
      moved: status,
      level: level?.toUpperCase(),
      type: type?.toUpperCase(),
      isActive: state,
    };
    currentTech && onUpdate(data, currentTech?.id);
  };

  const onDeleteClick = async () => {
    currentTech && onDelete(currentTech?.id);
  };

  const handleLevelSelect = (id: number) => {
    const selectedLevel = mockLevels.find((el) => el.id === id);
    setLevel(selectedLevel?.label);
  };

  const handleTypeSelect = (id: number) => {
    const selectedType = mockTypes.find((el) => el.id === id);
    selectedType?.id != undefined && setType(mockTypes[selectedType?.id].label);
  };

  const handleStatusSelect = (id: number) => {
    setStatus(id);
  };
  const handleNameChange = (value: string) => {
    setName(value);
  };

  return (
    <div className="edit-form">
      <div className="edit-form__content">
        <div className="edit-form__content_item">
          <span className="edit-form__content_item_title">Название</span>
          <Input
            value={name}
            onChangeValue={handleNameChange}
            sizing="medium"
          />
        </div>
        <div className="edit-form__content_item">
          <span className="edit-form__content_item_title">Уровень</span>
          <DropDown
            handleSelect={handleLevelSelect}
            title={level}
            options={mockLevels}
          />
        </div>
        <div className="edit-form__content_item">
          <span className="edit-form__content_item_title">Категория</span>
          <DropDown
            handleSelect={handleTypeSelect}
            title={type?.toString()}
            options={mockTypes}
          />
        </div>
        <div className="edit-form__content_item">
          <span className="edit-form__content_item_title">Актуальность</span>
          <div className="toggler">
            <Toggle
              dimension="m"
              onChange={(event) => setState(event.currentTarget.checked)}
              checked={state}
            />
            <span className="toggler__text">
              {state ? 'Активна' : 'Архивирована'}
            </span>
          </div>
        </div>
        <div className="edit-form__content_item">
          <span className="edit-form__content_item_title">Статус</span>
          <DropDown
            handleSelect={handleStatusSelect}
            title={statusToName(status)}
            options={mockStatuses}
          />
        </div>
      </div>
      <div className="edit-form__actions">
        <Button onClick={() => onUpdateClick()} size="medium">
          Сохранить
        </Button>
        <Button
          onClick={() => onDeleteClick()}
          style={{ backgroundColor: 'red' }}
          size="medium"
        >
          Удалить технологию{' '}
        </Button>
      </div>
    </div>
  );
};

export default TechnologyForm;
