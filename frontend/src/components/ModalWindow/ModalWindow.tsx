import React, { MouseEvent, MouseEventHandler } from 'react';
import './ModalWindow.scss';
import clsx from 'clsx';

export type ModalProps = React.ButtonHTMLAttributes<HTMLButtonElement> & {
  active: boolean;
  // setActive: (value: boolean) => void;
  handleBackdropClick: () => void;
  children: React.ReactNode;
  className?: string;
};

const ModalWindow: React.FC<ModalProps> = ({
  active,
  // setActive,
  children,
  className,
  handleBackdropClick,
}) => {
  const handleClick: MouseEventHandler<HTMLDivElement> = (
    e: MouseEvent<HTMLDivElement>
  ) => {
    e.stopPropagation();
  };

  React.useEffect(() => {
    if (active) {
      document.body.style.overflow = 'hidden';
    } else {
      document.body.style.overflow = 'auto';
    }
  }, [active]);

  return (
    <div
      onClick={handleBackdropClick}
      //   className={`${styles.modal} ${active === true ? styles.active : ''}`}
      className={clsx('modal', active ? 'active' : '')}
    >
      <div
        onClick={handleClick}
        className={
          active === false
            ? clsx('modal__content', className)
            : clsx('modal__content', 'active', className)
        }
      >
        {children}
      </div>
    </div>
  );
};

export default ModalWindow;
