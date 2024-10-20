import React, { useCallback } from 'react';
import './Input.scss';
import clsx from 'clsx';

export type InputProps = Omit<
  React.InputHTMLAttributes<HTMLInputElement>,
  'onChange' | 'value'
> & {
  placeholder?: string;
  value?: string;
  onChangeValue?: (value: string) => void;
  searchValue?: string;
  className?: string;
  sizing: 'big' | 'medium' | 'small';
};

const Input: React.FC<InputProps> = React.forwardRef(
  (
    { onChangeValue, searchValue, className, value, placeholder, sizing, id },
    forwardedRef
  ) => {
    const onUpdateSearch = useCallback((str: string) => {
      //onChangeValue(str);
    }, []);

    const onChangeInput = (event: React.ChangeEvent<HTMLInputElement>) => {
      onUpdateSearch(event.target.value);
    };

    return (
      <div id={id} className={clsx('input', sizing)}>
        <input
          value={value || searchValue}
          onChange={onChangeInput}
          className={clsx('input__block', className)}
          type="text"
          placeholder={placeholder ? `${placeholder}` : 'Начните ввод...'}
        />
      </div>
    );
  }
);

export default Input;
