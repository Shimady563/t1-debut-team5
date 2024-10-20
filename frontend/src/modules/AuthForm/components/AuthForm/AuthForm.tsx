import Button from '@/ui/Button/Button';
import { useRef } from 'react';
import { useForm } from 'react-hook-form';
import './AuthForm.scss';

const AuthForm = () => {
  const authFormRef = useRef<HTMLFormElement>(null);

  const authForm = useForm({
    mode: 'onChange',
  });

  const { register, handleSubmit, watch, formState } = authForm;
  const { isValid, touchedFields, errors } = formState;

  const handleLoginSubmit = () => {
    console.log('aaaaa');
  };

  return (
    <div className="auth-form">
      <div className="auth-form__header">
        <h1 className="auth-form__header_title">Авторизация</h1>
        <span className="auth-form__header_subtitle">
          Введите свои текущие учетные данные <br /> для авторизации в системе
        </span>
      </div>
      {/* -------------------------------------------------------------- */}
      <form
        ref={authFormRef}
        onSubmit={handleSubmit(handleLoginSubmit)}
        className="auth-form__form"
      >
        <div
          className="auth-form__form_input"
          style={{ position: 'relative', width: `100%` }}
        >
          <input
            className="auth-form__form_input_block"
            {...register('authLogin', {
              required: 'Обязательное поле',
              pattern: {
                value: /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/,
                message: 'Введите корректный e-mail', //!!!
              },
            })}
            placeholder="Введите e-mail"
          />
          {errors?.authLogin && touchedFields.authLogin && (
            <div className="auth-form__form_input_message">
              {errors?.authLogin?.message?.toString()}
            </div>
          )}
        </div>
        <div
          className="auth-form__form_input"
          style={{ position: 'relative', width: `100%` }}
        >
          <input
            className="auth-form__form_input_block"
            {...register('authPassword', {
              required: 'Обязательное поле',
              minLength: {
                value: 8,
                message: 'Минимум 8 символов',
              },
            })}
            placeholder="Введите пароль"
            type="password"
          />
          {errors?.authPassword && touchedFields.authPassword && (
            <div className="auth-form__form_input_message">
              {errors?.authPassword?.message?.toString()}
            </div>
          )}
        </div>

        <Button type="submit" size="medium">
          Войти
        </Button>
      </form>
      {/* ----------------------------------------------------------------------------------- */}
      <div className="auth-form__footer">
        <span className="auth-form__footer_text">
          Еще нет аккаунта?{' '}
          <span className="auth-form__footer_text_link">Зарегистрируйтесь</span>
        </span>
      </div>
    </div>
  );
};

export default AuthForm;
