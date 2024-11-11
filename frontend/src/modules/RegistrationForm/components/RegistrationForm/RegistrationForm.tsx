import { useRef, useState } from 'react';
import './RegistrationForm.scss';
import Button from '@/ui/Button/Button';
import { FieldValues, useForm } from 'react-hook-form';
import { NavLink } from 'react-router-dom';
import { useRegister } from '../../api/registerRequest';
import { EMAIL_REGEX } from '@/globalConsts';
import DropDown from '@/ui/DropDown/DropDown';
import {
  removeValidationError,
  throwValidationError,
} from '@/utils/validationErrors';

const mockSpecializations = [
  {
    id: 1,
    label: 'Администратор системы',
  },
  {
    id: 2,
    label: 'Backend-разработчик',
  },
  {
    id: 3,
    label: 'Frontend-разработчик',
  },
  {
    id: 4,
    label: 'Data Scientist',
  },
  {
    id: 5,
    label: 'Мобильный разработчик',
  },
  {
    id: 6,
    label: 'DevOps-инженер',
  },
  {
    id: 7,
    label: 'QA-инженер',
  },
  {
    id: 8,
    label: 'ML-Инженер',
  },
  {
    id: 9,
    label: 'Системный аналитик',
  },
];
// import { useSpecializations } from '../../api/getSpecializationsRequest';

const RegistrationForm = () => {
  const regFormRef = useRef<HTMLFormElement>(null);
  const [specialization, setSpecialization] = useState<number>(-1);
  // const specializations = useSpecializations();

  const registrate = useRegister();

  const regForm = useForm({
    mode: 'onChange',
  });

  const { register, handleSubmit, watch, formState } = regForm;
  const { touchedFields, errors } = formState;

  const onSubmit = (data: FieldValues) => {
    const errorPlace = document.getElementById(
      'specialization_dropdown_error'
    ) as HTMLElement;
    if (specialization === -1) {
      throwValidationError(errorPlace, 'Обязательное поле');
      return;
    }
    removeValidationError(errorPlace);
    registrate(data.regLogin, data.regPassword);
  };

  const handleSpecializationSelect = (id: number) => {
    setSpecialization(id);
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
        onSubmit={handleSubmit(onSubmit)}
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
                value: EMAIL_REGEX,
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
        <div className="reg-form__form_dropdown">
          <span className="reg-form__form_dropdown_title">
            Ваша специализация
          </span>
          <DropDown
            handleSelect={handleSpecializationSelect}
            title={
              specialization == -1
                ? 'Выбрать'
                : mockSpecializations[specialization - 1].label
            }
            options={mockSpecializations}
          />
          <div
            id="specialization_dropdown_error"
            className="reg-form__form_dropdown_message"
          ></div>
        </div>

        <Button className="reg-form__form_submit" type="submit" size="medium">
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
