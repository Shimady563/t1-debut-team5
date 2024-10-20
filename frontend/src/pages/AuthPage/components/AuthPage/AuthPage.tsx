import AuthForm from '@/modules/AuthForm/components/AuthForm/AuthForm';
import React from 'react';
import './AuthPage.scss';

const AuthPage = () => {
  return (
    <div className="auth-page">
      <AuthForm />
    </div>
  );
};

export default AuthPage;
