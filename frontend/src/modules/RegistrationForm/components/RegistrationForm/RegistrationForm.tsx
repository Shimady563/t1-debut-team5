import React, { useRef } from 'react';
import './RegistrationForm.scss';
import Button from '@/ui/Button/Button';
import { useForm } from 'react-hook-form';
import { NavLink } from 'react-router-dom';

const RegistrationForm = () => {
  const regFormRef = useRef<HTMLFormElement>(null);

  const regForm = useForm({
    mode: 'onChange',
  });

  const { register, handleSubmit, watch, formState } = regForm;
  const { isValid, touchedFields, errors } = formState;

  const handleLoginSubmit = () => {
    console.log('aaaaa');
  };

  return (
    <div className="reg-form">
      <div className="reg-form__header">
        <h1 className="reg-form__header_title">Регистрация</h1>
        <span className="reg-form__header_subtitle">
          Установите логин и пароль, чтобы <br /> создать аккаунт
        </span>
      </div>
      {/* -------------------------------------------------------------- */}
      <form
        ref={regFormRef}
        onSubmit={handleSubmit(handleLoginSubmit)}
        className="reg-form__form"
      >
        <div
          className="reg-form__form_input"
          style={{ position: 'relative', width: `100%` }}
        >
          <input
            className="reg-form__form_input_block"
            {...register('regLogin', {
              required: 'Обязательное поле',
              pattern: {
                value: /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/,
                message: 'Введите корректный e-mail', //!!!
              },
            })}
            placeholder="Введите e-mail"
          />
          {errors?.regLogin && touchedFields.regLogin && (
            <div className="reg-form__form_input_message">
              {errors?.regLogin?.message?.toString()}
            </div>
          )}
        </div>
        <div
          className="reg-form__form_input"
          style={{ position: 'relative', width: `100%` }}
        >
          <input
            className="reg-form__form_input_block"
            {...register('regPassword', {
              required: 'Обязательное поле',
              minLength: {
                value: 8,
                message: 'Минимум 8 символов',
              },
            })}
            placeholder="Введите пароль"
            type="password"
          />
          {errors?.regPassword && touchedFields.regPassword && (
            <div className="reg-form__form_input_message">
              {errors?.regPassword?.message?.toString()}
            </div>
          )}
        </div>

        <div
          className="reg-form__form_input"
          style={{ position: 'relative', width: `100%` }}
        >
          <input
            className="reg-form__form_input_block"
            {...register('regConfirmPassword', {
              required: 'Обязательное поле',
              minLength: {
                value: 8,
                message: 'Минимум 8 символов',
              },
              validate: (value) => {
                const password = watch('regPassword');
                return password === value || 'Пароли не совпадают';
              },
            })}
            placeholder="Повторите пароль"
            type="password"
          />
          {errors?.regConfirmPassword && touchedFields.regConfirmPassword && (
            <div className="reg-form__form_input_message">
              {errors?.regConfirmPassword?.message?.toString()}
            </div>
          )}
        </div>

        <Button type="submit" size="medium">
          Войти
        </Button>
      </form>
      {/* ----------------------------------------------------------------------------------- */}
      <div className="reg-form__footer">
        <span className="reg-form__footer_text">
          У Вас уже есть аккаунт?{' '}
          <NavLink to="/login">
            <span className="reg-form__footer_text_link">Авторизируйтесь</span>
          </NavLink>
        </span>
      </div>
    </div>
  );
};

export default RegistrationForm;
