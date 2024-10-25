import React, { useEffect } from 'react';
import './TechnologiesChipper.scss';
import { Chips } from '@admiral-ds/react-ui';
import { useUserTechnologies } from '../api/UserTechnologiesRequest';
import { useAllTechnologies } from '@/store/TechnologiesStore';
import { updateSelectedStatus } from '../helpers/updateSelectesStatus';
import { TTechnology } from '@/types';
import { getSelectedIds } from '../helpers/getSelectedTechnologiesIds';
import Button from '@/ui/Button/Button';
import clsx from 'clsx';
import { useUpdateUserTechnologies } from '../api/UpdateUserTechnologies';

type TechnologiesChipperProps = {
  onSubmit: () => void;
};

const TechnologiesChipper: React.FC<TechnologiesChipperProps> = ({
  onSubmit,
}) => {
  const userTechnologies = useUserTechnologies();
  const allServiceTechnologies = useAllTechnologies();

  const [allTechnologies, setAllTechnologies] = React.useState<any>([]);
  const updateUserTechnologies = useUpdateUserTechnologies();

  const handleKeyM = (id: number) => {
    setAllTechnologies((prev: TTechnology[]) =>
      prev.map((item: any) =>
        item.id === id ? { ...item, selected: !item.selected } : { ...item }
      )
    );
  };

  const onClick = () => {
    updateUserTechnologies(getSelectedIds(allTechnologies));
    onSubmit();
  };

  useEffect(() => {
    setAllTechnologies(
      updateSelectedStatus(allServiceTechnologies, userTechnologies)
    );
  }, [userTechnologies]);

  return (
    <div className="chipper">
      <h2 className="chipper__title">Выберите Ваши технологии</h2>
      <div className="chipper__content">
        {allTechnologies.map((el: any) => (
          <Chips
            key={el.id}
            className={clsx('chipper_el', el.selected ? 'selected' : '')}
            onClick={handleKeyM.bind(null, el.id)}
            selected={el.selected}
          >
            <div className="chipper__content_item">{el.name}</div>
          </Chips>
        ))}
      </div>
      <div onClick={() => onClick()} className="chipper__submit">
        <Button size="medium">Сохранить</Button>
      </div>
    </div>
  );
};

export default TechnologiesChipper;
