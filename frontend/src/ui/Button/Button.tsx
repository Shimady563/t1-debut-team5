import React from 'react';
import './Button.scss';
import clsx from 'clsx';

export type ButtonProps = React.ButtonHTMLAttributes<HTMLButtonElement> & {
  children: React.ReactNode;
  className?: string;
  disabled?: boolean;
  size: 'big' | 'medium' | 'small';
};

const Button: React.FC<ButtonProps> = ({
  children,
  className,
  disabled,
  size,
  ...props
}) => {
  return (
    <button
      disabled={disabled}
      {...props}
      className={clsx('button', size, className)}
    >
      <p className="button__text">{children}</p>
    </button>
  );
};

export default Button;
