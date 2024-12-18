import './Header.scss';
import { NavLink, useLocation } from 'react-router-dom';
import { useUserInfo } from '@/store/UserSlice';
import Button from '@/ui/Button/Button';
import { useLogout } from './api/logoutRequest';

type HeaderProps = {
  onModalClick: () => void;
};

const Header: React.FC<HeaderProps> = ({ onModalClick }) => {
  const user = useUserInfo();
  const logout = useLogout();
  const location = useLocation();

  const onLogoutClick = () => {
    logout();
  };

  if (location.pathname === '/share') {
    return <></>;
  }

  return (
    <div className="header">
      <div className="header__container">
        <div className="header__logo">
          <span>TechRadar by Team5</span>
        </div>
        <ul className="header__nav">
          {user.isAuth && (
            <li>
              <NavLink to="/radar">Радар</NavLink>
            </li>
          )}

          {user.user?.admin && (
            <li>
              <NavLink to="/admin">Управление</NavLink>
            </li>
          )}
          {user.user?.admin && (
            <li>
              <NavLink to="/stats">Статистика</NavLink>
            </li>
          )}

          {!user.isAuth && (
            <li>
              <NavLink to="/login">Войти</NavLink>
            </li>
          )}
          {user.isAuth && (
            <li>
              <NavLink to="/polls">Голосование</NavLink>
            </li>
          )}
          {user.isAuth && (
            <li style={{ cursor: 'pointer' }} onClick={onModalClick}>
              Выбор стека
            </li>
          )}

          {user.isAuth && (
            <Button onClick={onLogoutClick} size="medium">
              Выйти
            </Button>
          )}
        </ul>
      </div>
    </div>
  );
};

export default Header;
