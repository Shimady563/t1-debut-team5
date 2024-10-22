import { Dropdown } from 'react-bootstrap';
import { useDispatch } from 'react-redux';
import './DropDown.scss';
// import {
//   setDropdownValueId,
//   setDropdownValueName,
// } from "../../store/filtersSlices"

export type DropDownProps = {
  options: any;
  title?: string;
  handleSelect: (id: number) => void;
};

const DropDown: React.FC<DropDownProps> = ({
  options,
  title,
  handleSelect,
}) => {
  return (
    <Dropdown className="dropdown">
      <Dropdown.Toggle className="dropdown__toggle">{title}</Dropdown.Toggle>

      <Dropdown.Menu className="dropdown__menu">
        {options.map((option) => (
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
