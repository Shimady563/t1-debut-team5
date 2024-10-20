import React from 'react';
import './Header.scss';
import { NavLink, useLocation } from 'react-router-dom';

const Header = () => {
  return (
    <div className="header">
      <div className="header__container">
        <div className="header__logo">
          <span>TechRadar by Team5</span>
        </div>
        <ul className="header__nav">
          <li>
            <NavLink to="">Управление</NavLink>
          </li>
          <li>
            <NavLink to="/login">Опросы</NavLink>
          </li>
          <li>
            <NavLink to="reg">Голосование</NavLink>
          </li>
        </ul>
      </div>
    </div>
  );
};

export default Header;
