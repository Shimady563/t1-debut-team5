import { Dropdown } from 'react-bootstrap';
import './DropDown.scss';
import clsx from 'clsx';
// import {
//   setDropdownValueId,
//   setDropdownValueName,
// } from "../../store/filtersSlices"

export type DropDownProps = {
  options: any;
  title?: string;
  className?: string;
  handleSelect: (id: number) => void;
};

const DropDown: React.FC<DropDownProps> = ({
  options,
  title,
  handleSelect,
  className,
}) => {
  return (
    <Dropdown className={clsx('dropdown', className)}>
      <Dropdown.Toggle className="dropdown__toggle">{title}</Dropdown.Toggle>

      <Dropdown.Menu className="dropdown__menu">
        {options.map((option: any) => (
          <Dropdown.Item
            onClick={() => handleSelect(option.id)}
            key={option.id}
          >
            {option.label}
          </Dropdown.Item>
        ))}
      </Dropdown.Menu>
    </Dropdown>
  );
};

export default DropDown;
